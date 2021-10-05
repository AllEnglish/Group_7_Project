import java.util.*;

public class Player extends Agent
{    
    public Player(int type)
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
