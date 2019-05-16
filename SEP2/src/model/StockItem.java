package model;

public class StockItem {

    private String name, id;
    private int quantity, price;
    private boolean canExpire;
    private Date expiryDate;

    public StockItem(String name, String id, int quantity, int price, boolean canExpire, Date expiryDate) {
        this.name = name;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public boolean CanExpire() {
        return canExpire;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof StockItem)){
            return false;
        }
        StockItem other = (StockItem) obj;
        return name == other.getName() &&
                id == other.getId() &&
                quantity == other.getQuantity() &&
                price == other.getPrice() &&
                canExpire == other.CanExpire() &&
                expiryDate == other.getExpiryDate();
    }
}
