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
    public final Object clone() throws CloneNotSupportedException
    { 
        try
        {
            Class clazz = this.getClass();
            Constructor haha = clazz.getDeclaredConstructor(new Class[]{clazz});
            haha.setAccessible(true);
            Object copy = haha.newInstance(new Object[]{clazz.cast(this)});
            return clazz.cast(copy);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new CloneNotSupportedException();
        }
    }
}
