public class Hazard extends Card
{
    public Hazard(int number)
    {
        super(number);
    }
    
    @Override
    public String name()
    {
        String nameOfHazard;
        
        switch (this.number)
        {
            case 1:
                nameOfHazard = "Booby Trap";
                break;
            case 2:
                nameOfHazard = "Spiders";
                break;
            case 3:
                nameOfHazard = "Mummy";
                break;
            case 4:
                nameOfHazard = "Curse";
                break;
            case 5:
                nameOfHazard = "Rockslide Trap";
                break;
            default:
                nameOfHazard = "Unknown";
                break;
        }
        
        return nameOfHazard;
    }
}
