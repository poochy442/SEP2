package viewmodel.warehouse;

import javafx.stage.Stage;
import model.IDataModel;
import view.warehouse.ViewHandler;
import viewmodel.warehouse.employee.EmployeeAddVM;
import viewmodel.warehouse.employee.EmployeeMainVM;
import viewmodel.warehouse.inventory.InventoryAddVM;
import viewmodel.warehouse.inventory.InventoryMainVM;
import viewmodel.warehouse.request.ProductRequestVM;
import viewmodel.warehouse.main.MainVM;

/**
 * The viewmodel Provider for the Warehouse
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class ViewModelProvider {
    private IDataModel dataModel;
    private ViewHandler viewHandler;
    private MainVM mainVM;
    private InventoryMainVM inventoryMainVM;
    private InventoryAddVM inventoryAddVM;
    private EmployeeMainVM employeeMainVM;
    private EmployeeAddVM employeeAddVM;
    private ProductRequestVM productRequestVM;

    /**
     * Creates a ViewModelProvider with the specified information and instantiates all the required fields.
     * @param dataModel The {@link model.DataModel} to be used.
     */
    public ViewModelProvider(IDataModel dataModel) {
        this.dataModel = dataModel;
        viewHandler = new ViewHandler(new Stage(), this);
        mainVM = new MainVM(dataModel, viewHandler);
        inventoryAddVM = new InventoryAddVM(dataModel, viewHandler);
        inventoryMainVM = new InventoryMainVM(dataModel, viewHandler);
        employeeMainVM = new EmployeeMainVM(dataModel, viewHandler);
        employeeAddVM = new EmployeeAddVM(dataModel, viewHandler);
        productRequestVM = new ProductRequestVM(dataModel, viewHandler);
    }

    /**
     * Gets the main viewmodel.
     * @return The main viewmodel.
     */
    public MainVM getMainVM() {
        return mainVM;
    }

    /**
     * Gets the main Inventory viewmodel.
     * @return The main Inventory viewmodel.
     */
    public InventoryMainVM getInventoryMainVM() {
        return inventoryMainVM;
    }

    /**
     * Gets the main Employee viewmodel
     * @return The main Employee viewmodel
     */
    public EmployeeMainVM getEmployeeMainVM() {
        return employeeMainVM;
    }

    /**
     * Gets the add Employee viewmodel.
     * @return The add Employee viewmodel.
     */
    public EmployeeAddVM getEmployeeAddVM() {
        return employeeAddVM;
    }

    /**
     * Gets the add Inventory viewmodel.
     * @return The add Inventory viewmodel.
     */
    public InventoryAddVM getInventoryAddVM() {
        return inventoryAddVM;
    }

    /**
     * Gets the Product Request viewmodel.
     * @return
     */
    public ProductRequestVM getProductRequestVM() {return productRequestVM;}

    /**
     * Instantiates all the viewmodels, to make sure they aren't null.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public void instantiateViewModels(ViewHandler viewHandler) {
        employeeMainVM = new EmployeeMainVM(dataModel, viewHandler);
        employeeAddVM = new EmployeeAddVM(dataModel, viewHandler);
        mainVM = new MainVM(dataModel, viewHandler);
        inventoryMainVM = new InventoryMainVM(dataModel, viewHandler);
        inventoryAddVM = new InventoryAddVM(dataModel, viewHandler);
        productRequestVM = new ProductRequestVM(dataModel, viewHandler);
    }


}
