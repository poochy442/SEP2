package viewmodel.retailer.inventory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.IDataModel;
import model.ProductRequest;
import model.StockItem;
import view.retailer.ViewHandler;

import java.beans.PropertyChangeEvent;

/**
 * The viewmodel Class for the main Inventory view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class InventoryMainVM {
    private IDataModel dataModel;
    private ObservableList<StockItem> stockItems;
    private ViewHandler viewHandler;
    private StringProperty requestQty;

    /**
     * Creates an InventoryMainVM with the specified information and adds the required
     * Listeners.
     * @param dataModel The {@link model.DataModel} to be used.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public InventoryMainVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        stockItems = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
        dataModel.addListener("NewItemFromServer", this::addStockItemToClient);
        dataModel.addListener("NewItemFromUser",this::addStockItemToClient);
        requestQty = new SimpleStringProperty();
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
        viewHandler.openMainView();
    }

    /**
     * Gets the quantity for Request {@link StringProperty} stored.
     * @return The quantity for Request {@link StringProperty} stored.
     */
    public StringProperty requestQtyProperty() {
        return requestQty;
    }

    /**
     * This method opens the add Inventory view.
     */
    public void openInventoryAddView() {
        viewHandler.openInventoryAddView();
    }

    /**
     * This method opens the main Employee view.
     */
    public void openEmployeeMainView() {
        viewHandler.openEmployeeMainView();
    }

    /**
     * This method opens the Product Request view.
     */
    public void openProductRequestView() { viewHandler.openProductRequestView();
    }

    /**
     * Removes a {@link StockItem} from the List.
     * @param stockItem The {@link StockItem} to be removed.
     */
    public void removeStockItem(StockItem stockItem) {
        dataModel.removeStockItemWH(stockItem);
    }

    public void addToProductRequest(StockItem stockItem,String qty) {
        int quantity=Integer.parseInt(qty);
        ProductRequest productRequest = new ProductRequest(stockItem,quantity);
        dataModel.addToProductRequest(productRequest,true);
    }

    public void addToSales(StockItem selectedItem, String text) {
        StockItem x = selectedItem.copy();
        x.setQuantity(Integer.parseInt(text));
        dataModel.addToSales(x,true);
    }

    public void openSalesView() {
        viewHandler.openSalesView();
    }

    public void openInventoryMainView() {viewHandler.openInventoryMainView();
    }

    public void openDeliveryView() {
    }

    public void addProductRequestToList(StockItem selectedItem) {
        ProductRequest productRequest = new ProductRequest(selectedItem, Integer.parseInt(requestQty.getValue()));
        dataModel.addToProductRequest(productRequest, false);
        requestQty.setValue("");
    }

    public boolean onlyNumbersQuantity() {
        return dataModel.onlyNumbers(requestQty.getValue());
    }

}
