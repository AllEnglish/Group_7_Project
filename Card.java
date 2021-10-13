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
    public final Object clone() throws Exception
    {
        Class clazz = this.getClass();
        Constructor haha = clazz.getDeclaredConstructor(new Class[]{Card.class});
        haha.setAccessible(true);
        Card copy = (Card)haha.newInstance(new Object[]{this});
        
        return copy;
    }
}
