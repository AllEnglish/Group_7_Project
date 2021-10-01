public abstract class Treasure extends Card
{
    protected int value;
    
    public Treasure(int number)
    {
        super(number);
    }

    public int getValue()
    {
        return this.value;
    }
    
    public void setValue(int value)
    {
        this.value = value;
    }
    
    public abstract void share(ArrayList<Agent> receivers);
}
