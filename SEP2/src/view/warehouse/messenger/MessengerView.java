package view.warehouse.messenger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
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
     * Creates a MessengerView.
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
        align();
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

    @FXML
    void onSendMessageClicked(MouseEvent event) {
        messengerVM.sendMessage();
        align();
    }

    private void align() {
        for (int i = 0; i < chatBox.getItems().size(); i++) {  //TODO: Checking first element
            if (chatBox.getItems().get(i).getDepartmentID().equals("WH")) {
                getListCell(chatBox, i).setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                getListCell(chatBox, i).setStyle("-fx-border-width: 7px 305px 7px 0px;");
                getListCell(chatBox, i).setStyle("-fx-background-color: #F9F9F9;");
            } else {
                getListCell(chatBox, i).setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                getListCell(chatBox, i).setStyle("-fx-border-width: 7px 5px 7px 300px;");
                getListCell(chatBox, i).setStyle("-fx-background-color: #FFFFFF;");
            }
        }
    }

    private Cell getListCell(ListView list, int index) {
        Object[] cells = list.lookupAll(".cell").toArray();
        return (Cell) cells[index];
    }

}
