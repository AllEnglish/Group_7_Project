import java.util.*;

public class Computer extends Agent
{    
    public Computer(int type)
    {
        super(type);
    }
    
    public void act()
    {
        try
        {
            Thread.sleep((int)(Math.random() * 5000));
        }
        catch (InterruptedException e)
        {
        }
        
        this.setInExploring(Math.random() < 0.5);
        System.out.println("explorer " + this.type + " done!");
    }
}
