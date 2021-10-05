public class Main
{
    public static void main(String[] args)
    {
        Thread t = new Thread(new Game(5));
        t.start();
    }
}
