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
        Card copy;
        
        if (this instanceof Gemstone)
        {
            Gemstone origin = (Gemstone)this;
            copy = new Gemstone(origin.number, origin.value);
            copy.valueOfLeftover = origin.valueOfLeftover;
        }
        else if (this instanceof Artifact)
        {
            Artifact origin = (Artifact)this;
            copy = new Artifact(origin.number, origin.value);
            copy.inTomb = origin.inTomb;
        }
        else if (this instanceof Hazard)
        {
            Hazard origin = (Hazard)this;
            copy = new Hazard(origin.number);
        }
        else
            throw new CloneNotSupportedException();
            
        return copy;
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
