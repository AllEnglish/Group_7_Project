import java.util.ArrayList;

public abstract class Agent
{
    protected final int type;
    protected boolean inExploring;
    protected int gems;
    protected int gemsInsideTent;
    protected ArrayList<Artifact> possessionOfArtifacts;
    
    public Agent(int type)
    {
        this.type = type;
    }
    
    public int getType()
    {
        return this.type;
    }
    
    public boolean isInExploring()
    {
        return this.isExploring;
    }

    public int getGems()
    {
        return this.gems;
    }
    
    public void setGems(int gems)
    {
        this.gems = gems;
    }
    
    public int getGemsInsideTent()
    {
        return this.gemsInsideTent;
    }
    
    public void setGemsInsideTent(int gemsInsideTent)
    {
        this.gemsInsideTent = gemsInsideTent;
    }
    
    public void addGems(int gems)
    {
        this.gems += gems;
    }

    public void storeGemsIntoTent()
    {
        this.gemsInsideTent += this.gems;
        this.gems = 0;
    }

    public void lostAllGems()
    {
        this.gems = 0;
    }

    public abstract void act();
}
