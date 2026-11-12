import java.awt.*;
import java.util.Random;
import javax.swing.*;


//STAY IN CHARACTER!!!
public class FuckYou 
{
    public int roofHeight;
    public static int rightRoundPanels;
    public boolean leftWall;
    public int leftWallWidth;
    public boolean rightWall;
    public int rightWallWidth;
    public static boolean rightRound;
    public static int leftRoundPanels;
    public static boolean leftRound;
    public static final Color grossAssWallpaper = new Color(236, 212, 133);

    public static void main(String[] args)
    {
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();

        buildWalls(false, 0, false, 0, 0, grossAssWallpaper);
        //JFrame child = new JFrame("I AM A FUCKING ARCHITECT"); 
        //ImageIcon imageIcon = new ImageIcon("C:/Users/evhog/Downloads/photo.png");
        //JLabel label = new JLabel(imageIcon);
        //child.add(label);
        //child.setLocation(((int) display.getWidth() / 2) - 100, (int) display.getHeight() - 200);
        //child.setSize(200, 200);
        //child.setVisible(true);

        Timer timer = new Timer(500, e -> {
            buildWalls(false, 0, false, 0, 0, grossAssWallpaper);
        });

        timer.setRepeats(true);
        timer.start();

    }
    
    public static void buildWalls(boolean leftWall, int leftWallWidth, boolean rightWall, int rightWallWidth, int roofHeight, Color grossAssWallpaper)
    {
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();

        Random random = new Random();

        roofHeight = random.nextInt(200, ((int) display.getHeight() - 200));
        leftWall = random.nextBoolean();
        leftWallWidth = random.nextInt(20, (int) display.getWidth() / 2 - 100);
        rightWall = random.nextBoolean();
        rightWallWidth = random.nextInt(20, (int) display.getWidth() / 2 - 100);


        for (Window window : Window.getWindows()) 
        {
            if (window instanceof JFrame) 
            {
                window.dispose();
            }
        }

        //You spin me
        rightRound = false;
        //baby
        if(rightWall)
        { //like a record baby
            rightRound /*round round*/ = random.nextBoolean();
        }

        if(rightRound)
        {
            rightRoundPanels = 0;

            while (rightRoundPanels < 20)
            {
                rightRoundPanels++;
                JFrame rightRoundPanel = new JFrame(" ");
                rightRoundPanel.setLocation(((int) display.getWidth() - rightWallWidth - 100 + 10 * rightRoundPanels), 0 + roofHeight - 200 + 10 * rightRoundPanels);
                rightRoundPanel.setSize(40, 200);
                rightRoundPanel.getContentPane().setBackground(grossAssWallpaper);
                rightRoundPanel.setVisible(true);
            }
        }

        if (rightWall)
        {
            JFrame right = new JFrame(" ");
            right.setLocation(((int) display.getWidth() - rightWallWidth), 0);
            right.setSize(rightWallWidth, (int) display.getHeight());
            right.getContentPane().setBackground(grossAssWallpaper);
            right.setVisible(true);
        }

        leftRound = false;

        if(leftWall)
        {
            leftRound = random.nextBoolean();
        }

        if(leftRound)
        {
            leftRoundPanels = 0;

            while (leftRoundPanels < 20)
            {
                leftRoundPanels++;
                JFrame leftRoundPanel = new JFrame(" ");
                leftRoundPanel.setLocation(0 + 10 * leftRoundPanels + leftWallWidth - 200, (0 + roofHeight) - 10 * leftRoundPanels);
                leftRoundPanel.setSize(40, 200);
                leftRoundPanel.getContentPane().setBackground(grossAssWallpaper);
                leftRoundPanel.setVisible(true);
            }
        }

        if (leftWall)
        {
            JFrame left = new JFrame(" ");
            left.setLocation(0, 0);
            left.setSize(leftWallWidth, (int) display.getHeight());
            left.getContentPane().setBackground(grossAssWallpaper);
            left.setVisible(true);
        }

        JFrame roof = new JFrame("I AM A FUCKING ARCHITECT");
        roof.setLocation(0, 0);
        roof.setSize((int) display.getWidth(), roofHeight);
        roof.setVisible(true);
        roof.getContentPane().setBackground(grossAssWallpaper);
    }
}