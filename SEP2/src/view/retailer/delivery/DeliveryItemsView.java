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
import model.Delivery;
import viewmodel.retailer.delivery.DeliveryItemsVM;

/**
 * The view Class for the main Employee view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class DeliveryItemsView {

    @FXML
    private TableView<Delivery> deliveryTableView;

    @FXML
    private TableColumn<String, Delivery> nameCol;

    @FXML
    private AnchorPane anchorPane;

    private DeliveryItemsVM deliveryItemsVM;

    /**
     * Creates an DeliveryMainView.
     */
    public DeliveryItemsView() {

    }

    /**
     * An init method instantiating all the required fields.
     *
     * @param deliveryItemsVM the {@link DeliveryItemsVM} viewmodel to be used.
     */
    public void init(DeliveryItemsVM deliveryItemsVM) {
        this.deliveryItemsVM = deliveryItemsVM;
        deliveryTableView.setItems(deliveryItemsVM.getDeliveries());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
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
        deliveryItemsVM.openMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        deliveryItemsVM.openInventoryView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        deliveryItemsVM.openEmployeeMainView();
    }


    @FXML
    void onProductRequestClicked(ActionEvent event) {
        deliveryItemsVM.openProductRequestView();
    }

    @FXML
    void onDeliveryClicked(ActionEvent event) {
        deliveryItemsVM.openDeliveryClicked();
    }

    @FXML
    void onSalesClicked(ActionEvent event) {
        deliveryItemsVM.openSalesView();
    }

    @FXML void onBackClicked(ActionEvent event)
    {
        deliveryItemsVM.goBack();
    }
}
