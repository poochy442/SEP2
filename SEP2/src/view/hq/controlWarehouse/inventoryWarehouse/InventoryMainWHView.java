package view.hq.controlWarehouse.inventoryWarehouse;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
    void onEmployeeWHClicked(ActionEvent event) {
        inventoryWHVM.openEmployeeWHView();
    }

    @FXML
    void onHomeClicked(ActionEvent event) {
        inventoryWHVM.openMainView();
    }

}
