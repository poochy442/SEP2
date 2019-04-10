package viewmodel.hq.controlWarehouse.employeeWarehouse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import view.hq.ViewHandler;

public class EmployeeWHVM { //This class is to display employeeList from warehouse
    private ObservableList<Employee> employees;
    private ViewHandler viewHandler;

    public EmployeeWHVM(ViewHandler viewHandler)
    {
        this.viewHandler = viewHandler;
        employees = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
    }

    public ObservableList<Employee> getEmployees()
    {
        return employees;
    }

    public void openMainView()
    {
        viewHandler.openMainView();
    }

    public void openInventoryWHView()
    {
        viewHandler.openInventoryWHView();
    }


}
