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

    public StockItem get(int index){
        return stockItemList.get(index);
    }

    public int size(){
        return stockItemList.size();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof StockItemList)){
            return false;
        }
        StockItemList other = (StockItemList) obj;
        if(size() != other.size()){
            return false;
        }
        for(int i = 0; i < size(); i++){
            if(!(get(i).equals(other.get(i)))){
                return false;
            }
        }
        return true;
    }
}
