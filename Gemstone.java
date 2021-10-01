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

    public int share(int member)
    {
        // do something...
        return 0;
    }
}
