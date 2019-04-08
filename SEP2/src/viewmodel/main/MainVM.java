package viewmodel.main;


import model.IDataModel;
import view.ViewHandler;

public class MainVM {

    private IDataModel dataModel;
    private ViewHandler viewHandler;

    public MainVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
    }

    public void openEmployeeMainView() {
        viewHandler.openEmployeeMainView();
    }

    public void openInventoryMainView()
    {
        viewHandler.openInventoryMainView();
    }
}
