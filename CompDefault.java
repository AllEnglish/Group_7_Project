import java.util.*;

public class CompDefault extends Agent
{    
    public CompDefault(int type)
    {
        super(type);
    }
    
    @Override
    public boolean decision(int countOfExplorersInTomb, List<Card> path, List<Hazard> removedHazard)
    {
        return (Math.random() < 0.85);
    }
}
