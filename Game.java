import java.util.ArrayList;
import java.util.Collections;

public class Game implements Runnable
{
    protected int round;
    ArrayList<Agent> playerList = new ArrayList<>();
    ArrayList<Card> deck = new ArrayList<>();
    ArrayList<Card> fold = new ArrayList<>();
    ArrayList<Card> path = new ArrayList<>();

    public Game(int number)
    {
        // this.number = number;
    }
    
    @Override
    public void run()
    {
        
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
