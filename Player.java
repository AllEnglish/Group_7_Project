import java.util.*;

public class Player extends Agent
{    
    public Player(int type)
    {
        super(type);
    }
    
    @Override
    public boolean decision(int countOfExplorersInTomb, List<Card> path, List<Hazard> removedHazard);
    {
        return new Scanner(System.in).nextLine().equals("y");
    }
}
