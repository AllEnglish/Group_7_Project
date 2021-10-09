import java.util.*;

public class Computer2 extends Agent
{    
    public Computer(int type)
    {
        super(type);
    }
    
    public void act(List<Card> path)
    {
        int millisecond = (int)(Math.random() * 400);
        try
        {
            Thread.sleep(millisecond);
        }
        catch (InterruptedException e) {}
        
        /* To-do */
        
        if (path.size() <= 3)
            this.setInExploring(false);
        else if (3 < path.size() < 6)
            this.setInExploring(Math.random() <= 0.5);
        else
            this.setInExploring(Math.random() < 0.85);
        
        
        /* End of to-do */
        
        System.out.println("    " + this.getClass().getName() + " " + this.type + " decided! (" + (millisecond / 1000.0) + "s)");
    }
}
