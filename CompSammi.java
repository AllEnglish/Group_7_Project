import java.util.*;

public class CompSammi extends Agent
{    
    public CompSammi(int type)
    {
        super(type);
    }
    
    @Override
    public boolean decision(GameData g)
    {
        ArrayList<Card> path = g.getPath();
        
        if (path.size() <= 3)
            return true;
        else if (path.get(path.size() - 1) instanceof Treasure)
            return (Math.random() < 0.85);
        else
            return (Math.random() < 0.55);
    }
}
