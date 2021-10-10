import java.util.*;

public class CompDefault extends Agent
{    
    public CompDefault(int type)
    {
        super(type);
    }
    
    public void act(List<Card> path)
    {
        try
        {
            int millisecond = (int)(Math.random() * 200);
            Thread.sleep(millisecond);
            System.out.println("    " + this.getClass().getName() + " " + this.type + " decided! (" + (millisecond / 1000.0) + "s)");
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
        /* To-do */
        
        this.setInExploring(Math.random() < 0.85);
        
        /* End of to-do */
    }
}
