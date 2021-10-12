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
            Gemstone copy = new Gemstone(self.number, self.value);
            copy.valueOfLeftover = this.valueOfLeftover;
            
            return copy;
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
