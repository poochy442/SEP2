package viewmodel.hq;

import model.IDataModel;
import view.hq.ViewHandler;
import viewmodel.hq.controlWarehouse.employeeWarehouse.EmployeeWHVM;
import viewmodel.hq.controlWarehouse.inventoryWarehouse.InventoryWHVM;
import viewmodel.hq.hq.employee.EmployeeAddHQVM;
import viewmodel.hq.hq.employee.EmployeeMainHQVM;
import viewmodel.hq.hq.main.MainVM;

public class ViewModelProvider {

    private IDataModel dataModel;
    private MainVM mainVM;
    private EmployeeMainHQVM employeeMainHQVM;
    private EmployeeAddHQVM employeeAddHQVM;
    private InventoryWHVM inventoryWHVM;
    private EmployeeWHVM employeeWHVM;

    public ViewModelProvider(IDataModel dataModel)
    {
        this.dataModel = dataModel;
    }

    public MainVM getMainVM() {
        return mainVM;
    }

    public EmployeeMainHQVM getEmployeeMainHQVM() {
        return employeeMainHQVM;
    }

    public EmployeeAddHQVM getEmployeeAddHQVM() {
        return employeeAddHQVM;
    }

    public InventoryWHVM getInventoryWHVM() {
        return inventoryWHVM;
    }

    public EmployeeWHVM getEmployeeWHVM() {
        return employeeWHVM;
    }

    public void instantiateViewModels(ViewHandler viewHandler) {
        employeeAddHQVM = new EmployeeAddHQVM(dataModel, viewHandler);
        employeeMainHQVM = new EmployeeMainHQVM(dataModel, viewHandler);
        mainVM = new MainVM(dataModel, viewHandler);
        inventoryWHVM = new InventoryWHVM(dataModel,viewHandler);
        employeeWHVM = new EmployeeWHVM(dataModel,viewHandler);
    }
}
