package viewmodel.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.IDataModel;
import view.ViewHandler;

import java.beans.PropertyChangeEvent;

public class EmployeeMainVM {
    private IDataModel dataModel;
    private ObservableList<Employee> employees;
    private ViewHandler viewHandler;

    public EmployeeMainVM(IDataModel dataModel, ViewHandler viewHandler)
    {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        employees = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
        dataModel.addListener("NewEmployeeAddedFromClient", this::addEmployeeToClient);
    }

    private void addEmployeeToClient(PropertyChangeEvent evt) {
        employees.add((Employee) evt.getNewValue());
    }

    public ObservableList<Employee> getEmployees()
    {
        return employees;
    }

    public void openMainView()
    {
        viewHandler.openMainView();
    }

    public void openInventoryView()
    {
        viewHandler.openInventoryMainView();
    }

    public void openEmployeeAddView()
    {
        viewHandler.openEmployeeAddView();
    }
}
