package view.retailer.messenger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Cell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Message;
import viewmodel.retailer.messenger.MessengerVM;

/**
 * The main view Class for the Warehouse.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class MessengerView {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ListView<Message> chatBox;


    @FXML
    private TextArea txtMsg;

    private MessengerVM messengerVM;

    /**
     * Creates a MessengerView.
     */
    public MessengerView() {

    }

    /**
     * An init method instantiating all the required fields.
     *
     * @param messengerVM The {@link  MessengerVM} viewmodel to be used.
     */
    public void init(MessengerVM messengerVM) {
        this.messengerVM = messengerVM;
        chatBox.setItems(messengerVM.getMessages());
        txtMsg.textProperty().bindBidirectional(messengerVM.txtMsgProperty());
        if(chatBox.getItems().size() > 0){
            align(chatBox.getItems().size() - 1);
        }
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

    @FXML void onDeliveryClicked(ActionEvent event)
    {
        messengerVM.openDeliveryView();
    }

    @FXML
    void onSalesClicked(ActionEvent event) {
        messengerVM.openSalesMainView();
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
    void onSendMessageClicked(MouseEvent event) {
        messengerVM.sendMessage();
        if(chatBox.getItems().size() > 0){
            align(chatBox.getItems().size() - 1);
        }
    }

    private void align(int i) {
        Cell workingCell = null;
        try {
            workingCell = (Cell) chatBox.lookupAll(".cell").toArray()[i];
            if (chatBox.getItems().get(i).getDepartmentID().equals("HQ")) {
                workingCell.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                workingCell.setStyle("-fx-border-width: 7px 305px 7px 0px;");
                workingCell.setStyle("-fx-background-color: #F9F9F9;");
            } else {
                workingCell.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                workingCell.setStyle("-fx-border-width: 7px 5px 7px 300px;");
                workingCell.setStyle("-fx-background-color: #FFFFFF;");
            }
        } catch (RuntimeException e){
            e.getCause();
        }
    }

    private Cell getListCell(ListView list, int index) {
        Object[] cells = list.lookupAll(".cell").toArray();
        return (Cell) cells[index];
    }

}

