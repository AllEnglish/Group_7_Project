import java.util.ArrayList;

public class GameData
{
    private int stayExplorersCount;
    private ArrayList<Card> path;
    
    public GameData(Game g)
    {
        this.stayExplorersCount = 0;
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
    
    public int getStayExplorersCount()
    {
        return this.stayExplorersCount;
    }
    
    public ArrayList<Card> getPath()
    {
        return this.path;
    }
}
