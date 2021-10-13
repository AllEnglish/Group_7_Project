import java.util.*;

public class CompSammi extends Agent
{    
    public CompSammi(int type)
    {
        super(type);
    }
    
    @Override
    public boolean decision(int countOfExplorersInTomb, List<Card> path, List<Hazard> removedHazard)
    {
        if (path.size() <= 3)
            return true;
        else if (path.get(path.size() - 1) instanceof Treasure)
            return (Math.random() < 0.85);
        else
            return (Math.random() < 0.55);
    }
}
