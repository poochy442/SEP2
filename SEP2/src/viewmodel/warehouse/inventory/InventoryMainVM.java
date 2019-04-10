package viewmodel.warehouse.inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.IDataModel;
import model.StockItem;
import view.warehouse.ViewHandler;

import java.beans.PropertyChangeEvent;

public class InventoryMainVM {
    private IDataModel dataModel;
    private ObservableList<StockItem> stockItems;
    private ViewHandler viewHandler;

    public InventoryMainVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        stockItems = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
        dataModel.addListener("NewItemAddedFromClient", this::addStockItemToClient);
    }

    private void addStockItemToClient(PropertyChangeEvent evt) {
        stockItems.add((StockItem) evt.getNewValue());
    }

    public ObservableList<StockItem> getStockItems() {
        return stockItems;
    }


    public void openMainView() {
        viewHandler.openMainView();
    }

    public void openInventoryAddView() {
        viewHandler.openInventoryAddView();
    }

    public void openEmployeeMainView() {
        viewHandler.openEmployeeMainView();
    }
}
