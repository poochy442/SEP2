package view.hq.controlWarehouse.employeeWarehouse;

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
import viewmodel.hq.controlWarehouse.employeeWarehouse.EmployeeWHVM;

public class EmployeeMainWHView {
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

    private EmployeeWHVM employeeWHVM;

    public EmployeeMainWHView()
    {

    }

    public void init(EmployeeWHVM employeeWHVM)
    {
        this.employeeWHVM = employeeWHVM;
        employeeTable.setItems(employeeWHVM.getEmployees());
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        iDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    @FXML
    void onHomeClicked(ActionEvent event) {
        employeeWHVM.openMainView();
    }

    @FXML
    void onInventoryWHClicked(ActionEvent event) {
        employeeWHVM.openInventoryWHView();
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
}
