package viewmodel.hq.controlWarehouse.employeeWarehouse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.IDataModel;
import view.hq.ViewHandler;

public class EmployeeWHVM { //This class is to display employeeList from warehouse
    private ObservableList<Employee> employees;
    private ViewHandler viewHandler;
    private IDataModel dataModel;

    public EmployeeWHVM(IDataModel dataModel, ViewHandler viewHandler)
    {
        this.viewHandler = viewHandler;
        this.dataModel = dataModel;
        employees = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
    }

    public ObservableList<Employee> getEmployees()
    {
        return employees;
    }

    public void openMainHQView()
    {
        viewHandler.openMainHQView();
    }

    public void openInventoryWHView()
    {
        viewHandler.openInventoryWHView();
    }


    public void openEmployeeMainWHView() {viewHandler.openEmployeeWHView();
    }

    public void removeEmployee(Employee e) {
        dataModel.removeEmployeeHQ(e);
    }
}
