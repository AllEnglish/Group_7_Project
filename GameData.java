import java.util.ArrayList;
import java.util.List;

public class GameData
{
    private int countOfExplorersInTomb;
    private List<Card> path;
    
    public GameData(Game g)
    {
        this.countOfExplorersInTomb = 0;
        this.path = new ArrayList<>();
        
        for (Card c : g.getPath())
        {
            try
            {
                this.path.add(c.clone());
            }
            catch (CloneNotSupportedException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public int getCountOfExplorersInTomb()
    {
        return this.countOfExplorersInTomb;
    }
    
    public List<Card> getPath()
    {
        return this.path;
    }
}
