public abstract class Card
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
}
