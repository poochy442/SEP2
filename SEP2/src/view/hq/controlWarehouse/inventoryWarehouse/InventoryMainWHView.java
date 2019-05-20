package view.hq.controlWarehouse.inventoryWarehouse;

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
import viewmodel.hq.controlWarehouse.inventoryWarehouse.InventoryWHVM;

import java.time.LocalDate;

public class InventoryMainWHView {
    @FXML
    private TableView<StockItem> stockItemTable;

    @FXML
    private TableColumn<String, StockItem> nameCol;

    @FXML
    private TableColumn<String, StockItem> iDCol;

    @FXML
    private TableColumn<Integer, StockItem> quantityCol;

    @FXML
    private TableColumn<Integer, StockItem> priceCol;

    @FXML
    private TableColumn<Boolean, StockItem> canExpireCol;

    @FXML
    private TableColumn<LocalDate, StockItem> expiryDateCol;

    @FXML
    private TableColumn<Integer, StockItem> minStockCol;

    @FXML
    private TableColumn<Integer, StockItem> maxStockCol;

    @FXML
    private AnchorPane anchorPane;

    private InventoryWHVM inventoryWHVM;

    public InventoryMainWHView()
    {

    }

    public void init(InventoryWHVM inventoryWHVM)
    {
        this.inventoryWHVM = inventoryWHVM;
        stockItemTable.setItems(inventoryWHVM.getStockItems());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        iDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        canExpireCol.setCellValueFactory(new PropertyValueFactory<>("canExpire")); //TODO: Can expire weird
        expiryDateCol.setCellValueFactory(new PropertyValueFactory<>("expiryDate")); //TODO: Expiry date weird
        minStockCol.setCellValueFactory(new PropertyValueFactory<>("minStock"));
        maxStockCol.setCellValueFactory(new PropertyValueFactory<>("maxStock"));
    }

    @FXML
    void onAddItemStockClicked(ActionEvent event) {

    }

    @FXML
    void onCloseClicked(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        inventoryWHVM.openMainView();
    }

    @FXML
    void onEditItemStockClicked(ActionEvent event) {

    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        inventoryWHVM.openEmployeeWHView();
    }

    @FXML
    void onHQClicked(MouseEvent event) {
        inventoryWHVM.openMainHQView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        inventoryWHVM.openInventoryMainWHView();
    }

    @FXML
    void onMinimizeClicked(MouseEvent event) {
        Stage stage = (Stage)anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onRemoveItemStockClicked(ActionEvent event) {

    }

    @FXML
    void onRetailerClicked(MouseEvent event) {

    }
}
