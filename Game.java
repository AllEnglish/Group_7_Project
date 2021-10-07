import java.util.ArrayList;
import java.util.Collections;

public class Game implements Runnable
{
    protected final int round;
    protected ArrayList<Agent> explorers;
    protected ArrayList<Card> deck;
    protected ArrayList<Card> fold = new ArrayList<>();
    protected ArrayList<Card> path = new ArrayList<>();

    public Game(int round)
    {
        this.round = round;
        this.explorers = new ArrayList<>();
        this.deck = new ArrayList<>();
        
        // just for testing
        this.explorers.add(new Computer(0));
        this.explorers.add(new Computer(1));
        this.explorers.add(new Computer(2));
        this.explorers.add(new Computer(3));
        this.explorers.add(new Computer(4));
        this.explorers.add(new Computer(5));
    }
    
    @Override
    public void run()
    {
        this.initializeDeck();
        
        for (int currentRound = 0; currentRound < this.round; currentRound++)
        {
            for (Agent explorer : this.explorers)
                explorer.setInExploring(true);
            
            System.out.println("round " + (currentRound + 1));
            System.out.println();
            this.deckShuffle();
            
            do
            {     
                this.revealNextRoom();
                System.out.println();
                
                Card currentRoom = this.path.get(this.path.size() - 1);
                
                if (currentRoom instanceof Treasure)
                {
                    Treasure roomOfTreasure = (Treasure)currentRoom;
                    
                    ArrayList<Agent> p = new ArrayList<>();
                    
                    for (Agent explorer : this.explorers)
                        if (explorer.isInExploring())
                            p.add(explorer);

                    roomOfTreasure.share(p);
                }
                else if (currentRoom instanceof Hazard)
                {
                    Hazard roomOfHazard = (Hazard)currentRoom;
                    // do something...
                    // check if there is a same type of hazard on the path
                }
                
                System.out.println(this.path);

                for (Agent explorer : this.explorers)
                    if (explorer.isInExploring())
                        System.out.println("explorer " + explorer.getType() + " owns " + explorer.getGems() + " gem(s).");

                System.out.println("[?] asking everyone stay or leave.");

                for (Agent explorer : this.explorers)
                {
                    if (explorer.isInExploring())
                        explorer.act();
                }

                // Hint dialog, do not reserve it
                if (this.isSomeoneExploring())
                {
                    for (Agent explorer : this.explorers)
                        if (explorer.isInExploring())
                            System.out.print(explorer.getType() + " ");
                    System.out.print("want to keep exploring.");
                    System.out.println();
                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e) {}
                }
                else
                    System.out.println("game over! all of the explorers went back to the camp.");
            }
            while (this.isSomeoneExploring());

            break;
        }
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

    public void deckShuffle()
    {
        Collections.shuffle(this.deck);
    }

    public void revealNextRoom()
    {
        this.path.add(this.deck.remove(0));
    }

    public int getRound()
    {
        return this.round;
    }

    public boolean isSomeoneExploring()
    {
        boolean b = false;
        for (Agent p : this.explorers)
            b |= p.isInExploring();
        return b;
    }
    /*
    public Agent findWinner()
    {
        int maxScore = 0;
        int index;
        int artifactValue = 0;
        
        for (Artifact artifact : this.playerList.get(0).possessionOfArtifacts)
            artifactValue += artifact.getValue();
        maxScore =  this.playerList.get(0).getGemsInsideTent() + artifactValue;    
        
        for(int i = 1 ; i < this.playerList.size() ; i++)
        {
            int artifactValueCheck = 0;
            for (Artifact artifact : this.playerList.get(i).possessionOfArtifacts)
                artifactValueCheck += artifact.getValue();
            if (this.playerList.get(i).getGemsInsideTent() + artifactValueCheck > maxScore)
            {
                maxScore = this.playerList.get(i).getGemsInsideTent() + artifactValueCheck;
                index = i;
            }
            return this.playerList.get(index);     
        }        
    }
    */
       
    public Agent[] findWinner()
    {
        int maxScore = 0;
        ArrayList<Agent> winner = new ArrayList<>();
        
        for (Agent people : this.explorers)
            if (people.total() > maxScore)
                maxScore = people.total();    
         for(int i = 0 ; i < this.explorers.size() ; i++)
         {
            if (maxScore == this.explorers.get(i).total())
                winner.add(this.explorers.get(i));
         }
        return winner.toArray(new Agent[winner.size()]);
    }
    
}
