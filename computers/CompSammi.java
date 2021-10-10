import java.util.*;

public class CompSammi extends Agent
{    
    public CompSammi(int type)
    {
        super(type);
    }
    
    public void act(List<Card> path)
    {
        int millisecond = (int)(Math.random() * 500);
        try
        {
            Thread.sleep(millisecond);
        }
        catch (InterruptedException e) {}
        
        /* To-do */
        
        if (path.size() <= 3)
            this.setInExploring(true);
        else if (path.get(path.size() - 1) instanceof Treasure)
            this.setInExploring(Math.random() < 0.55);
        else
            this.setInExploring(Math.random() < 0.85);
        
        /* End of to-do */
        
        System.out.println("    " + this.getClass().getName() + " " + this.type + " decided! (" + (millisecond / 1000.0) + "s)");
    }
}