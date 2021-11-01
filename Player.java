import java.util.*;

public class Player extends Agent
{    
    public Player(int type)
    {
        super(type);
    }
    
    @Override
    public boolean decision(GameData g)
    {
        return new Scanner(System.in).nextLine().equals("y");
    }
}
