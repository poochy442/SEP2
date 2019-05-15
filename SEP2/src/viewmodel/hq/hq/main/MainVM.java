package viewmodel.hq.hq.main;

import model.IDataModel;
import view.hq.ViewHandler;

public class MainVM {
    private IDataModel dataModel;
    private ViewHandler viewHandler;

    public MainVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.dataModel = dataModel;
    }

    public void openEmployeeMainHQView() {
        viewHandler.openEmployeeMainHQView();
    }

    public void openInventoryWHView()
    {
        viewHandler.openInventoryWHView();
    }

    public void openEmployeeWHView()
    {
        viewHandler.openEmployeeWHView();
    }

    public void openMainView() {viewHandler.openMainView();
    }
}
