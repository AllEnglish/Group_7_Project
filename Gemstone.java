import java.util.ArrayList;

public class Gemstone extends Treasure
{
    protected int currentValue;
    
    public Gemstone(int number, int value)
    {
        super(number, value);
        this.currentValue = this.value;
    }

    public int getCurrentValue()
    {
        return this.currentValue;
    }

    @Override
    public String name()
    {
        return "Gemstone";
    }

    @Override
    public void share(ArrayList<Agent> receivers)
    {
        int oneShare = this.currentValue / receivers.size();
        this.currentValue %= receivers.size();
        
        for (Agent receiver : receivers)
            receiver.addGems(oneShare);
    }
}
