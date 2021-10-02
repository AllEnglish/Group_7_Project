import java.util.ArrayList;

public class Artifact extends Treasure
{
    public Artifact(int number, int value)
    {
        super(number, value);
    }
    
    @Override
    public String name()
    {
        String nameOfArtifact;
        
        switch (this.number)
        {
            case 1:
                nameOfArtifact = "Beaker";
                break;
            default:
                nameOfArtifact = "Unknown";
                break;
        }
        
        return nameOfArtifact;
    }
    
    @Override
    public void share(ArrayList<Agent> receivers)
    {
        if (receivers.size() == 1 && !receivers.get(0).status)
            receivers.get(0).ownedArtifact.add(this);
    }
}
