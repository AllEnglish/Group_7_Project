import java.util.*;

public class Computer extends Agent
{    
    public Computer(int type)
    {
        super(type);
    }
    
    // 
    public void act()
    {
        /*
        if (Math.random() < 0.5)
            setInExploring(true);
        else
            setInExploring(false);
        */       
        setInExploring(Math.random() < 0.5);
    }
}
