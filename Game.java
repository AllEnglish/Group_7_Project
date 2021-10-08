import java.util.ArrayList;
import java.util.Collections;

public class Game implements Runnable
{
    protected final int round;
    protected ArrayList<Agent> explorers;
    protected ArrayList<Card> deck;
    // protected ArrayList<Card> fold = new ArrayList<>();
    protected ArrayList<Card> path;

    public Game(int round)
    {
        this.round = round;
        this.explorers = new ArrayList<>();
        this.deck = new ArrayList<>();
        this.path = new ArrayList<>();
        
        // just for testing
        this.explorers.add(new Computer(0));
        this.explorers.add(new Computer(1));
        this.explorers.add(new Computer(2));
        this.explorers.add(new Computer(3));
        this.explorers.add(new Computer(4));
        this.explorers.add(new Computer(5));
        this.explorers.add(new Computer(6));
        this.explorers.add(new Computer(7));
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
            this.deckShuffle();
            
            do
            {     
                this.revealNextRoom();
                System.out.println();
                
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
                            System.out.println("[GG] " + roomOfHazard.name() + " happened!");
                            break;
                        }
                    }
                }
                
                // hint dialog ------------------------
                System.out.println(this.path);
                for (Agent explorerWhoStay : this.getExplorersWhoStay())
                    System.out.println("explorer " + explorerWhoStay.getType() + " owns " + explorerWhoStay.getGems() + " gem(s).");
                System.out.println("[?] asking everyone stay or leave.");
                // end of hint ------------------------

                /********************/
                
                ArrayList<Agent> explorersWhoChooseToGo = new ArrayList<>();
                ArrayList<Agent> explorersWhoStay = this.getExplorersWhoStay();
                
                Thread[] ts = new Thread[explorersWhoStay.size()];
                for (int idx = 0; idx < explorersWhoStay.size(); idx++)
                {
                    final int finalIdx = idx;
                    ts[idx] = new Thread(() -> explorersWhoStay.get(finalIdx).act());
                    ts[idx].start();
                }
                
                try
                {
                    for (Thread t : ts)
                        t.join();
                }
                catch (InterruptedException e) {}
                    
                for (Agent explorerWhoStay : explorersWhoStay)
                {
                    // explorerWhoStay.act();
                    if (!explorerWhoStay.isInExploring())
                        explorersWhoChooseToGo.add(explorerWhoStay);
                }
                
                /********************/
                  
                for (Card room : this.path)
                    if (room instanceof Treasure)
                        ((Treasure)room).share(explorersWhoChooseToGo);

                // hint dialog ------------------------
                if (this.isSomeoneExploring())
                {
                    for (Agent explorerWhoStay : this.getExplorersWhoStay())
                        System.out.print(explorerWhoStay.getType() + " ");
                    System.out.print("want to keep exploring.");
                    System.out.println();
                    try
                    {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e) {}
                }
                // end of hint ------------------------
            }
            while (this.isSomeoneExploring());

            break;
        }
        
        System.out.println();
        System.out.println("game over! final result:");
        for (Agent explorer : this.explorers)
        {
            explorer.storeGemsIntoTent();
            System.out.print("explorer " + explorer.getType() + ": ");
            System.out.print("\u001B[32m" + explorer.getGemsInsideTent() + "\u001B[0m + ");
            System.out.println("\u001B[33m" + (explorer.total() - explorer.getGemsInsideTent()) + "\u001B[0m");
        }
        for (Agent winner : this.findWinners())
        {
            System.out.print(winner.getType() + " ");
        }
        System.out.println("are winner!");
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
        
        this.deck.add(new Artifact(0, 5));
        this.deck.add(new Artifact(1, 7));
        this.deck.add(new Artifact(2, 8));
        this.deck.add(new Artifact(3, 10));
        this.deck.add(new Artifact(4, 12));
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
    
    public ArrayList<Agent> getExplorersWhoStay()
    {
        ArrayList<Agent> explorersWhoStay = new ArrayList<>();
        
        for (Agent explorer : this.explorers)
            if (explorer.isInExploring())
                explorersWhoStay.add(explorer);
                
        return explorersWhoStay;
    }

    public boolean isSomeoneExploring()
    {
        boolean find = false;
        
        for (Agent explorer : this.explorers)
            find |= explorer.isInExploring();
            
        return find;
    }
       
    public Agent[] findWinners()
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
