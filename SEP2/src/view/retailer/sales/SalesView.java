package view.retailer.sales;

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
import viewmodel.retailer.sales.SalesVM;

/**
 * The view Class for the Product Request view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class SalesView {

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

    private SalesVM salesVM;

    /**
     * Creates a SalesView.
     */
    public SalesView() {

    }

    /**
     * An init method instantiating all the required fields.
     *
     * @param salesVM The {@link SalesVM} viewmodel to be used.
     */
    public void init(SalesVM salesVM) {
        this.salesVM = salesVM;
        productRequestTable.setItems(salesVM.getProductRequests());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("stockItem"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        salesVM.openMainView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        salesVM.openEmployeeMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        salesVM.openInventoryMainView();
    }

    @FXML
    void onSendRequestClicked(ActionEvent event) {
        salesVM.sendProductRequest();
    }

    @FXML
    void onCloseClicked(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void onMinimizeClicked(MouseEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onProductRequestClicked(ActionEvent event) {
        salesVM.openProductRequest();

    }
}
