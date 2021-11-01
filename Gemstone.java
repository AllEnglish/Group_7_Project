import java.util.List;

public class Gemstone extends Treasure
{
    protected int remainValue;
    
    public Gemstone(int number, int value)
    {
        super(number, value);
        this.remainValue = this.value;
    }
    
    public Gemstone(Gemstone g)
    {
        super(g.number, g.value);
        this.remainValue = g.remainValue;
    }

    public int getRemainValue()
    {
        return this.remainValue;
    }
    
    public void resetValue()
    {
        this.remainValue = this.value;
    }

    @Override
    public String name()
    {
        return "Gemstone";
    }

    @Override
    public void share(List<Agent> receivers)
    {
        if (!receivers.isEmpty())
        {
            int oneShare = this.remainValue / receivers.size();
            this.remainValue %= receivers.size();
            
            for (Agent receiver : receivers)
                receiver.addGems(oneShare);
        }
    }
    
    @Override
    public String toString()
    {
        return String.format("<\u001B[32m%d/%d\u001B[0m>", this.valueOfLeftover, this.value);
        // return String.format("%s <Gemstone %d with value %d/%d>", this.name(), this.number, this.remainValue, this.value);
    }
}
