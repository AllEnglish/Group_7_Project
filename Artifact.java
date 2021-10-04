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
                nameOfArtifact = "King Tut's Dagger";
                break;
            case 2:
                nameOfArtifact = "Ankh";
                break;
            case 3:
                nameOfArtifact = "Falcon Pectoral";
                break;
            case 4:
                nameOfArtifact = "Crook and Flail";
                break;
            case 5:
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
            receivers.get(0).possessionOfArtifacts.add(this);
    }
}
