package view.warehouse.inventory;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.StockItem;
import viewmodel.warehouse.inventory.ProductRequestVM;

public class ProductRequestView {

    @FXML
    private TableView<StockItem> productRequestTable;

    @FXML
    private TableColumn<String, StockItem> nameCol;

    @FXML
    private TableColumn<String, StockItem> iDCol;

    @FXML
    private TableColumn<Integer, StockItem> quantityCol;

    @FXML
    private AnchorPane anchorPane;

    private ProductRequestVM productRequestVM;

    public ProductRequestView()
    {

    }


    public void init(ProductRequestVM productRequestVM) {
        this.productRequestVM = productRequestVM;
      //  productRequestTable.setItems(productRequestVM.getStockItems()); //TODO: Finish
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        iDCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        productRequestTable.setRowFactory( tv -> {
            TableRow<StockItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {

                }
            });
            return row ;
        });
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        productRequestVM.openMainView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        productRequestVM.openEmployeeMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        productRequestVM.openInventoryMainView();
    }

    @FXML
    void onSendRequestClicked(ActionEvent event) {
        productRequestVM.sendProductRequest();
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
