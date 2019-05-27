package viewmodel.hq.controlWarehouse.inventoryWarehouse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.IDataModel;
import model.StockItem;
import view.hq.ViewHandler;

import java.beans.PropertyChangeEvent;

/**
 * The viewmodel Class for the Warehouse Inventory view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class InventoryWHVM { //This class is to display inventoryStockList from warehouse
    private ObservableList<StockItem> stockItems;
    private ViewHandler viewHandler;
    private IDataModel dataModel;

    /**
     * Creates an InventoryWHVM with the specified information
     * @param dataModel The {@link model.DataModel} to be used.
     * @param viewHandler The {@link view.hq.ViewHandler} to be used.
     */
    public InventoryWHVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.dataModel = dataModel;
        stockItems = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
        dataModel.addListener("NewItemFromServer", this::addStockItemToClient);
        dataModel.addListener("NewItemFromUser",this::addStockItemToClient);
    }

    private void addStockItemToClient(PropertyChangeEvent evt) {
        stockItems.add((StockItem) evt.getNewValue());
    }
    /**
     * Gets the {@link StockItem}s stored.
     * @return The {@link StockItem}s stored.
     */
    public ObservableList<StockItem> getStockItems() {
        return stockItems;
    }

    /**
     * This method opens the main view.
     */
    public void openMainView() {

    }

    /**
     * This method opens the Warehouse Employee view.
     */
    public void openEmployeeWHView() {
        viewHandler.openEmployeeMainWHView();
    }

    /**
     * This method opens the main Headquarters view.
     */
    public void openMainHQView() {viewHandler.openMainHQView();
    }

    /**
     * This method opens the Warehouse main Inventory view,
     */
    public void openInventoryMainWHView() {
    }

    /**
     * This method removes a {@link StockItem}.
     * @param stockItem The {@link StockItem} to be removed.
     */
    public void removeStockItem(StockItem stockItem) {
        dataModel.removeStockItemHQ(stockItem);
    }
}
