package view;


import viewmodel.ViewModelProvider;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewHandler {
    //    private Stage stage;
    private ViewModelProvider viewModelProvider;


    public ViewHandler(Stage stage, ViewModelProvider viewModelProvider) {
//        this.stage = stage;
       this.viewModelProvider=viewModelProvider;
    }

    public void start() throws Exception {
        openView("Mainview");

    }

    public void openView(String viewToOpen) throws IOException {
        Scene scene = null;
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        Stage localStage = new Stage();
        if ("MainView".equals(viewToOpen))
        {
            loader.setLocation(getClass().getResource("Main.fxml"));
            root=loader.load();
            MainView view = loader.getController();
            view.init(viewModelProvider.getMainVM());
            localStage.setTitle("Main View");
        }



        scene = new Scene(root);
        localStage.setScene(scene);
        localStage.show();
    }
}
