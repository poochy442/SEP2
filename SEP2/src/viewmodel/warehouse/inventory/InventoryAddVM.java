package viewmodel.warehouse.inventory;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Date;
import model.IDataModel;
import model.StockItem;
import view.warehouse.ViewHandler;

import java.time.LocalDate;

public class InventoryAddVM {
    private StringProperty name;
    private StringProperty quantity;
    private StringProperty price;
    private StringProperty id;
    private StringProperty canExpire;
    private ObjectProperty<LocalDate> expiryDate;
    private Date date;

    private IDataModel dataModel;
    private ViewHandler viewHandler;

    public InventoryAddVM(IDataModel dataModel, ViewHandler viewHandler)
    {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        name = new SimpleStringProperty();
        quantity = new SimpleStringProperty();
        price = new SimpleStringProperty();
        id = new SimpleStringProperty();
        canExpire = new SimpleStringProperty();
        expiryDate = new SimpleObjectProperty<>();

        date = new Date(0,0,0);
    }

    public void dateConverter() // Here it is important
    {
        LocalDate localDate = expiryDate.get();
        date.setDay(localDate.getDayOfMonth());
        date.setMonth(localDate.getMonth().getValue());
        date.setYear(localDate.getYear());
        System.out.println(localDate);
    }

    public void addStockItem()
    {
        dateConverter();
        StockItem si = new StockItem(name.getValue(), id.getValue(), Integer.parseInt(quantity.getValue()), Integer.parseInt(price.getValue()), Boolean.parseBoolean(canExpire.getValue()), date); // Here it is important
        dataModel.addItemToClient(si);
        System.out.println(Boolean.parseBoolean(canExpire.getValue()));
        System.out.println("Day: " + date.getDay());
        System.out.println("Month: " + date.getMonth());
        System.out.println("Year: " + date.getYear());
        System.out.println("END");
//        dataModel.addItemToServer(si);

        //To delete data from view
        name.setValue("");
        quantity.setValue("");
        price.setValue("");
        id.setValue("");
        canExpire.setValue("");
        expiryDate.setValue(null);

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
        return id;
    }

    public StringProperty canExpireProperty() {
        return canExpire;
    }

    public ObjectProperty<LocalDate> getExpiryDate() {
        return expiryDate;
    }

    public void openMainView()
    {
        viewHandler.openMainView();
    }

    public void openInventoryView()
    {
        viewHandler.openInventoryMainView();
    }

    public void openEmployeeView()
    {
        viewHandler.openEmployeeMainView();
    }
}
