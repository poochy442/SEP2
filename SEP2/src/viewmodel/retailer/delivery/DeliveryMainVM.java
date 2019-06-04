package viewmodel.retailer.delivery;

import javafx.collections.FXCollections;
import model.IDataModel;
import view.retailer.ViewHandler;

import java.beans.PropertyChangeEvent;

/**
 * This is the viewmodel Class for the main Employye view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class DeliveryMainVM {
    private IDataModel dataModel;
    private ObservableList<DeliveryList> deliveryLists;
    private ViewHandler viewHandler;

    /**
     * Creates an EmployeeMainVM with the specified information and adds the needed Listeners.
     *
     * @param dataModel   The {@link model.DataModel} to be used.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public DeliveryMainVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        deliveryLists = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
        dataModel.addListener("", this::addDeliveryListToClient);

    }

    private void addDeliveryListToClient(PropertyChangeEvent evt) {
    }

    /**
     * The method called by the Listener added at {@link this#DeliveryMainVM(IDataModel, ViewHandler)} (IDataModel, ViewHandler)}.
     * This method adds the Employee stored in the {@link PropertyChangeEvent} data.
     *
     * @param The {@link PropertyChangeEvent} that caused the Listener to call this method.
     *            <p>
     *            /**
     *            Gets the {@link DeliveryList}s stored.
     * @return The {@link DeliveryList}s stored.
     */
    public ObservableList<DeliveryList> getDeliveryLists() {
        return deliveryLists;
    }

    /**
     * This method opens the main view.
     */
    public void openMainView() {
        viewHandler.openMainView();
    }

    /**
     * This method opens the main Inventory view.
     */
    public void openInventoryView() {
        viewHandler.openInventoryMainView();
    }

    /**
     * This method opens the add Employee view.
     */
    public void openEmployeeAddView() {
        viewHandler.openEmployeeAddView();
    }

    /**
     * This method removes an {@link DeliveryList} from the List.
     *
     * @param e The {@link DeliveryList} to be removed.
     */

    public void openProductRequestView() {
        viewHandler.openProductRequestView();
    }

    public void openDeliveryClicked() {
        viewHandler.openDeliveryMainView();
    }

    public void openSalesView() {
        viewHandler.openSalesView();
    }

    public void openEmployeeMainView() {
        viewHandler.openEmployeeMainView();
    }
}