package viewmodel.hq;

import model.IDataModel;
import view.hq.ViewHandler;
import viewmodel.hq.controlWarehouse.employeeWarehouse.EmployeeWHVM;
import viewmodel.hq.controlWarehouse.inventoryWarehouse.InventoryWHVM;
import viewmodel.hq.hq.employee.EmployeeAddHQVM;
import viewmodel.hq.hq.employee.EmployeeMainHQVM;
import viewmodel.hq.hq.inventory.InventoryAddHQVM;
import viewmodel.hq.hq.inventory.InventoryMainHQVM;
import viewmodel.hq.hq.main.MainVM;

/**
 * The viewmodel provider for the Headquarters and it is responsible for passing the requested
 * viewmodel to any Class requesting a viewmodel.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class ViewModelProvider {

    private IDataModel dataModel;
    private MainVM mainVM;
    private EmployeeMainHQVM employeeMainHQVM;
    private EmployeeAddHQVM employeeAddHQVM;
    private InventoryWHVM inventoryWHVM;
    private EmployeeWHVM employeeWHVM;
    private InventoryMainHQVM inventoryMainHQVM;
    private InventoryAddHQVM inventoryAddHQVM;

    /**
     * Creates a ViewModelProvider with the specified information
     * @param dataModel The {@link model.DataModel} to be used.
     */
    public ViewModelProvider(IDataModel dataModel)
    {
        this.dataModel = dataModel;
    }

    /**
     * This method gets the main viewmodel.
     * @return The main viewmodel.
     */
    public MainVM getMainVM() {
        return mainVM;
    }

    /**
     * This method gets the Headquarters main Employee viewmodel.
     * @return The Headquarters main Employee viewmodel.
     */
    public EmployeeMainHQVM getEmployeeMainHQVM() {
        return employeeMainHQVM;
    }

    /**
     * This method gets the Headquarters add Inventory viewmodel.
     * @return The Headquarters add Inventory viewmodel.
     */
    public InventoryAddHQVM getInventoryAddHQVM() {
        return inventoryAddHQVM;
    }

    /**
     * This method gets the Headquarters main Inventory viewmodel.
     * @return The Headquarters main Inventory viewmodel.
     */
    public InventoryMainHQVM getInventoryMainHQVM() {
        return inventoryMainHQVM;
    }

    /**
     * This method gets the Headquarters add Employee viewmodel.
     * @return The Headquarters add Employee viewmodel.
     */
    public EmployeeAddHQVM getEmployeeAddHQVM() {
        return employeeAddHQVM;
    }

    /**
     * This method gets the Warehouse Inventory viewmodel.
     * @return The Warehouse Inventory viewmodel.
     */
    public InventoryWHVM getInventoryWHVM() {
        return inventoryWHVM;
    }

    /**
     * This method gets the Warehouse Employee viewmodel.
     * @return The Warehouse Employee viewmodel.
     */
    public EmployeeWHVM getEmployeeWHVM() {
        return employeeWHVM;
    }

    /**
     * This method instantiates all the viewmode.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public void instantiateViewModels(ViewHandler viewHandler) {
        employeeAddHQVM = new EmployeeAddHQVM(dataModel, viewHandler);
        employeeMainHQVM = new EmployeeMainHQVM(dataModel, viewHandler);
        mainVM = new MainVM(dataModel, viewHandler);
        inventoryWHVM = new InventoryWHVM(dataModel,viewHandler);
        employeeWHVM = new EmployeeWHVM(dataModel,viewHandler);
        inventoryAddHQVM = new InventoryAddHQVM(dataModel, viewHandler);
        inventoryMainHQVM = new InventoryMainHQVM(dataModel, viewHandler);
    }
}
