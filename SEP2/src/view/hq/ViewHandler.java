package view.hq;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.hq.controlWarehouse.employeeWarehouse.EmployeeMainWHView;
import view.hq.controlWarehouse.inventoryWarehouse.InventoryMainWHView;
import view.hq.hq.employeeHQ.EmployeeAddHQView;
import view.hq.hq.employeeHQ.EmployeeMainHQView;
import view.hq.hq.inventoryHQ.InventoryAddHQView;
import view.hq.hq.inventoryHQ.InventoryMainHQView;
import view.hq.hq.main.MainView;
import viewmodel.hq.ViewModelProvider;

import java.io.IOException;

/**
 * <h1>The ViewHandler Class for the Headquarters</h1>
 * This Class is responsible for opening the different views the Headquarters has access to.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class ViewHandler {
    private Stage primaryStage;
    private ViewModelProvider viewModelProvider;
    private Rectangle2D screenSize;

    /**
     * Creates a ViewHandler with the specified information.
     * @param stage The {@link Stage} to be used.
     * @param vmp The {@link ViewModelProvider} to be used.
     */
    public ViewHandler(Stage stage, ViewModelProvider vmp)
    {
        primaryStage = stage;
        primaryStage.setMaximized(true);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        screenSize = Screen.getPrimary().getVisualBounds();
        viewModelProvider = vmp;
        viewModelProvider.instantiateViewModels(this);
    }

    /**
     * The start method, to be called upon the start of our program, which will open the default view.
     */
    public void start() {
        openMainHQView();
    }

    /**
     * This method opens the Headquarterrs' main Employee view.
     */
    public void openEmployeeMainHQView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("hq/employeeHQ/EmployeeMainHQ.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        EmployeeMainHQView view = loader.getController();
        view.init(viewModelProvider.getEmployeeMainHQVM());
        primaryStage.setTitle("Employee list HQ");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * This method opens the Warehouses' inventory view.
     */
    public void openInventoryMainWHView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("controlWarehouse/inventoryWarehouse/InventoryMainWH.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        InventoryMainWHView view = loader.getController();
        view.init(viewModelProvider.getInventoryWHVM());
        primaryStage.setTitle("Stock item list WH");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This method opens the Warehouses' Employee view.
     */
    public void openEmployeeMainWHView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("controlWarehouse/employeeWarehouse/EmployeeMainWH.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        EmployeeMainWHView view = loader.getController();
        view.init(viewModelProvider.getEmployeeWHVM());
        primaryStage.setTitle("Employee list WH");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This method opens the Headquartes' add Employee view.
     */
    public void openEmployeeAddHQView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("hq/employeeHQ/EmployeeAddHQ.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        EmployeeAddHQView view = loader.getController();
        view.init(viewModelProvider.getEmployeeAddHQVM());
        primaryStage.setTitle("Add employee to HQ system");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This method opens the main (and default) Headquarters view.
     */
    public void openMainHQView()
    {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("hq/main/Main.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        MainView view = loader.getController();
        view.init(viewModelProvider.getMainVM());
        primaryStage.setTitle("Dashboard");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openInventoryMainHQView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("hq/inventoryHQ/InventoryMainHQ.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        InventoryMainHQView view = loader.getController();
        view.init(viewModelProvider.getInventoryMainHQVM());
        primaryStage.setTitle("Stock item list HQ");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openInventoryAddHQView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("hq/inventoryHQ/InventoryAddHQ.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        InventoryAddHQView view = loader.getController();
        view.init(viewModelProvider.getInventoryAddHQVM());
        primaryStage.setTitle("Add Stock item list HQ");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
