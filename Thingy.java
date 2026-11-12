import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Thingy
{
    public static int paddleX = 0;
    public int ballShiftX = 5;
    public int ballShiftY = 5;

    public int ballX = 0;
    public int ballY = 0;

    public static void main(String[] args, int ballX, int ballY, int ballShiftX, int ballShiftY)
    {
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        int paddleWidth = 250;
        int paddleHeight = 75;
        int moveAmount = 20;

        Runnable task = new Runnable() 
        {
            public void run(int ballX, int ballY, int ballShiftX, int ballShiftY) 
            {
                ballX += ballShiftX;
                ballY += ballShiftY;
            }
        };

        JFrame ball = new JFrame("Ball");
        
        ball.setLocation(ballX, ballY);
        ball.setSize(150, 130);
        ball.setVisible(true);

        JFrame paddle = new JFrame("Paddle");
        paddle.setLocation(paddleX, ((int) display.getHeight() - paddleHeight));
        paddle.setSize(paddleWidth, paddleHeight);
        paddle.setVisible(true);

        paddle.addKeyListener(new KeyAdapter() 
        {
            public void keyPressed(KeyEvent e) 
            {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && Thingy.paddleX < (display.getWidth() - paddleWidth))
                {
                    Thingy.paddleX += moveAmount;
                }

                if (e.getKeyCode() == KeyEvent.VK_LEFT && Thingy.paddleX > 0) 
                {
                    Thingy.paddleX -= moveAmount;
                }
            }
        });
        
        if (ballX >= ((int) display.getWidth()) - 150 || ballX <= 0)
        {
            ballShiftX *= -1;
        }
        if (ballY >= ((int) display.getHeight()) - 130 || ballY <= 0)
        {
            ballShiftY *= -1;
        }

        while(true)
        {
            paddle.setLocation(paddleX, ((int) display.getHeight() - paddleHeight));
        }
        
        scheduler.scheduleAtFixedRate(task, 0, 15, TimeUnit.MILLISECONDS);
    }
}