package viewmodel.hq.controlWarehouse.inventoryWarehouse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.IDataModel;
import model.StockItem;
import view.hq.ViewHandler;

public class InventoryWHVM { //This class is to display inventoryStockList from warehouse
    private ObservableList<StockItem> stockItems;
    private ViewHandler viewHandler;
    private IDataModel dataModel;

    public InventoryWHVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.dataModel = dataModel;
        stockItems = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
    }

    public ObservableList<StockItem> getStockItems() {
        return stockItems;
    }


    public void openMainView() {

    }

    public void openEmployeeWHView() {
        viewHandler.openEmployeeWHView();
    }

    public void openMainHQView() {viewHandler.openMainHQView();
    }

    public void openInventoryMainWHView() {
    }
}
