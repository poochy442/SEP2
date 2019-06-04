package view.retailer.delivery;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DeliveryList;
import viewmodel.retailer.delivery.DeliveryMainVM;

/**
 * The view Class for the main Employee view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class DeliveryMainView {

    @FXML
    private TableView<DeliveryList> deliveryListTableView;

    @FXML
    private TableColumn<String, DeliveryList> nameCol;

    @FXML
    private AnchorPane anchorPane;

    private DeliveryMainVM deliveryMainVM;

    private DeliveryList selectedDeliveryList;

    /**
     * Creates an EmployeeMainView.
     */
    public DeliveryMainView() {

    }

    /**
     * An init method instantiating all the required fields.
     *
     * @param deliveryMainVM the {@link DeliveryMainVM} viewmodel to be used.
     */
    public void init(DeliveryMainVM deliveryMainVM) {
        this.deliveryMainVM = deliveryMainVM;
        deliveryListTableView.setItems(deliveryMainVM.getDeliveryLists());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
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
    void onDashboardClicked(ActionEvent event) {
        deliveryMainVM.openMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        deliveryMainVM.openInventoryView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        deliveryMainVM.openEmployeeMainView();
    }


    @FXML
    void onProductRequestClicked(ActionEvent event) {
        deliveryMainVM.openProductRequestView();
    }

    @FXML
    void onDeliveryClicked(ActionEvent event) {
        deliveryMainVM.openDeliveryClicked();
    }

    @FXML
    void onSalesClicked(ActionEvent event) {
        deliveryMainVM.openSalesView();
    }
}