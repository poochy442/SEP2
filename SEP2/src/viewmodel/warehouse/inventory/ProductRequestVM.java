package viewmodel.warehouse.inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.IDataModel;
import model.StockItem;
import view.warehouse.ViewHandler;

import javax.swing.*;

public class ProductRequestVM {
    private IDataModel dataModel;
    private ObservableList<StockItem> stockItems;
    private ViewHandler viewHandler;

    public ProductRequestVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        stockItems = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
       // dataModel.addListener();  //TODO: Finish2
    }

    public ObservableList<StockItem> getStockItems() {
        return stockItems;
    }

    public void openMainView() {
        viewHandler.openMainView();
    }

    public void openEmployeeMainView() {
        viewHandler.openEmployeeMainView();
    }

    public void openInventoryMainView() {viewHandler.openInventoryMainView();
    }

    public void sendProductRequest() {

    }
}
