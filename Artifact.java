public class Artifact extends Treasure
{
    public Artifact(int number, int value)
    {
        super(number, value);
    }
    
    @Override
    public String name()
    {
        return "name";
    }
    
    @Override
    public void share(ArrayList<Agent> receivers)
    {
        if (receivers.size() == 1 && !receivers.get(0).status)
            receivers.get(0).ownedArtifact.add(this);
    }
}
