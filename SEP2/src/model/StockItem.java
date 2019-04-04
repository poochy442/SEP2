package model;

public class StockItem {

    private String name; //TODO: add ID
    private int quantity, price;
    private boolean canExpire;
    private Date expiryDate;

    public StockItem(String name, int quantity, int price, boolean canExpire, Date expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.canExpire = canExpire;
        if(!canExpire){
            expiryDate = new Date(1,1,1111);
        } else {
            expiryDate = expiryDate;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
