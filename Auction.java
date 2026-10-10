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
            //question 2
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

    /**
     * Return the lot with the given number. Return null if a lot with this 
     * number does not exist.v
     * @param lotNumber The number of the lot to return.
     * @return The lot with the given number, or null.
     */
    public Lot getLot(int lotNumber)
    {
        //question 6
        for (Lot aLot : listOfLots){
            if (aLot.getNumber()==lotNumber){
                return aLot;
            }
        }
        return null;
    }
    
    //question 3
    public void close(){
        for (Lot aLot : listOfLots){
            Bid highest = aLot.getHighestBid();
            if (highest == null){
                System.out.println("No bidder for this lot");
            }else{
                System.out.println("The bidder is " + highest.getBidder().getName());
                System.out.println("The value is " + highest.getValue());
            }
        }
    }
    
    //question 4
    public ArrayList<Lot> getUnsold(){
        ArrayList<Lot> unsold = new ArrayList<>();
        for (Lot aLot : listOfLots){
            Bid highest = aLot.getHighestBid();
            if (highest == null){
                unsold.add(aLot);
            }
        }
        return unsold;
    }
    
    //question 7
    public Lot removeLot(int number)
    {
        Iterator<Lot> it = listOfLots.iterator();
        while (it.hasNext()) {
            Lot aLot = it.next();
            if (aLot.getNumber() == number) {
                it.remove();
                return aLot;
            }
        }
        return null;
    }
}

