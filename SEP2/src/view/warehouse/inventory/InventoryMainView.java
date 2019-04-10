package view.warehouse.inventory;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.StockItem;
import viewmodel.warehouse.inventory.InventoryMainVM;

import java.time.LocalDate;

public class InventoryMainView {

    @FXML
    private TableView<StockItem> stockItemTable;

    @FXML
    private TableColumn<String, StockItem> nameCol;

    @FXML
    private TableColumn<Integer, StockItem> quantityCol;

    @FXML
    private TableColumn<Integer, StockItem> priceCol;

    @FXML
    private TableColumn<String, StockItem> iDCol;

    @FXML
    private TableColumn<Boolean, StockItem> canExpireCol; // TODO: canExpireCol is weird

    @FXML
    private TableColumn<LocalDate, StockItem> expiryDateCol; //TODO: expiryDateCol is weird

    @FXML
    private AnchorPane anchorPane;

    private InventoryMainVM inventoryMainVM;

    public InventoryMainView()
    {

    }

    public void init(InventoryMainVM inventoryMainVM)
    {
        this.inventoryMainVM = inventoryMainVM;
        stockItemTable.setItems(inventoryMainVM.getStockItems());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        iDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        canExpireCol.setCellValueFactory(new PropertyValueFactory<>("canExpire")); //TODO: Can expire weird
        expiryDateCol.setCellValueFactory(new PropertyValueFactory<>("expiryDate")); //TODO: Expiry date weird
    }

    @FXML
    void onAddItemStockClicked(ActionEvent event) {
        inventoryMainVM.openInventoryAddView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        inventoryMainVM.openEmployeeMainView();
    }

    @FXML
    void onHomeClicked(ActionEvent event) {
        inventoryMainVM.openMainView();
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
