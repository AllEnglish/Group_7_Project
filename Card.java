public abstract class Card implements Cloneable
{
    protected int number;

    public Card(int number)
    {
        this.number = number;
    }

    public abstract String name();

    public int getNumber()
    {
        return this.number;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        if (this instanceof Gemstone)
        {
            Gemstone self = (Gemstone)this;
            return new Gemstone(self.number, self.value);
        }
        else
            throw new CloneNotSupportedException();
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Card)
            return this.getClass() == obj.getClass() && this.number == ((Card)obj).number;
        else
            return false;
    }
}
