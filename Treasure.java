import java.util.List;

public abstract class Treasure extends Card
{
    protected final int value;
    
    public Treasure(int number, int value)
    {
        super(number);
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }

    public abstract void share(List<Agent> receivers);
}
