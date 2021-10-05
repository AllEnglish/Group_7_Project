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
        Scanner keyboard = new Scanner(System.in);
        String test = keyboard.nextLine();
        
        if(test.equals("y"))
        {
            setInExploring(true);
        }
        else
        {
            setInExploring(false);
        }
    }
}
