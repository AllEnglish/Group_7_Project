import java.util.*;

public class Player extends Agent
{    
    public Player(int type)
    {
        super(type);
    }
    
    @Override
    public void act()
    {
        setInExploring(new Scanner(System.in).nextLine().equals("y"));
    }
}
