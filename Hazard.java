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
            case 0:
                nameOfHazard = "Booby Trap";
                break;
            case 1:
                nameOfHazard = "Spiders";
                break;
            case 2:
                nameOfHazard = "Mummy";
                break;
            case 3:
                nameOfHazard = "Curse";
                break;
            case 4:
                nameOfHazard = "Rockslide Trap";
                break;
            default:
                nameOfHazard = "Unknown";
                break;
        }
        
        return nameOfHazard;
    }
}
