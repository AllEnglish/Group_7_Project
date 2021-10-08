import java.util.*;

public class Computer extends Agent
{    
    public Computer(int type)
    {
        super(type);
    }
    
    public void act()
    {
        int millisecond = (int)(Math.random() * 400);
        try
        {
            Thread.sleep(millisecond);
        }
        catch (InterruptedException e)
        {
        }
        
        this.setInExploring(Math.random() < 0.85);
        System.out.print(this.type + " (" + (millisecond / 1000.0) + "s) -> ");
    }
}
