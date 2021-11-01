import java.util.*;

public class CompDefault extends Agent
{    
    public CompDefault(int type)
    {
        super(type);
    }
    
    @Override
    public boolean decision(GameData g)
    {
        return (Math.random() < 0.85);
    }
}
