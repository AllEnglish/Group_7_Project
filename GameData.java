public class GameData
{
    private int playerNum;
    private List<Card> path;
    
    public GameData(Game g)
    {
        this.playerNum = 0;
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
    
    public int getPlayerNum()
    {
        return this.playerNum;
    }
    
    public List<Card> getPath()
    {
        return this.path;
    }
}
