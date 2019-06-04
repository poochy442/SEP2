package model;

public class Delivery {
    private String requestid,requestedFrom,status;
    private ProductRequestList productList;

    public Delivery(String requestid, String requestedFrom, String status) {
        this.requestid = requestid;
        this.requestedFrom = requestedFrom;
        this.status = status;
        productList = new ProductRequestList();
    }


    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public String getRequestedFrom() {
        return requestedFrom;
    }

    public void setRequestedFrom(String requestedFrom) {
        this.requestedFrom = requestedFrom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProductRequestList getProductList() {
        return productList;
    }

    public void setProductList(ProductRequestList productList) {
        this.productList = productList;
    }
    public void addToDelivery(ProductRequest productRequest)
    {
        productList.addRequestToList(productRequest);
    }
}
