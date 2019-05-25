package viewmodel.warehouse.inventory;

import javafx.beans.property.*;
import javafx.scene.control.Alert;
import model.IDataModel;
import model.StockItem;
import view.warehouse.ViewHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class InventoryAddVM {
    private StringProperty name;
    private StringProperty quantity;
    private StringProperty price;
    private StringProperty id;
    private BooleanProperty canExpire;
    private ObjectProperty<LocalDate> expiryDate;
    private Date date;
    private StringProperty minStock;
    private StringProperty maxStock;
    private IDataModel dataModel;
    private ViewHandler viewHandler;

    public InventoryAddVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        name = new SimpleStringProperty();
        quantity = new SimpleStringProperty();
        price = new SimpleStringProperty();
        id = new SimpleStringProperty();
        canExpire = new SimpleBooleanProperty();
        expiryDate = new SimpleObjectProperty<>();
        minStock = new SimpleStringProperty();
        maxStock = new SimpleStringProperty();

        date = new Date(0, 0, 0);
    }

    public void dateConverter() throws ParseException // Here it is important
    {
        if (expiryDate.get() != null) {
            LocalDate localDate = expiryDate.get();
            int day = localDate.getDayOfMonth();
            int month = localDate.getMonthValue();
            int year = localDate.getYear();
            date = new SimpleDateFormat("MM/dd/yyyy").parse("" + month + "/" + day + "/" + year);
        } else {
            date = null;
        }

    }

    public void addStockItem() {
        try {
            dateConverter();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StockItem si = new StockItem(name.getValue(), id.getValue(), Integer.parseInt(quantity.getValue()), Integer.parseInt(price.getValue()), canExpire.getValue(), date, Integer.parseInt(minStock.getValue()), Integer.parseInt(maxStock.getValue())); // Here it is important
        dataModel.addItemFromUser(si);

        //Resetting fields in view
        name.setValue("");
        quantity.setValue("");
        price.setValue("");
        id.setValue("");
        canExpire.set(false);
        expiryDate.setValue(null);
        minStock.setValue("");
        maxStock.setValue("");

    }


    public void goBack() {
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

    public BooleanProperty canExpireProperty() {
        return canExpire;
    }

    public ObjectProperty<LocalDate> getExpiryDate() {
        return expiryDate;
    }

    public void openMainView() {
        viewHandler.openMainView();
    }

    public void openInventoryView() {
        viewHandler.openInventoryMainView();
    }

    public void openEmployeeView() {
        viewHandler.openEmployeeMainView();
    }

    public StringProperty minStockProperty() {
        return minStock;
    }

    public StringProperty maxStockProperty() {
        return maxStock;
    }


    public void confirmation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Employee has been added");
        alert.setContentText("Press ok to continue");
        alert.showAndWait();
    }
}
