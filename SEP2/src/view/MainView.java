package view;


import javafx.event.ActionEvent;
import viewmodel.MainVM;


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
