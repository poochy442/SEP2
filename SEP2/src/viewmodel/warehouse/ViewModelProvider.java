package viewmodel.warehouse;

import model.IDataModel;
import view.warehouse.ViewHandler;
import viewmodel.warehouse.employee.EmployeeAddVM;
import viewmodel.warehouse.employee.EmployeeMainVM;
import viewmodel.warehouse.inventory.InventoryAddVM;
import viewmodel.warehouse.inventory.InventoryMainVM;
import viewmodel.warehouse.main.MainVM;

public class ViewModelProvider {
    private IDataModel dataModel;
    private MainVM mainVM;
    private InventoryMainVM inventoryMainVM;
    private InventoryAddVM inventoryAddVM;
    private EmployeeMainVM employeeMainVM;
    private EmployeeAddVM employeeAddVM;


    public ViewModelProvider(IDataModel dataModel) {
        this.dataModel = dataModel;
    }

    public MainVM getMainVM() {
        return mainVM;
    }

    public InventoryMainVM getInventoryMainVM() {
        return inventoryMainVM;
    }

    public EmployeeMainVM getEmployeeMainVM() {
        return employeeMainVM;
    }

    public EmployeeAddVM getEmployeeAddVM() {
        return employeeAddVM;
    }

    public InventoryAddVM getInventoryAddVM() {
        return inventoryAddVM;
    }

    public void instantiateViewModels(ViewHandler viewHandler) {
        employeeMainVM = new EmployeeMainVM(dataModel, viewHandler);
        employeeAddVM = new EmployeeAddVM(dataModel, viewHandler);
        mainVM = new MainVM(dataModel, viewHandler);
        inventoryMainVM = new InventoryMainVM(dataModel, viewHandler);
        inventoryAddVM = new InventoryAddVM(dataModel, viewHandler);
    }


}
