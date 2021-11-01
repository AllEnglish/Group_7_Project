import java.util.Scanner;

public class Player extends Agent
{    
    public Player(int type)
    {
        super(type);
    }
    
    @Override
    public boolean decision(GameData data)
    {
        return new Scanner(System.in).nextLine().equals("y");
    }
}
