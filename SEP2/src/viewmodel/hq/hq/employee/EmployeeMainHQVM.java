package viewmodel.hq.hq.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.IDataModel;
import view.hq.ViewHandler;

import java.beans.PropertyChangeEvent;

public class EmployeeMainHQVM { //This class is to display employeeList of HQ
    private IDataModel dataModel;
    private ObservableList<Employee> employees;
    private ViewHandler viewHandler;

    public EmployeeMainHQVM(IDataModel dataModel, ViewHandler viewHandler)
    {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        employees = FXCollections.observableArrayList();
        dataModel.addListener("NewEmployeeAddedFromClient", this::addEmployeeToClient);
    }

    private void addEmployeeToClient(PropertyChangeEvent evt) {
        employees.add((Employee) evt.getNewValue());
    }

    public ObservableList<Employee> getEmployees() {
        return employees;
    }

    public void openMainView() {
        viewHandler.openMainHQView();
    }

    public void openEmployeeAddView()
    {
        viewHandler.openEmployeeAddHQView();
    }

    public void openInventoryMainWHView() { viewHandler.openInventoryWHView();
    }
}
