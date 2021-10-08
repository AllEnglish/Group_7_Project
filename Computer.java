import java.util.*;

public class Computer extends Agent
{    
    public Computer(int type)
    {
        super(type);
    }
    
    public void act()
    {
        int millisecond = (int)(Math.random() * 1000);
        try
        {
            Thread.sleep(millisecond);
        }
        catch (InterruptedException e)
        {
        }
        
        this.setInExploring(Math.random() < 0.8);
        System.out.println("    explorer " + this.type + " just makes their decision! (" + (millisecond / 1000.0) + "s)");
    }
}
