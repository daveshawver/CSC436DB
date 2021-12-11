package u21g;
import java.time.LocalDateTime;

/**
 * This class provides support for the generation and parsing of tickets.
 */
public class Ticket {

    int userID;
    int copy_id;
    int ticketID;

    final int days_to_rent = 7;
    private LocalDateTime checkout_date;
    private LocalDateTime return_date;

    private boolean currentlyOut;

    /**
     *
     * @param userID the User ID of the user creating the ticket
     * @param copy_id the Item ID of the item being checked in or out
     * @param currentlyOut a boolean value to track if a ticket is a out ticket (active) or an in ticket (inactive)
     */
    public Ticket(){};

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setCopyID(int copyID) {
        this.copy_id = copyID;
    }

    public void setCurrentlyOut(boolean currentlyOut){
        this.currentlyOut = currentlyOut;
    }
    
    public void setTicketID(int ticketID){
        this.ticketID = ticketID;
    }


    public Ticket(int userID, int copy_id, boolean currentlyOut, int ticketID){
        if( userID < 0 ){
            throw new IllegalArgumentException("User ID cannot be less than 0");
        }
        this.userID = userID;
        if( copy_id < 0 ){
            throw new IllegalArgumentException("Item ID cannot be less than 0");
        }
        this.copy_id = copy_id;

        this.currentlyOut = currentlyOut;
        if( currentlyOut ) {
            checkout_date = LocalDateTime.now();
            return_date = checkout_date.plusDays(days_to_rent);
        }
        else{
            return_date = LocalDateTime.now();
        }
    }

    /**
     * Simple getter function for the ticket ID
     * @return an int of the item ID
     */
    public int getTicketID() {
        return ticketID;
    }

    public int getCopyID() {
        return copy_id;
    }

    /**
     * Simple getter function for the user ID
     * @return an int of the user ID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Gets whether a ticket is active or not
     * @return true if the ticket is active, false otherwise
     */
    public boolean isActive_ticket() {
        return currentlyOut;
    }

    /**
     * Gets the checkout date and time of a ticket
     * @return a LocalDateTime object
     */
    public LocalDateTime getCheckout_date() {
        return checkout_date;
    }

    /**
     * Gets either the scheduled return date for an active ticket, or the actual return date of a closed ticket
     * @return a LocalDateTime object regardless of the status of the ticket in question
     */
    public LocalDateTime getReturn_date() {
        return return_date;
    }
}