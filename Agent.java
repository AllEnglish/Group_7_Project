import java.util.ArrayList;

public abstract class Agent // implements Comparable<Agent>
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
        return this.inExploring;
    }
    
    public void setInExploring(boolean inExploring)
    {
        this.inExploring = inExploring;
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

    public void flee()
    {
        this.gems = 0;
        this.inExploring = false;
    }
    
    // the name of this method and it's varible are temporary
    public int total()
    {
        int xv = 0;
        for (Artifact af : this.possessionOfArtifacts)
            xv += af.getValue();
        return this.gemsInsideTent + xv;
    }

    public abstract void act();
}
