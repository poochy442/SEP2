package view.warehouse.employee;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viewmodel.warehouse.employee.EmployeeAddVM;

public class EmployeeAddView {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField iDField;

    @FXML
    private AnchorPane anchorPane;

    private EmployeeAddVM employeeAddVM;

    public EmployeeAddView()
    {

    }

    public void init(EmployeeAddVM employeeAddVM)
    {
        this.employeeAddVM = employeeAddVM;
        firstNameField.textProperty().bindBidirectional(employeeAddVM.firstNameProperty());
        lastNameField.textProperty().bindBidirectional(employeeAddVM.lastNameProperty());
        iDField.textProperty().bindBidirectional(employeeAddVM.IDProperty());
        
    }


    @FXML
    void onAddClicked(ActionEvent event) {
        employeeAddVM.addEmployee();
        employeeAddVM.confirmation();
    }

    @FXML
    void onBackClicked(ActionEvent event) {
        employeeAddVM.goBack();
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
        employeeAddVM.openMainView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        employeeAddVM.openEmployeeView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        employeeAddVM.openInventoryView();
    }
}
