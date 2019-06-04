package model;

/**
 * This class is used for storing a request for a {@link StockItem} from a client to the server
 *
 * @author Kenneth Jensen
 * @author Florin Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class Request {

    /**
     * The {@link StockItem} to be requested
     */
    StockItem requestItem;
    /**
     * The quantity to request
     */
    int quantity;
    /**
     * The current quantity of the item
     */
    int currentQty;

    /**
     * Creates a Request with the given parameters
     * @param requestItem The {@link StockItem} to request
     * @param quantity The quantity to request
     * @param currentQty The current quantity of the item
     */
    public Request(StockItem requestItem, int quantity, int currentQty){
        this.requestItem = requestItem;
        this.quantity = quantity;
        this.currentQty = currentQty;
    }

    /**
     * Gets the requested {@link StockItem}
     * @return The requested {@link StockItem}
     */
    public StockItem getRequestItem() {
        return requestItem;
    }

    /**
     * Gets the requested quantity
     * @return The requested quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the current quantity
     * @return The current quantity
     */
    public int getCurrentQty() {
        return currentQty;
    }
}
