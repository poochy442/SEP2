package view.warehouse.inventory;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viewmodel.warehouse.inventory.InventoryAddVM;

public class InventoryAddView {
    @FXML
    private TextField nameField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField iDField;

    @FXML
    private CheckBox canExpireCheckBox;

    @FXML
    private DatePicker expiryDatePicker;

    @FXML
    private TextField minStockField;

    @FXML
    private TextField maxStockField;

    @FXML
    private AnchorPane anchorPane;
    private InventoryAddVM inventoryAddVM;

    public InventoryAddView()
    {

    }

    public void init(InventoryAddVM inventoryAddVM)
    {
        this.inventoryAddVM = inventoryAddVM;
        nameField.textProperty().bindBidirectional(inventoryAddVM.nameProperty());
        quantityField.textProperty().bindBidirectional(inventoryAddVM.quantityProperty());
        priceField.textProperty().bindBidirectional(inventoryAddVM.priceProperty());
        iDField.textProperty().bindBidirectional(inventoryAddVM.IDProperty());
        canExpireCheckBox.selectedProperty().bindBidirectional(inventoryAddVM.canExpireProperty()); // TODO: BINDING canExpire weird
        expiryDatePicker.valueProperty().bindBidirectional(inventoryAddVM.getExpiryDate()); //TODO: BINDING datePicker weird
        minStockField.textProperty().bindBidirectional(inventoryAddVM.minStockProperty());
        maxStockField.textProperty().bindBidirectional(inventoryAddVM.maxStockProperty());

    }
    @FXML
    void onAddClicked(ActionEvent event) {
        inventoryAddVM.addStockItem();
    }

    @FXML
    void onBackClicked(ActionEvent event) {
        inventoryAddVM.goBack();
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
    void onEmployeeClicked(ActionEvent event) {
        inventoryAddVM.openEmployeeView();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        inventoryAddVM.openMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        inventoryAddVM.openInventoryView();
    }

    @FXML
    void canExpireClicked(ActionEvent event)
    {
        if(canExpireCheckBox.isSelected())
        {
            expiryDatePicker.setDisable(false);
        }
        else
        {
            expiryDatePicker.setDisable(true);
            expiryDatePicker.setValue(null);
        }
    }
}
