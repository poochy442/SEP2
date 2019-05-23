package model;

public class ProductRequest {
    private int quantity;
    private String productId;
    private StockItem stockItem;

    public ProductRequest(StockItem stockItem,int quantity) {
        this.productId=stockItem.getId();
        this.quantity = quantity;
        this.stockItem=stockItem;
    }


    public int getQuantity() {
        return quantity;
    }

    public String getProductId() {
        return productId;
    }

    public StockItem getStockItem ()
    {
        return stockItem;
    }
}
