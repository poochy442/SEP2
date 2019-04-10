package view.warehouse.main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viewmodel.warehouse.main.MainVM;

public class MainView {
    private MainVM mainVM;
    @FXML
    private AnchorPane anchorPane;

    public MainView() {

    }

    public void init (MainVM mainVM)
    {
       this.mainVM=mainVM;
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        mainVM.openEmployeeMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) { mainVM.openInventoryMainView();

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


