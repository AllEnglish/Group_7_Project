import java.util.ArrayList;
import java.util.Collections;

public class Game implements Runnable
{
    protected int round;
    protected ArrayList<Agent> playerList = new ArrayList<>();
    protected ArrayList<Card> deck;
    protected ArrayList<Card> fold = new ArrayList<>();
    protected ArrayList<Card> path = new ArrayList<>();

    public Game(int number)
    {
        // this.number = number;
        this.deck = new ArrayList<>();
    }
    
    @Override
    public void run()
    {
        this.initializeDeck();
        System.out.println("HI");
        this.deckShuffle();
        for (Card c : this.deck)
            System.out.println(c.getNumber() + " ");
    }
    
    private void initializeDeck()
    {
        this.deck.add(new Hazard(0));
        this.deck.add(new Hazard(0));
        this.deck.add(new Hazard(0));
        this.deck.add(new Hazard(1));
        this.deck.add(new Hazard(1));
        this.deck.add(new Hazard(1));
        this.deck.add(new Hazard(2));
        this.deck.add(new Hazard(2));
        this.deck.add(new Hazard(2));
        this.deck.add(new Hazard(3));
        this.deck.add(new Hazard(3));
        this.deck.add(new Hazard(3));
        this.deck.add(new Hazard(4));
        this.deck.add(new Hazard(4));
        this.deck.add(new Hazard(4));
        
        this.deck.add(new Gemstone(0, 1));
        this.deck.add(new Gemstone(1, 2));
        this.deck.add(new Gemstone(2, 3));
        this.deck.add(new Gemstone(3, 4));
        this.deck.add(new Gemstone(4, 5));
        this.deck.add(new Gemstone(4, 5));
        this.deck.add(new Gemstone(5, 7));
        this.deck.add(new Gemstone(5, 7));
        this.deck.add(new Gemstone(6, 9));
        this.deck.add(new Gemstone(7, 11));
        this.deck.add(new Gemstone(7, 11));
        this.deck.add(new Gemstone(8, 13));
        this.deck.add(new Gemstone(9, 14));
        this.deck.add(new Gemstone(10, 15));
        this.deck.add(new Gemstone(11, 17));
    }

    public void deckShffule()
    {
        Collections.shuffle(this.deck);
    }

    public void flop()
    {
        this.path.add(this.deck.remove(0));
    }

    public int getRound()
    {
        return this.round;
    }

    public boolean isEveryoneBack()
    {
        boolean isAllBack = false;
        for (Agent p : this.playerList)
            isAllBack |= p.isInExploring();
        return !isAllBack ;
    }
}
