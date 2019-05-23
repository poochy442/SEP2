package viewmodel.warehouse.main;

import model.IDataModel;
import view.warehouse.ViewHandler;

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

    public void openRequestMainView()
    {
        viewHandler.openProductRequestView();
    }
}
