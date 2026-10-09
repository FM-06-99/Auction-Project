import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 7.0
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> listOfLots;
    // The number that will be given to the next lot entered into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        listOfLots = new ArrayList<>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        listOfLots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot aLot : listOfLots) {
            System.out.println(aLot.toString());
        }
    }
    
    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            // question 2
            boolean successful = selectedLot.bidFor(new Bid(bidder, value));
            if(successful) {
                System.out.println("The bid for lot number " +
                                   lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                Bid highestBid = selectedLot.getHighestBid();
                System.out.println("Lot number: " + lotNumber +
                                   " already has a bid of: " +
                                   highestBid.getValue());
            }
        }
    }
    // question 5: you risk getting a lot of internal errors.
    /**
     * Return the lot with the given number. Return null if a lot with this 
     * number does not exist.
     * @param lotNumber The number of the lot to return.
     * @return The lot with the given number, or null.
     */
    public Lot getLot(int lotNumber)
    {
        if((lotNumber >= 1) && (lotNumber < nextLotNumber)) {
            //question 6
            for (Lot aLot : listOfLots){
                if (aLot.getNumber() == lotNumber){
                    return aLot;
                }
            }
            System.out.println("Lot number: " + lotNumber +
                               " seem to have been removed.");
            return null;
        }
        else {
            System.out.println("Lot number: " + lotNumber +
                               " does not exist.");
            return null;
        }
    }
    // question 3
    public void close()
    {
       for(Lot aLot : listOfLots)
       {
           Bid highest = aLot.getHighestBid();
           if (highest == null){
               System.out.println("There were no bids for the lot");
           } else {
               System.out.println("The highest bid was" + highest.getValue());
               System.out.println("The bidder bid was" + highest.getBidder().getName());
           }
       }
    }
    //Question 4
    public ArrayList<Lot>getUnsold()
    {
        ArrayList<Lot> UnsoldList = new ArrayList();
        for(Lot alot : listOfLots) 
        {
            Bid highest = alot.getHighestBid();
            if (highest == null) 
            {
                UnsoldList.add(alot);
                System.out.println(UnsoldList);
            }
        }
        return UnsoldList;
    }
    // question 7
    public Lot removeLot(int number)
    {
        boolean match = false;
        Iterator<Lot> it = listOfLots.iterator();
        Lot lot = null;
        while(it.hasNext() && match == false)
        {
            lot = it.next();
            if(lot.getNumber() == number)
            {
            it.remove();
            match = true;
            System.out.println(number + "has been removed.");
            }
        }
        if (match == false)
        {
            System.out.println("Lot number: "+ number + "does not exist.");
            lot = null;
        }
        return lot;
    }
}
