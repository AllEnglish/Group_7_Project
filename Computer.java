import java.util.*;

public class Computer extends Agent
{    
    public Computer(int type)
    {
        super(type);
    }
    
    public void act()
    {
        int millisecond = (int)(Math.random() * 5000);
        try
        {
            Thread.sleep(millisecond);
        }
        catch (InterruptedException e)
        {
        }
        
        this.setInExploring(Math.random() < 0.5);
        System.out.println("explorer " + this.type + " done! spent " + (millisecond / 1000.0) + "second(s).");
    }
}
