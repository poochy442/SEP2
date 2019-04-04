import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewHandler;
import viewmodel.ViewModelProvider;

public class Run extends Application {


    public static void main(String[] args) {
        Application.launch(Run.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ViewModelProvider mvp = new ViewModelProvider();
        ViewHandler vh = new ViewHandler(stage, mvp);
        vh.start();

    }
}

