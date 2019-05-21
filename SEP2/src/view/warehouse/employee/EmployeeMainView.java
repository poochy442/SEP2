package view.warehouse.employee;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    @FXML
    private AnchorPane anchorPane;

    private EmployeeMainVM employeeMainVM;

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

    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {

    }

    @FXML
    void onEditEmployeeClicked(ActionEvent event) {

    }
}
