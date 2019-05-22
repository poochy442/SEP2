package view.hq.hq.employeeHQ;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viewmodel.hq.hq.employee.EmployeeAddHQVM;

public class EmployeeAddHQView {
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField iDField;

    @FXML
    private AnchorPane anchorPane;

    private EmployeeAddHQVM employeeAddHQVM;

    public EmployeeAddHQView() {

    }

    public void init(EmployeeAddHQVM employeeAddHQVM)
    {
        this.employeeAddHQVM = employeeAddHQVM;
        firstNameField.textProperty().bindBidirectional(employeeAddHQVM.firstNameProperty());
        lastNameField.textProperty().bindBidirectional(employeeAddHQVM.lastNameProperty());
        iDField.textProperty().bindBidirectional(employeeAddHQVM.IDProperty());
    }

    @FXML
    void onAddClicked(ActionEvent event) {
        employeeAddHQVM.addEmployee();
    }

    @FXML
    void onBackClicked(ActionEvent event) {
        employeeAddHQVM.goBack();
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
        employeeAddHQVM.openMainView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        employeeAddHQVM.openEmployeeMainHQ();
    }

    @FXML
    void onRetailerClicked(MouseEvent event) {

    }

    @FXML
    void onWarehouseClicked(MouseEvent event) {
        employeeAddHQVM.openInventoryWHView();
    }

}
