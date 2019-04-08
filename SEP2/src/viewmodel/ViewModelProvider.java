package viewmodel;

import model.IDataModel;
import view.ViewHandler;
import viewmodel.employee.EmployeeAddVM;
import viewmodel.employee.EmployeeMainVM;
import viewmodel.inventory.InventoryAddVM;
import viewmodel.inventory.InventoryMainVM;
import viewmodel.main.MainVM;

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
