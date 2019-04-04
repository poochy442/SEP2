package Sep2Project.view.Main;

import Sep2Project.viewmodel.MainVM;
import javafx.event.ActionEvent;


public class MainView {
    private MainVM mainVM;


    public MainView() {

    }

    public void seeEmployee(ActionEvent actionEvent) {
        mainVM.seeEmployee();

    }
    public void init (MainVM mainVM)
    {
       this.mainVM=mainVM;
    }
}


