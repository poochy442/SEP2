package model;

import java.util.Date;

public class StockItem {

    private String name, id;
    private int quantity, price, minStock, maxStock;
    private boolean canExpire;
    private Date expiryDate;

    public StockItem(String name, String id, int quantity, int price, boolean canExpire, Date expiryDate, int minStock, int maxStock) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.canExpire = canExpire;
        this.expiryDate = expiryDate;
        this.minStock = minStock;
        this.maxStock = maxStock;
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

    public String getId() {return id;}

    public int getMinStock() {
        return minStock;
    }

    public int getMaxStock() {
        return maxStock;
    }

    public boolean isCanExpire() {
        return canExpire;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
}
