import java.util.ArrayList;

public class Gemstone extends Treasure
{
    protected int valueOfLeftover;
    
    public Gemstone(int number, int value)
    {
        super(number, value);
        this.valueOfLeftover = this.value;
    }

    public int getValueOfLeftover()
    {
        return this.valueOfLeftover;
    }

    @Override
    public String name()
    {
        return "Gemstone";
    }

    @Override
    public void share(ArrayList<Agent> receivers)
    {
        int oneShare = this.valueOfLeftover / receivers.size();
        this.valueOfLeftover %= receivers.size();
        
        for (Agent receiver : receivers)
            receiver.addGems(oneShare);
    }
    
    @Override
    public String toString()
    {
        return String.format("<\u001B[32m%c\u001B[0m>", this.name().charAt(0));
        // return String.format("%s <Gemstone %d with value %d/%d>", this.name(), this.number, this.valueOfLeftover, this.value);
    }
}
