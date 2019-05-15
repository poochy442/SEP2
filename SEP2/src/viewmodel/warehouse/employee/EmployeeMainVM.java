package viewmodel.warehouse.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.EmployeeList;
import model.IDataModel;
import view.warehouse.ViewHandler;

import java.beans.PropertyChangeEvent;

public class EmployeeMainVM {
    private IDataModel dataModel;
    private ObservableList<Employee> employees;
    private ViewHandler viewHandler;

    public EmployeeMainVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        employees = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
        dataModel.addListener("NewEmployeeAddedFromClient", this::addEmployeeToClient);
        dataModel.addListener("NewEmployeeList", this::newEmployeeListListener);
        dataModel.addListener("RefreshEmployeeList",this::newEmployeeListListener);
    }

    private void newEmployeeListListener(PropertyChangeEvent propertyChangeEvent) {
        EmployeeList employeeList = (EmployeeList) propertyChangeEvent.getNewValue();
        employees = FXCollections.observableArrayList();
        for (int i = 0; i < employeeList.size(); i++) {
            employees.add(employeeList.get(i));
            System.out.println(employeeList.get(i).toString());
        }
    }

    private void addEmployeeToClient(PropertyChangeEvent evt) {
        employees.add((Employee) evt.getNewValue());
    }

    public ObservableList<Employee> getEmployees() {
        return employees;
    }

    public void openMainView() {
        viewHandler.openMainView();
    }

    public void openInventoryView() {
        viewHandler.openInventoryMainView();
    }

    public void openEmployeeAddView() {
        viewHandler.openEmployeeAddView();
    }

    public void refresshView() {
        dataModel.refreshEmployeeList();
    }
}
