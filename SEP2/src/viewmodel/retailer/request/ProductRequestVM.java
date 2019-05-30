package viewmodel.retailer.request;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.IDataModel;
import model.ProductRequest;
import view.retailer.ViewHandler;

import java.beans.PropertyChangeEvent;

/**
 * The viewmodel class for the Product Request view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class ProductRequestVM {
    private IDataModel dataModel;
    private ObservableList<ProductRequest> productRequests;
    private ViewHandler viewHandler;

    /**
     * Creates a SalesVM with the specified information and adds the required listeners.
     *
     * @param dataModel   The {@link model.DataModel} to be used.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public ProductRequestVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        productRequests = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
    }

    /**
     * Sends a Product Request to the Server with the information in the {@link PropertyChangeEvent} passed.
     * @param evt The {@link PropertyChangeEvent} that caused this method to be called.
     */

    /**
     * Gets the {@link ProductRequest}s from the List.
     * @return The {@link ProductRequest}s from the List.
     */
    public ObservableList<ProductRequest> getProductRequests() {
        return productRequests;
    }

    /**
     * This method opens the main view.
     */
    public void openMainView() {
        viewHandler.openMainView();
    }

    /**
     * This method opens the main Employee view.
     */
    public void openEmployeeMainView() {
        viewHandler.openEmployeeMainView();
    }

    /**
     * This method opens the main Inventory view.
     */
    public void openInventoryMainView() {
        viewHandler.openInventoryMainView();
    }

    /**
     * Sends the product request to the {@link model.DataModel}.
     */
    public void sendProductRequest() {

        dataModel.sendProductRequest();
    }
}
