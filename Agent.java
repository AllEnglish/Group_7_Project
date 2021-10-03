import java.util.ArrayList;

public abstract class Agent
{
    protected final int type;
    protected boolean status;
    protected int gems;
    protected int gemsInTent;
    protected ArrayList<Artifact> ownedArtifact;
    
    public Agent(int type)
    {
        this.type = type;
    }
    
    public int getType()
    {
        return this.type;
    }

    public int getGems()
    {
        return this.gems;
    }
    
    public void setGems(int gems)
    {
        this.gems = gems;
    }
    
    public int getGemsInTent()
    {
        return this.gemsInTent;
    }
    
    public void setGemsInTent(int gemsInTent)
    {
        this.gemsInTent = gemsInTent;
    }
    
    public void addGems(int gems)
    {
        this.gems += gems;
    }

    public void putGemsIntoTent()
    {
        this.gemsInTent += this.gems;
        this.gems = 0;
    }

    public void lostAllGems()
    {
        this.gems = 0;
    }

    public abstract void act();
}
