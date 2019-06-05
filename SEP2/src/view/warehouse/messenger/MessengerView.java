package view.warehouse.messenger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Message;
import viewmodel.warehouse.messenger.MessengerVM;

/**
 * The main view Class for the Warehouse.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class MessengerView {
    private MessengerVM messengerVM;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ListView<Message> chatBox;


    @FXML
    private TextArea txtMsg;

    /**
     * Creates a MainView.
     */
    public MessengerView() {

    }

    /**
     * An init method instantiating all the required fields.
     *
     * @param messengerVM The {@link MessengerVM} viewmodel to be used.
     */
    public void init(MessengerVM messengerVM) {
        this.messengerVM = messengerVM;
        chatBox.setItems(messengerVM.getMessages());
        txtMsg.textProperty().bindBidirectional(messengerVM.txtMsgProperty());
        ;
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        messengerVM.openEmployeeMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        messengerVM.openInventoryMainView();

    }

    @FXML
    void onProductRequestClicked(ActionEvent event) {
        messengerVM.openProductRequestView();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        messengerVM.openMainView();
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
    void onDeliveryClicked(ActionEvent event) {
        messengerVM.openDeliveryMainView();
    }

    @FXML void onSendMessageClicked(ActionEvent event)
    {
        messengerVM.sendMessage();
    }
}
