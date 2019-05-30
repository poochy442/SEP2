package view.warehouse.request;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ProductRequest;
import model.StockItem;
import viewmodel.warehouse.request.ProductRequestVM;

/**
 * The view Class for the Product Request view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class ProductRequestView {

    @FXML
    private TableView<ProductRequest> productRequestTable;

    @FXML
    private TableColumn<StockItem, ProductRequest> nameCol;

    @FXML
    private TableColumn<String, ProductRequest> iDCol;

    @FXML
    private TableColumn<Integer, ProductRequest> quantityCol;

    @FXML
    private AnchorPane anchorPane;

    private ProductRequestVM productRequestVM;

    /**
     * Creates a SalesView.
     */
    public ProductRequestView()
    {

    }

    /**
     * An init method instantiating all the required fields.
     * @param productRequestVM The {@link ProductRequestVM} viewmodel to be used.
     */
    public void init(ProductRequestVM productRequestVM) {
        this.productRequestVM = productRequestVM;
        productRequestTable.setItems(productRequestVM.getProductRequests());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("stockItem"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
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
