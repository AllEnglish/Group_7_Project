public abstract class Agent
{
    protected boolean status;
    protected int gems;
    protected int gemsInTent;
    protected ArrayList<Artifact> ownedArtifact;

    public int getGems()
    {
        retrun this.gems;
    }
    
    public void setGems(int gems)
    {
        this.gems = gems;
    }
    
    public int getGemsInTent()
    {
        return this.gemsInTent;
    }
    
    public int setGemsInTent(int gemsInTent)
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
