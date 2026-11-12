import random
import sys
import time

score = 0
bonus = 0
level = 1

print(" ")
print(" ")
print(" ")
print(" ")
print(" ")
print(" ")
print(" ")
print(" ")
print(" ")
print("HELLO USER!     BONUS AT 1000pts")
print(" ")
print(" ")
print(" ")
print(" ")
print(" ")
print(" ")
print(" ")
print(" ")
print(" ")
time.sleep(2)

while True:
 intone = random.randint(1,10)
 inttwo = random.randint(1,10)
 ans = (intone + inttwo)

 if score == 2500:
  level = 2
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print("           LEVEL UP!")
  print("            LEVEL 2")
  print(" ")
  print(" ")
  print(" ")
  time.sleep(2)

 if score == 5000:
  level = 3
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print("           LEVEL UP!")
  print("            LEVEL 3")
  print(" ")
  print(" ")
  print(" ")
  time.sleep(2)

 if score == 7500:
  level = 4
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print("           LEVEL UP!")
  print("            LEVEL 4")
  print(" ")
  print(" ")
  print(" ")
  time.sleep(2)

 if score == 10000:
  level = 5
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print("           LEVEL UP!")
  print("           LEVEL MAX")
  print(" ")
  print(" ")
  print(" ")
  time.sleep(2)
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print(" ")
  print("             LEVEL 5")
  print(" ")
  print(" ")
  print(" ")
  time.sleep(1)

 print(intone,"+",inttwo)
 print(" ")
 print(" ")
 print(" ")
 print(" ")
 print(" ")
 print(" ")
 print(" ")
 print(" ")
 print(" ")

 target_word = "score"
 user_input = input("ENTER ANSWER: ")

 if score >= 1000:
  bonus = 2500

 if target_word in user_input.lower():
  print(score)
  time.sleep(3)
 else:
  try:
    number = int(user_input)

    if number == ans:
     print("CORRECT")
     score = score + 100
     time.sleep(1)
    else:
     print("INCORRECT")
     print("ANSWER: ",ans)
     time.sleep(3)
     print(" ")
     print(" ")
     print(" ")
     print("           GAME OVER ")
     print("        _______________")
     print("             SCORE:     ")
     print("             ",score, "      ")
     print("          +",bonus,"BONUS")
     print("          TOTAL: ",score+bonus)
     print("          LEVEL: ",level)
     print(" ")
     break

  except ValueError:
   print("INVALID INPUT")