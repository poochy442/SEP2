package viewmodel.inventory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.IDataModel;
import model.StockItem;
import view.ViewHandler;

public class InventoryAddVM {
    private StringProperty name;
    private StringProperty quantity;
    private StringProperty price;
    private StringProperty ID;

    private IDataModel dataModel;
    private ViewHandler viewHandler;

    public InventoryAddVM(IDataModel dataModel, ViewHandler viewHandler)
    {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        name = new SimpleStringProperty();
        quantity = new SimpleStringProperty();
        price = new SimpleStringProperty();
        ID = new SimpleStringProperty();
    }

    public void addStockItem()
    {
        StockItem si = new StockItem(name.getValue(), Integer.parseInt(quantity.getValue()), Integer.parseInt(price.getValue()), ID.getValue());
        dataModel.addItemToClient(si);
//        dataModel.addItemToServer(si);
        name.setValue("");
        quantity.setValue("");
        price.setValue("");
        ID.setValue("");
    }

    public void goBack()
    {
        viewHandler.openInventoryMainView();
    }

    public StringProperty nameProperty() {
        return name;
    }


    public StringProperty quantityProperty() {
        return quantity;
    }

    public StringProperty priceProperty() {
        return price;
    }

    public StringProperty IDProperty() {
        return ID;
    }
}
