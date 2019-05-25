package view.warehouse.employee;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Employee;
import viewmodel.warehouse.employee.EmployeeMainVM;

public class EmployeeMainView {

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<String, Employee> firstNameCol;

    @FXML
    private TableColumn<String, Employee> lastNameCol;

    @FXML
    private TableColumn<String, Employee> iDCol;

    @FXML TableColumn<String, Employee> departmentIDCol;

    @FXML
    private AnchorPane anchorPane;

    private EmployeeMainVM employeeMainVM;

    private Employee selectedEmployee;

    public EmployeeMainView()
    {

    }

    public void init(EmployeeMainVM employeeMainVM)
    {
        this.employeeMainVM = employeeMainVM;
        employeeTable.setItems(employeeMainVM.getEmployees());
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        iDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        departmentIDCol.setCellValueFactory(new PropertyValueFactory<>("departmentID"));
    }

    @FXML
    void onCloseClicked(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void onMinimizeClicked(MouseEvent event) {
        Stage stage = (Stage)anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        employeeMainVM.openMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        employeeMainVM.openInventoryView();
    }

    @FXML
    void onAddEmployeeClicked(ActionEvent event) {
        employeeMainVM.openEmployeeAddView();
    }

    @FXML
    void onRemoveEmployeeClicked(ActionEvent event) {
        selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + " ?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to delete the employee with ID: " + selectedEmployee.getId() + "?");
        alert.setContentText("Press ok to continue");
        alert.showAndWait();

        if(alert.getResult() == ButtonType.YES)
        {
            employeeTable.getItems().remove(selectedEmployee);
            employeeMainVM.removeEmployee(selectedEmployee);
        }

        //TODO: Should we make it in VM?
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {

    }

    @FXML
    void onEditEmployeeClicked(ActionEvent event) {

    }
}
