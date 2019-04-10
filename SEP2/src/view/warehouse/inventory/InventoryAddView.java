package view.warehouse.inventory;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private TextField canExpireField;

    @FXML
    private DatePicker datePicker;

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
        canExpireField.textProperty().bindBidirectional(inventoryAddVM.canExpireProperty()); // TODO: BINDING canExpire weird
        datePicker.valueProperty().bindBidirectional(inventoryAddVM.getExpiryDate()); //TODO: BINDING datePicker weird

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
}