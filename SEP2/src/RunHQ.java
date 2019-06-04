import javafx.application.Application;
import javafx.stage.Stage;
import model.DataModel;
import model.IDataModel;
import network.client.Client;
import view.hq.ViewHandler;
import viewmodel.hq.ViewModelProvider;

/**
 * The Run class for our headquarters {@link Application}
 */
public class RunHQ extends Application {
    public static void main(String[] args) {
        Application.launch(RunHQ.class);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        IDataModel model = new DataModel();
        ViewModelProvider vmp = new ViewModelProvider(model);
        ViewHandler vh = new ViewHandler(primaryStage, vmp);
        vh.start();
        Client client = new Client("localhost", model,"HQ");
        client.run();
    }
}
