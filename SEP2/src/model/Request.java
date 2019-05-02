package model;

public class Request {

    StockItem requestItem;
    int quantity;

    public Request(StockItem requestItem, int quantity){
        this.requestItem = requestItem;
        this.quantity = quantity;
    }

    public StockItem getRequestItem() {
        return requestItem;
    }

    public int getQuantity() {
        return quantity;
    }
}
