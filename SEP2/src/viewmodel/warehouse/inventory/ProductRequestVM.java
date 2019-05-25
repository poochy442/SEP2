package viewmodel.warehouse.inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.IDataModel;
import model.ProductRequest;
import view.warehouse.ViewHandler;

import java.beans.PropertyChangeEvent;

public class ProductRequestVM {
    private IDataModel dataModel;
    private ObservableList<ProductRequest> productRequests;
    private ViewHandler viewHandler;

    public ProductRequestVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        productRequests = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
        dataModel.addListener("SendProductRequest", this::sendProductRequest);
    }

    private void sendProductRequest(PropertyChangeEvent evt) {
        productRequests.add((ProductRequest) evt.getNewValue());
    }

    public ObservableList<ProductRequest> getProductRequests() {
        return productRequests;
    }

    public void openMainView() {
        viewHandler.openMainView();
    }

    public void openEmployeeMainView() {
        viewHandler.openEmployeeMainView();
    }

    public void openInventoryMainView() {
        viewHandler.openInventoryMainView();
    }

    public void sendProductRequest() {

        dataModel.sendProductRequest();
    }
}
