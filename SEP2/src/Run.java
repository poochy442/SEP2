import javafx.application.Application;
import javafx.stage.Stage;
import model.DataModel;
import model.IDataModel;
import view.warehouse.ViewHandler;
import viewmodel.warehouse.ViewModelProvider;

public class Run extends Application {


    public static void main(String[] args) {
        Application.launch(Run.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        IDataModel im = new DataModel();
        ViewModelProvider vmp = new ViewModelProvider(im);
        ViewHandler vh = new ViewHandler(stage, vmp);
        vh.start();

    }
}

