package viewmodel.warehouse.employee;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Employee;
import model.IDataModel;
import view.warehouse.ViewHandler;

public class EmployeeAddVM {
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty ID;

    private IDataModel dataModel;
    private ViewHandler viewHandler;

    public EmployeeAddVM(IDataModel dataModel, ViewHandler viewHandler)
    {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        firstName = new SimpleStringProperty();
        lastName = new SimpleStringProperty();
        ID = new SimpleStringProperty();
    }

    public void addEmployee()
    {
        Employee e = new Employee(firstName.getValue(), lastName.getValue(), ID.getValue(), "WH");
        dataModel.addEmployeeToClient(e);
        //dataModel.addEmployeeToServer(e);
        firstName.setValue("");
        lastName.setValue("");
        ID.setValue("");
        //TODO TESTING VIEW
        dataModel.refresh();
    }

    public void goBack()
    {
        viewHandler.openEmployeeMainView();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty IDProperty() {
        return ID;
    }
}
