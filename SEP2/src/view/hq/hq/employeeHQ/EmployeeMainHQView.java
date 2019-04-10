package view.hq.hq.employeeHQ;

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
import viewmodel.hq.hq.employee.EmployeeMainHQVM;

public class EmployeeMainHQView {
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

    private EmployeeMainHQVM employeeMainHQVM;

    public EmployeeMainHQView() {

    }

    public void init(EmployeeMainHQVM employeeMainHQVM)
    {
        this.employeeMainHQVM = employeeMainHQVM;
        employeeTable.setItems(employeeMainHQVM.getEmployees());
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        iDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    @FXML
    void onAddEmployeeClicked(ActionEvent event) {
        employeeMainHQVM.openEmployeeAddView();
    }

    @FXML
    void onHomeClicked(ActionEvent event) {
        employeeMainHQVM.openMainView();
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
