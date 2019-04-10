package view.hq.hq.main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viewmodel.hq.hq.main.MainVM;

public class MainView {
    private MainVM mainVM;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    void onEmployeeHQClicked(ActionEvent event) {
        mainVM.openEmployeeMainHQView();
    }

    @FXML
    void onEmployeeWHClicked(ActionEvent event) {
        mainVM.openEmployeeWHView();
    }

    @FXML
    void onInventoryWHClicked(ActionEvent event) {
        mainVM.openInventoryWHView();
    }

    public MainView()
    {

    }

    public void init(MainVM mainVM)
    {
        this.mainVM = mainVM;
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
