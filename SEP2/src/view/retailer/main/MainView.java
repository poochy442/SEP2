package view.retailer.main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viewmodel.retailer.main.MainVM;

/**
 * The main view Class for the Warehouse
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class MainView {
    private MainVM mainVM;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Creates a MainView
     */
    public MainView() {

    }

    /**
     * An init method instantiating all the required fields
     *
     * @param mainVM The {@link MainVM} viewmodel to be used
     */
    public void init(MainVM mainVM) {
        this.mainVM = mainVM;
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        mainVM.openEmployeeMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        mainVM.openInventoryMainView();

    }

    @FXML
    void onProductRequestClicked(ActionEvent event) {
        mainVM.openRequestMainView();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {


    }

    @FXML void onDeliveryClicked(ActionEvent event)
    {
        mainVM.openDeliveryView();
    }

    @FXML
    void onSalesClicked(ActionEvent event) {
        mainVM.openSalesMainView();
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

}


