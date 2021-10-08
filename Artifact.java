import java.util.ArrayList;

public class Artifact extends Treasure
{
    protected boolean takenAway;
    
    public Artifact(int number, int value)
    {
        super(number, value);
        this.takenAway = false;
    }
    
    @Override
    public String name()
    {
        String nameOfArtifact;
        
        switch (this.number)
        {
            case 0:
                nameOfArtifact = "King Tut's Dagger";
                break;
            case 1:
                nameOfArtifact = "Ankh";
                break;
            case 2:
                nameOfArtifact = "Falcon Pectoral";
                break;
            case 3:
                nameOfArtifact = "Crook and Flail";
                break;
            case 4:
                nameOfArtifact = "Mask of Tutankhamun";
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
        if (receivers.size() == 1 && !receivers.get(0).isInExploring())
        {
            receivers.get(0).possessionOfArtifacts.add(this);
            this.takenAway = true;
        }
    }
    
    @Override
    public String toString()
    {
        return String.format("<\u001B[33m%s\u001B[0m>", (this.takenAway ? "---" : this.name()));
        // return String.format("%s <Artifact %d with value %d>", this.name(), this.number, this.value);
    }
}
