package u21g;
import java.time.LocalDateTime;

/**
 * This class provides support for the generation and parsing of tickets.
 */
public class Ticket {

    final int userID;
    final int itemID;
    final boolean item_is_game;

    final int days_to_rent = 7;
    private LocalDateTime checkout_date;
    private LocalDateTime return_date;

    private boolean active_ticket;

    /**
     *
     * @param userID the User ID of the user creating the ticket
     * @param itemID the Item ID of the item being checked in or out
     * @param item_is_game a boolean value to track if an item is a game or a piece of equipment
     * @param active_ticket a boolean value to track if a ticket is a out ticket (active) or an in ticket (inactive)
     */
    public Ticket(int userID, int itemID, boolean item_is_game, boolean active_ticket){
        if( userID < 0 ){
            throw new IllegalArgumentException("User ID cannot be less than 0");
        }
        this.userID = userID;
        if( itemID < 0 ){
            throw new IllegalArgumentException("Item ID cannot be less than 0");
        }
        this.itemID = itemID;

        this.item_is_game = item_is_game;
        this.active_ticket = active_ticket;
        if( active_ticket ) {
            checkout_date = LocalDateTime.now();
            return_date = checkout_date.plusDays(days_to_rent);
        }
        else{
            return_date = LocalDateTime.now();
        }
    }

    /**
     * Simple getter function for the item ID
     * @return an int of the item ID
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Simple getter function for the user ID
     * @return an int of the user ID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Gets whether the item is a game or not
     * @return true if item is a game, false otherwise
     */
    public boolean isItem_is_game() {
        return item_is_game;
    }

    /**
     * Gets whether a ticket is active or not
     * @return true if the ticket is active, false otherwise
     */
    public boolean isActive_ticket() {
        return active_ticket;
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