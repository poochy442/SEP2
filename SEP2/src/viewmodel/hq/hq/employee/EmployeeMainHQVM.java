package viewmodel.hq.hq.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.IDataModel;
import view.hq.ViewHandler;

import java.beans.PropertyChangeEvent;

/**
 * The viewmodel class for the Headquarters main Employee view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class EmployeeMainHQVM { //This class is to display employeeList of HQ
    private IDataModel dataModel;
    private ObservableList<Employee> employees;
    private ViewHandler viewHandler;

    /**
     * Creates an EmployeeMainHQVM with the specified information and adds a listener for adding {@link Employee}s.
     * @param dataModel The {@link model.DataModel} to be used.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public EmployeeMainHQVM(IDataModel dataModel, ViewHandler viewHandler)
    {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        employees = FXCollections.observableArrayList();
        dataModel.addListener("NewEmployeeFromUser", this::addEmployeeToClient);
        dataModel.addListener("NewEmployeeFromServer",this::addEmployeeToClient);
    }

    /**
     * This method is called when the Listener added in {@link EmployeeMainHQVM#EmployeeMainHQVM(IDataModel, ViewHandler)} is triggered,
     * and adds an Employee to the List.
     * @param evt The {@link PropertyChangeEvent} that caused this method to be called.
     */
    private void addEmployeeToClient(PropertyChangeEvent evt) {
        employees.add((Employee) evt.getNewValue());
    }

    /**
     * Gets the {@link Employee}s stored in the List.
     * @return The {@link Employee}s stored in the List.
     */
    public ObservableList<Employee> getEmployees() {
        return employees;
    }

    /**
     * This method opens the main view.
     */
    public void openMainView() {
        viewHandler.openMainHQView();
    }

    /**
     * This method opens the add Employee view.
     */
    public void openEmployeeAddView()
    {
        viewHandler.openEmployeeAddHQView();
    }

    /**
     * This method opens the Waraehouse main Inventory view.
     */
    public void openInventoryMainWHView() { viewHandler.openInventoryMainWHView();
    }

    /**
     * Removes an {@link Employee} from the List.
     * @param e The {@link Employee} to be removed.
     */
    public void removeEmployee(Employee e) {dataModel.removeEmployeeHQ(e);
    }

    public void openInventoryMainHQView() {viewHandler.openInventoryMainHQView();
    }
}
