package viewmodel.retailer.delivery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Delivery;
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

public class DeliveryItemsVM {
    private IDataModel dataModel;
    private ObservableList<Delivery> deliveries;
    private ViewHandler viewHandler;

    /**
     * Creates an EmployeeMainVM with the specified information and adds the needed Listeners.
     * @param dataModel The {@link model.DataModel} to be used.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public DeliveryItemsVM(IDataModel dataModel, ViewHandler viewHandler)
    {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        deliveries = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
        dataModel.addListener("", this::addDeliveryToClient);

    }

    private void addDeliveryToClient(PropertyChangeEvent propertyChangeEvent) {
    }

    /**
     * The method called by the Listener added at {@link this#DeliveryItemsVM(IDataModel, ViewHandler)} (IDataModel, ViewHandler)}.
     * This method adds the Employee stored in the {@link PropertyChangeEvent} data.
     * @param {@link PropertyChangeEvent} that caused the Listener to call this method.

    /**
     * Gets the {@link Delivery}s stored.
     * @return The {@link Delivery}s stored.
     */
    public ObservableList<Delivery> getDeliveries()
    {
        return deliveries;
    }

    /**
     * This method opens the main view.
     */
    public void openMainView()
    {
        viewHandler.openMainView();
    }

    /**
     * This method opens the main Inventory view.
     */
    public void openInventoryView()
    {
        viewHandler.openInventoryMainView();
    }

    /**
     * This method opens the add Employee view.
     */
    public void openEmployeeAddView()
    {
        viewHandler.openEmployeeAddView();
    }

    /**
     * This method removes an {@link Delivery} from the List.
     * @param The {@link Delivery} to be removed.
     */

    public void openProductRequestView() {viewHandler.openProductRequestView();
    }

    public void openDeliveryClicked() {
        viewHandler.openDeliveryMainView();
    }

    public void openSalesView() {viewHandler.openSalesView();
    }

    public void openEmployeeMainView() {viewHandler.openEmployeeMainView();
    }
}
