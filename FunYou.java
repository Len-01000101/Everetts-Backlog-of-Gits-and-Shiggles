import java.io.IOException;

public class FunYou
{
    public static void main(String[] args)
    {
        ProcessBuilder pb = new ProcessBuilder("taskmgr.exe");

        while(true)
        {
            try
            {
                pb.start();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}