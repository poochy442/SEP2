package model;

import java.util.ArrayList;

public class StockItemList {

    private ArrayList<StockItem> stockItemList;

    public StockItemList() {
        this.stockItemList = new ArrayList<>();
    }

    public void add(StockItem item){
        stockItemList.add(item);
    }

    public void remove(int index){
        stockItemList.remove(index);
    }

    public void remove(StockItem item){
        stockItemList.remove(item);
    }

    public int totalPrice(){
        int count = 0;

        for(StockItem item : stockItemList){
            count += item.getPrice();
        }

        return count;
    }

    public int totalQuantity(){
        int count = 0;

        for(StockItem item : stockItemList){
            count += item.getQuantity();
        }

        return count;
    }

}
