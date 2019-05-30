package viewmodel.hq.hq.inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.IDataModel;
import model.StockItem;
import view.hq.ViewHandler;

import java.beans.PropertyChangeEvent;

/**
 * The viewmodel Class for the main Inventory view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class InventoryMainHQVM {
    private IDataModel dataModel;
    private ObservableList<StockItem> stockItems;
    private ViewHandler viewHandler;

    /**
     * Creates an InventoryMainHQVM with the specified information and adds the required
     * Listeners.
     * @param dataModel The {@link model.DataModel} to be used.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public InventoryMainHQVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        stockItems = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
        dataModel.addListener("NewItemFromServer", this::addStockItemToClient);
        dataModel.addListener("NewItemFromUser",this::addStockItemToClient);
    }

    /**
     * Adds a stock item to the List using the information stored in the {@link PropertyChangeEvent} passed.
     * @param evt The {@link PropertyChangeEvent} that caused this method to be called.
     */
    private void addStockItemToClient(PropertyChangeEvent evt) {
        stockItems.add((StockItem) evt.getNewValue());
    }

    /**
     * Gets the {@link StockItem}s in the List.
     * @return The {@link StockItem}s in the List.
     */
    public ObservableList<StockItem> getStockItems() {
        return stockItems;
    }

    /**
     * This method opens the main view.
     */
    public void openMainView() {
        viewHandler.openMainHQView();
    }

    /**
     * This method opens the add Inventory view.
     */
    public void openInventoryAddHQView() {
        viewHandler.openInventoryAddHQView();
    }

    /**
     * This method opens the main Employee view.
     */
    public void openEmployeeMainHQView() {
        viewHandler.openEmployeeMainHQView();
    }

    /**
     * Removes a {@link StockItem} from the List.
     * @param stockItem The {@link StockItem} to be removed.
     */
    public void removeStockItem(StockItem stockItem) {
        dataModel.removeStockItemHQ(stockItem); //TODO: U can change it to one method requiring passing string from which entity it comes
    }

    public void openInventoryMainWHView() {
        viewHandler.openInventoryMainWHView();
    }

    //JOptionPane.showMessageDialog(null, "Quantity is low."); //TODO: Alert implementation
}
