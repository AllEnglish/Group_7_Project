import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
    public boolean equals(Object obj)
    {
        if (obj instanceof Card)
            return this.getClass() == obj.getClass() && this.number == ((Card)obj).number;
        else
            return false;
    }
    
    @Override
    public final Card clone() throws CloneNotSupportedException
    { 
        try
        {
            Class<? extends Card> clazz = this.getClass();
            Constructor<? extends Card> constructor = clazz.getDeclaredConstructor(new Class[]{clazz});
            constructor.setAccessible(true);
            Object copy = constructor.newInstance(new Object[]{clazz.cast(this)});
            return (Card)copy;
            // return clazz.cast(copy);
        }
        catch (Exception e)
        {
            throw new CloneNotSupportedException();
        }
    }
}
