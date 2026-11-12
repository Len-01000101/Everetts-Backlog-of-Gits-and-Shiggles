import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class screenSaver extends JPanel implements Runnable 
{
    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    private Random random = new Random();
    
    private double cameraZ = 0;
    private double speed = 0.05;
    private int totalSegments = 40;
    
    private LinkedList<hallwaySegment> segments = new LinkedList<>();

    private static class hallwaySegment 
    {
        double z;             
        double leftWallShift;
        double rightWallShift;
        int glitchType;

        public hallwaySegment(double z, Random r) 
        {
            this.z = z;
            this.leftWallShift = 0;
            this.rightWallShift = 0;
            this.glitchType = 0;

            double roll = r.nextDouble();

            if (roll < 0.08) 
            {
                this.leftWallShift = r.nextDouble() * 1.5 - 0.3;
                this.rightWallShift = r.nextDouble() * 1.5 - 0.3;
            } 
            else if (roll < 0.12) 
            {
                this.glitchType = 1;
            } 
            else if (roll < 0.15) 
            {
                this.glitchType = 2;
            }
        }
    }

    public screenSaver()
    {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT)); 
        this.setBackground(new Color(225, 215, 160));
        
        for (int i = 0; i < totalSegments; i++) 
        {
            segments.add(new hallwaySegment(i * 1.0, random));
        }

        Thread loopThread = new Thread(this);
        loopThread.start();
    }

    public void run() 
    {
        while (true) 
        {
            updatePhysics();
            repaint();
            try 
            {
                Thread.sleep(16);
            } 
            catch (InterruptedException e) 
            {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void updatePhysics()
    {
        cameraZ += speed;

        if (!segments.isEmpty() && cameraZ >= segments.getFirst().z + 1.0) 
        {
            hallwaySegment oldSegment = segments.removeFirst();
            double nextZ = segments.getLast().z + 1.0;
            segments.add(new hallwaySegment(nextZ, random));
        }
    }

    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = WIDTH / 2;
        int centerY = HEIGHT / 2;

        for (int i = segments.size() - 1; i >= 0; i--) 
        {
            hallwaySegment seg = segments.get(i);
            
            double relativeZ = seg.z - cameraZ;
            if (relativeZ <= 0.05) relativeZ = 0.05;

            double fov = 400.0;
            double scale = fov / relativeZ;

            double roomHalfWidth = 2.5;
            int xLeft = (int) (centerX - (roomHalfWidth + seg.leftWallShift) * scale);
            int xRight = (int) (centerX + (roomHalfWidth + seg.rightWallShift) * scale);
            
            double roomHalfHeight = 2.0;
            int yCeiling = (int) (centerY - roomHalfHeight * scale);
            int yFloor = (int) (centerY + roomHalfHeight * scale);

            int colorDecay = (int) Math.max(0, Math.min(200, relativeZ * 5));
            Color wallColor = new Color(200 - colorDecay, 190 - colorDecay, 120 - colorDecay);
            Color floorColor = new Color(130 - colorDecay, 110 - colorDecay, 85 - colorDecay);
            Color ceilingColor = new Color(220 - colorDecay, 220 - colorDecay, 200 - colorDecay);

            if (seg.glitchType == 2) 
            {
                g2.setColor(Color.BLACK);
                g2.fillRect(xLeft, yCeiling, xRight - xLeft, yFloor - yCeiling);
                continue;
            }

            g2.setColor(floorColor);
            Polygon floorQuad = new Polygon();
            floorQuad.addPoint(xLeft, yFloor);
            floorQuad.addPoint(xRight, yFloor);
            floorQuad.addPoint(WIDTH, HEIGHT);
            floorQuad.addPoint(0, HEIGHT);
            g2.fillPolygon(floorQuad);

            g2.setColor(ceilingColor);
            Polygon ceilingQuad = new Polygon();
            ceilingQuad.addPoint(xLeft, yCeiling);
            ceilingQuad.addPoint(xRight, yCeiling);
            ceilingQuad.addPoint(WIDTH, 0);
            ceilingQuad.addPoint(0, 0);
            g2.fillPolygon(ceilingQuad);

            g2.setColor(wallColor);
            g2.fillRect(0, yCeiling, xLeft, yFloor - yCeiling);
            g2.fillRect(xRight, yCeiling, WIDTH - xRight, yFloor - yCeiling);
            g2.setColor(new Color(40, 40, 30, Math.max(0, 255 - (int)(relativeZ * 6))));
            g2.drawRect(xLeft, yCeiling, xRight - xLeft, yFloor - yCeiling);

            if (seg.glitchType == 1)
            {
                g2.setColor(new Color(100 - colorDecay / 2, 50 - colorDecay / 4, 30));
                int obstacleW = (int) (0.8 * scale);
                int obstacleH = (int) (1.2 * scale);
                g2.fillRect(centerX - obstacleW / 2, centerY - obstacleH / 2, obstacleW, obstacleH);
                g2.setColor(Color.BLACK);
                g2.drawRect(centerX - obstacleW / 2, centerY - obstacleH / 2, obstacleW, obstacleH);
            }
        }
        
        GradientPaint vignette = new GradientPaint(centerX, centerY, new Color(0, 0, 0, 0), centerX, 0, new Color(0, 0, 0, 180), true);
        g2.setPaint(vignette);
        g2.fillRect(0, 0, WIDTH, HEIGHT);
    }

    public static void main(String[] args) 
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                JFrame frame = new JFrame("Cool thingymabobber"); 
                screenSaver simulation = new screenSaver(); 
                
                frame.add(simulation); 
                frame.pack(); 
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                frame.setLocationRelativeTo(null); 
                frame.setResizable(false); 
                frame.setVisible(true); 
            }
        });
    }
}
