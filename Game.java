import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class Game
{
    private final int round;
    private ArrayList<Agent> explorers;
    private ArrayList<Card> deck;
    private ArrayList<Card> path;
    private ArrayList<Artifact> artifactsInTomb;
    private Hazard lastOccurredHazard;

    public Game(int round)
    {
        this.round = round;
        this.explorers = new ArrayList<>();
        this.deck = new ArrayList<>();
        this.path = new ArrayList<>();
        this.artifactsInTomb = new ArrayList<>();
        this.lastOccurredHazard = null;
        
        // just for testing
        this.explorers.add(new CompSammi(0));
        this.explorers.add(new CompDefault(1));
        this.explorers.add(new CompDefault(2));
        this.explorers.add(new CompDefault(3));
        this.explorers.add(new CompDefault(4));
        this.explorers.add(new CompDefault(5));
        
        this.initializeCards();
    }
    
    public void startGame()
    {
        for (int currentRound = 0; currentRound < this.round; currentRound++)
        {
            this.reset();
            System.out.println();
            System.out.println("round " + (currentRound + 1) + " start!");
            
            do
            {
                System.out.println();
                this.revealNextRoom();
                
                Card currentRoom = this.path.get(this.path.size() - 1);
                
                if (currentRoom instanceof Treasure)
                {
                    Treasure roomOfTreasure = (Treasure)currentRoom;
                    ArrayList<Agent> receivers = new ArrayList<>();
                    
                    for (Agent explorerWhoStay : this.getExplorersWhoStay())
                        receivers.add(explorerWhoStay);

                    roomOfTreasure.share(receivers);
                }
                else if (currentRoom instanceof Hazard)
                {
                    Hazard roomOfHazard = (Hazard)currentRoom;

                    for (int i = 0; i < this.path.size() - 1; i++)
                    {
                        if (roomOfHazard.equals(this.path.get(i)))
                        {
                            for (Agent explorerWhoStay : this.getExplorersWhoStay())
                                explorerWhoStay.flee();
                            this.lastOccurredHazard = roomOfHazard;
                            System.out.println("\u001B[31m*** " + roomOfHazard.name().toUpperCase() + " HAPPENED! ***\u001B[0m");
                            break;
                        }
                    }
                }
                
                // Hint
                System.out.println(this.path);
                for (Agent explorerWhoStay : this.getExplorersWhoStay())
                    System.out.println("explorer " + explorerWhoStay.getType() + " owns " + explorerWhoStay.getGems() + " gem(s).");
                System.out.println("--- asking everyone STAY or GO.");

                ArrayList<Agent> explorersWhoChooseToGo = new ArrayList<>();
                HashMap<Agent, Thread> actionOrder = new HashMap<>();
                
                for (Agent explorerWhoStay : this.getExplorersWhoStay())
                {
                    Thread action = new Thread(() -> explorerWhoStay.act(this.path));
                    action.start();
                    actionOrder.put(explorerWhoStay, action);
                }
                
                try
                {
                    for (Map.Entry<Agent, Thread> actionSet : actionOrder.entrySet())
                    {
                        actionSet.getValue().join();
                        if (!actionSet.getKey().isInExploring())
                            explorersWhoChooseToGo.add(actionSet.getKey());
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                
                for (Card room : this.path)
                    if (room instanceof Treasure)
                        ((Treasure)room).share(explorersWhoChooseToGo);

                // Hint
                System.out.println(this.getExplorersWhoStay() + " want(s) to keep exploring.");
                
                try
                {
                    Thread.sleep(500);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            while (this.isSomeoneExploring());
            
            System.out.println("\nround " + (currentRound + 1) + " ended!");
            
            for (Agent explorer : this.explorers)
                explorer.storeGemsIntoTent();
            
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        
        System.out.println("\ngame over! final result:");
        for (Agent explorer : this.explorers)
        {
            System.out.print("explorer " + explorer.getType() + ": ");
            System.out.printf("\u001B[32m%2d\u001B[0m + ", explorer.getGemsInsideTent());
            System.out.printf("\u001B[33m%2d\u001B[0m", explorer.total() - explorer.getGemsInsideTent());
            System.out.printf(" = [%2d]\n", explorer.total());
        }
    }

    private void initializeCards()
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
        
        this.artifactsInTomb.add(new Artifact(0, 5));
        this.artifactsInTomb.add(new Artifact(1, 7));
        this.artifactsInTomb.add(new Artifact(2, 8));
        this.artifactsInTomb.add(new Artifact(3, 10));
        this.artifactsInTomb.add(new Artifact(4, 12));
    }
    
    private void reset()
    {
        for (Agent explorer : this.explorers)
            explorer.setInExploring(true);
        
        this.deck.addAll(this.path);
        this.path.clear();
        
        Iterator<Card> deckChecker = this.deck.iterator();
        
        while (deckChecker.hasNext())
        {
            Card room = deckChecker.next();
            
            if (room instanceof Gemstone)
                ((Gemstone)room).resetValue();
            else if (room instanceof Artifact)
                deckChecker.remove();
            else if (this.lastOccurredHazard != null && room == this.lastOccurredHazard)
                deckChecker.remove();
        }
  
        this.lastOccurredHazard = null;
        this.deck.add(this.artifactsInTomb.remove(0));
        this.shuffleDeck();
    }

    private void shuffleDeck()
    {
        Collections.shuffle(this.deck, new Random(5));
    }

    private void revealNextRoom()
    {
        this.path.add(this.deck.remove(0));
    }
    
    private ArrayList<Agent> getExplorersWhoStay()
    {
        ArrayList<Agent> explorersWhoStay = new ArrayList<>();
        
        for (Agent explorer : this.explorers)
            if (explorer.isInExploring())
                explorersWhoStay.add(explorer);
                
        return explorersWhoStay;
    }

    private boolean isSomeoneExploring()
    {
        boolean find = false;
        
        for (Agent explorer : this.explorers)
            find |= explorer.isInExploring();
            
        return find;
    }
       
    private Agent[] findWinners()
    {
        int maxScore = 0;
        ArrayList<Agent> winners = new ArrayList<>();
        
        for (Agent people : this.explorers)
            if (people.total() > maxScore)
                maxScore = people.total();    
         for(int i = 0 ; i < this.explorers.size() ; i++)
         {
            if (maxScore == this.explorers.get(i).total())
                winners.add(this.explorers.get(i));
         }
        return winners.toArray(new Agent[winners.size()]);
    }
}
