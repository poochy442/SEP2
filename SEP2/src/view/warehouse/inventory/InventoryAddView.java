package view.warehouse.inventory;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    private CheckBox canExpireCheckBox;

    @FXML
    private DatePicker expiryDatePicker;

    @FXML
    private TextField minStockField;

    @FXML
    private TextField maxStockField;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label errorPriceLabel;

    @FXML
    private Label errorQuantityLabel;

    @FXML
    private Label errorMinStockLabel;

    @FXML
    private Label errorMaxStockLabel;

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
        canExpireCheckBox.selectedProperty().bindBidirectional(inventoryAddVM.canExpireProperty());
        expiryDatePicker.valueProperty().bindBidirectional(inventoryAddVM.getExpiryDate());
        minStockField.textProperty().bindBidirectional(inventoryAddVM.minStockProperty());
        maxStockField.textProperty().bindBidirectional(inventoryAddVM.maxStockProperty());

    }
    @FXML
    void onAddClicked(ActionEvent event) {
        boolean one, two, three, four = false;
        if(!inventoryAddVM.onlyNumbersPrice())
        {
            errorPriceLabel.setVisible(true);
            one = false;
        }
        else
        {
            errorPriceLabel.setVisible(false);
            one = true;
        }

        if(!inventoryAddVM.onlyNumbersQuantity())
        {
            errorQuantityLabel.setVisible(true);
            two = false;
        }
        else
        {
            errorQuantityLabel.setVisible(false);
            two = true;
        }

        if(!inventoryAddVM.onlyNumbersMaxStock())
        {
            errorMaxStockLabel.setVisible(true);
            three = false;
        }
        else
        {
            errorMaxStockLabel.setVisible(false);
            three = true;
        }

        if(!inventoryAddVM.onlyNumbersMinStock())
        {
            errorMinStockLabel.setVisible(true);
            four = false;
        }
        else
        {
            errorMinStockLabel.setVisible(false);
            four = true;
        }

        if(one && two && three && four)
        {
            inventoryAddVM.addStockItem();
            inventoryAddVM.confirmation();
        }
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
        } //TODO: Violating MVVM pattern?
    }
}
