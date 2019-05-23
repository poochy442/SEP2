package model;

import java.util.ArrayList;

public class ProductRequestList {
    private ArrayList<ProductRequest> requestList;

    public ProductRequestList() {
        requestList = new ArrayList<>();
    }

    public void addRequestToList(ProductRequest productRequest) {
        requestList.add(productRequest);
    }

    public void removeRequestFromList(String productId) {
        for (int i = 0; i < requestList.size(); i++) {
            if (requestList.get(i).getProductId().equals(productId)) ;
            {
                requestList.remove(i);
                break;
            }

        }
    }
    public int getSize()
    {
        return requestList.size();
    }
    public ProductRequest getProductRequest( int i)
    {
        return requestList.get(i);
    }

}
