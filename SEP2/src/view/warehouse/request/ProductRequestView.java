package view.warehouse.request;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.ProductRequest;
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
    private TableColumn<ProductRequest, String> nameCol;

    @FXML
    private TableColumn<ProductRequest, String> iDCol;

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
        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductRequest, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductRequest, String> param) {
                return new SimpleStringProperty(param.getValue().getName());
            }
        });
        iDCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductRequest, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductRequest, String> param) {
                return new SimpleStringProperty(param.getValue().getID());
            }
        });
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

    @FXML void onProductRequestClicked(ActionEvent event)
    {
        productRequestVM.openProductRequestView();
    }

    @FXML void onDeliveryClicked(ActionEvent event)
    {

    }
}
