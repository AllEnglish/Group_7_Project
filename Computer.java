import java.util.*;

public class Computer extends Agent
{    
    public Computer(int type)
    {
        super(type);
    }
    
    public void act()
    {
        int millisecond = (int)(Math.random() * 200);
        try
        {
            Thread.sleep(millisecond);
        }
        catch (InterruptedException e)
        {
        }
        
        this.setInExploring(Math.random() < 0.85);
        System.out.println(this.type + (millisecond / 1000.0) + " -> ");
    }
}
