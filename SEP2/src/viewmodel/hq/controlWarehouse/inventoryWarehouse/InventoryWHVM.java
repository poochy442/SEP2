package viewmodel.hq.controlWarehouse.inventoryWarehouse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.StockItem;
import view.hq.ViewHandler;

public class InventoryWHVM { //This class is to display inventoryStockList from warehouse
    private ObservableList<StockItem> stockItems;
    private ViewHandler viewHandler;

    public InventoryWHVM(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        stockItems = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
    }

    public ObservableList<StockItem> getStockItems() {
        return stockItems;
    }


    public void openMainView() {
        viewHandler.openMainView();
    }

    public void openEmployeeWHView() {
        viewHandler.openEmployeeWHView();
    }
}
