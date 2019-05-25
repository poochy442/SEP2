package viewmodel.hq.hq.employee;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import model.Employee;
import model.IDataModel;
import view.hq.ViewHandler;

public class EmployeeAddHQVM { //This class is for adding employees to EmployeeList of HQ
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty ID;

    private ViewHandler viewHandler;
    private IDataModel dataModel;

    public EmployeeAddHQVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        firstName = new SimpleStringProperty();
        lastName = new SimpleStringProperty();
        ID = new SimpleStringProperty();
    }

    public void addEmployee() {
        Employee e = new Employee(firstName.getValue(), lastName.getValue(), ID.getValue(), "HQ");
        dataModel.addEmployeeFromUser(e);
        //dataModel.addEmployeeFromServer(e);
        firstName.setValue("");
        lastName.setValue("");
        ID.setValue("");
    }

    public void goBack() {
        viewHandler.openEmployeeMainHQView();
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

    public void openMainView() {
        viewHandler.openMainHQView();
    }

    public void openEmployeeMainHQ() {
        viewHandler.openEmployeeMainHQView();
    }

    public void openInventoryWHView() {
        viewHandler.openInventoryWHView();
    }

    public void confirmation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Employee has been added");
        alert.setContentText("Press ok to continue");
        alert.showAndWait();

    }
}
