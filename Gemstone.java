public class Gemstone extends Treasure
{
    protected final int originalValue;
    
    public Gemstone(int number, int originalValue)
    {
        super(number);
        this.originalValue = originalValue;
    }

    public int getOriginalValue()
    {
        return this.originalValue;
    }

    @Override
    public String name()
    {
        return "Gemstone";
    }

    @Override
    public void share(ArrayList<Agent> receivers)
    {
        int oneShare = this.value / receivers.size();
        this.Value %= receivers.size();
        
        for (Agent receiver : receivers)
            receiver.addGems(oneShare)
    }
}
