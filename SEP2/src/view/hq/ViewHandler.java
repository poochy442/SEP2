package view.hq;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import view.hq.controlWarehouse.employeeWarehouse.EmployeeMainWHView;
import view.hq.controlWarehouse.inventoryWarehouse.InventoryMainWHView;
import view.hq.hq.employeeHQ.EmployeeAddHQView;
import view.hq.hq.employeeHQ.EmployeeMainHQView;
import view.hq.hq.main.MainView;
import viewmodel.hq.ViewModelProvider;

import java.io.IOException;

public class ViewHandler {
    private Stage primaryStage;
    private ViewModelProvider viewModelProvider;
    private Rectangle2D screenSize;

    public ViewHandler(Stage stage, ViewModelProvider vmp)
    {
        primaryStage = stage;
        primaryStage.setMaximized(true);
//      primaryStage.initStyle(StageStyle.UNDECORATED);
        screenSize = Screen.getPrimary().getVisualBounds();
        viewModelProvider = vmp;
        viewModelProvider.instantiateViewModels(this);
    }

    public void start() {
        openMainView();
    }

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

    public void openInventoryWHView() {
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

    public void openEmployeeWHView() {
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

    public void openMainView()
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
}
