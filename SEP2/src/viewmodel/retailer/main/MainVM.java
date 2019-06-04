package viewmodel.retailer.main;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.IDataModel;
import view.retailer.ViewHandler;

import java.beans.PropertyChangeEvent;

/**
 * The main viewmodel Class for the Warehouse.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class MainVM {
    private IDataModel dataModel;
    private ViewHandler viewHandler;
    private IntegerProperty costOfGoods, profit, operationalCost;

    /**
     * Creates a MainVM with the specified information
     *
     * @param dataModel   The {@link model.DataModel} to be used.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public MainVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        costOfGoods = new SimpleIntegerProperty();
        profit = new SimpleIntegerProperty();
        operationalCost = new SimpleIntegerProperty();
        dataModel.addListener("PieChartUpdate",this::pieChartUpdate);
    }

    private void pieChartUpdate(PropertyChangeEvent evt) {
        Platform.runLater(()-> {
            int[] vals = (int[])evt.getNewValue();
            costOfGoods.set(costOfGoods.getValue() + vals[0]);
            profit.set(profit.getValue() + vals[1]);
            operationalCost.set(operationalCost.getValue() + vals[2]);
        });
    }


    /**
     * This method opens the main Employee view.
     */
    public void openEmployeeMainView() {
        viewHandler.openEmployeeMainView();
    }

    /**
     * This method opens the main Inventory view.
     */
    public void openInventoryMainView()
    {
        viewHandler.openInventoryMainView();
    }

    /**
     * This method opens the Product Request view.
     */
    public void openRequestMainView()
    {
        viewHandler.openProductRequestView();
    }

    public void openSalesMainView() {
        viewHandler.openSalesView();
    }

    public void openDeliveryView() {viewHandler.openDeliveryMainView();
    }

    public IntegerProperty costOfGoodsProperty() {
        return costOfGoods;
    }

    public IntegerProperty profitProperty() {
        return profit;
    }


    public IntegerProperty operationalCostProperty() {
        return operationalCost;
    }
}
