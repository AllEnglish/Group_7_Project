import java.util.*;

public class Computer extends Agent
{    
    public Computer(int type)
    {
        super(type);
    }
    
    public void act()
    {
        int millisecond = (int)(Math.random() * 1200);
        try
        {
            Thread.sleep(millisecond);
        }
        catch (InterruptedException e)
        {
        }
        
        this.setInExploring(Math.random() < 0.7);
        System.out.println("explorer " + this.type + " just makes it's decision! (" + (millisecond / 1000.0) + "s)");
    }
}
