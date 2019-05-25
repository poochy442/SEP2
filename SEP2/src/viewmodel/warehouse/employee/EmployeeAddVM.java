package viewmodel.warehouse.employee;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
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
        Employee e = new Employee(firstName.getValue(), lastName.getValue(), ID.getValue(),"WH");
        dataModel.addEmployeeFromUser(e);
        //dataModel.addEmployeeFromServer(e);
        firstName.setValue("");
        lastName.setValue("");
        ID.setValue("");
    }

    public void goBack()
    {
        viewHandler.openEmployeeMainView();
    }

    public void openMainView()
    {
        viewHandler.openMainView();
    }

    public void openInventoryView()
    {
        viewHandler.openInventoryMainView();
    }

    public void openEmployeeView()
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


    public void confirmation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Employee has been added");
        alert.setContentText("Press ok to continue");
        alert.showAndWait();
    }
}
