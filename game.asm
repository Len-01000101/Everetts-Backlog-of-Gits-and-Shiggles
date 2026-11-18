org 256

section .text
start:
    mov ah, 0
    mov al, 19
    int 16

    mov ax, 40960
    mov es, ax          

game_loop:
    mov ah, 0           
    mov al, 19          
    int 16              

check_input:
    mov ah, 1
    int 22
    jz update_physics

    mov ah, 0
    int 22
    
    cmp al, 119
    je move_up
    cmp al, 115
    je move_down
    cmp al, 27
    je exit_game
    jmp update_physics

move_up:
    mov ax, [paddleY]
    sub ax, 5
    cmp ax, 0
    jle clamp_top
    mov [paddleY], ax
    jmp update_physics
clamp_top:
    mov word [paddleY], 0
    jmp update_physics

move_down:
    mov ax, [paddleY]
    add ax, 5
    cmp ax, 170
    jge clamp_bottom
    mov [paddleY], ax
    jmp update_physics

clamp_bottom:
    mov word [paddleY], 170

update_physics:
    mov ax, [ballX]
    add ax, [ballDx]
    mov [ballX], ax

    mov ax, [ballY]
    add ax, [ballDy]
    mov [ballY], ax

    cmp ax, 0
    jle reverse_y
    
    ; Collision Check: Bottom Wall (Y >= 196)
    cmp ax, 196
    jge reverse_y
    jmp check_paddle_collision

reverse_y:
    mov ax, [ballDy]
    imul ax, -1
    mov [ballDy], ax

check_paddle_collision:
    mov ax, [ballX]
    cmp ax, 14
    jg render_shapes
    cmp ax, 10
    jl render_shapes

    mov ax, [ballY]
    cmp ax, [paddleY]  
    jl render_shapes
    
    mov bx, [paddleY]
    add bx, 30
    cmp ax, bx          
    jg render_shapes

reverse_x:
    mov ax, [ballDx]
    imul ax, -1
    mov [ballDx], ax

render_shapes:
    mov cx, [paddleY]
paddle_row:
    mov dx, 0
paddle_col:
    mov ax, cx          
    mov bx, 320         
    mul bx
    add ax, 10
    add ax, dx
    mov di, ax          
    mov byte [es:di], 15
    inc dx
    cmp dx, 4
    jne paddle_col
    inc cx
    mov bx, [paddleY]
    add bx, 30
    cmp cx, bx
    jne paddle_row

    mov cx, [ballY]
ball_row:
    mov dx, 0
ball_col:
    mov ax, cx          
    mov bx, 320         
    mul bx
    add ax, [ballX]    
    add ax, dx          
    mov di, ax          
    mov byte [es:di], 12
    inc dx
    cmp dx, 4
    jne ball_col
    inc cx
    mov bx, [ballY]
    add bx, 4
    cmp cx, bx
    jne ball_row

    mov ah, 134
    mov cx, 0
    mov dx, 16000
    int 21

    jmp game_loop

exit_game:
    mov ah, 0           
    mov al, 3
    int 16              

    mov ah, 76
    mov al, 0           
    int 33

section .data
    paddleY    dw 85       
    ballX      dw 160      
    ballY      dw 100      
    ballDx     dw 4        
    ballDy     dw 2
; It still doesn't work cuz I hardly understand Assembly
