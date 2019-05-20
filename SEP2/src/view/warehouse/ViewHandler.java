package view.warehouse;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.warehouse.employee.EmployeeAddView;
import view.warehouse.employee.EmployeeMainView;
import view.warehouse.inventory.InventoryAddView;
import view.warehouse.inventory.InventoryMainView;
import view.warehouse.inventory.ProductRequestView;
import view.warehouse.main.MainView;
import viewmodel.warehouse.ViewModelProvider;

import java.io.IOException;

public class ViewHandler {
    private Stage primaryStage;
    private ViewModelProvider viewModelProvider;
    private Rectangle2D screenSize;

    public ViewHandler(Stage stage, ViewModelProvider vmp) {
        primaryStage = stage;
        primaryStage.setMaximized(true);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        screenSize = Screen.getPrimary().getVisualBounds();
        viewModelProvider = vmp;
        viewModelProvider.instantiateViewModels(this);
    }

    public void start() {
        openMainView();
    }

    public void openMainView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("main/Main.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainView view = loader.getController();
        view.init(viewModelProvider.getMainVM());
        primaryStage.setTitle("Dashboard");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openEmployeeMainView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("employee/EmployeeMain.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EmployeeMainView view = loader.getController();
        view.init(viewModelProvider.getEmployeeMainVM());
        primaryStage.setTitle("List of employees");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openInventoryMainView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("inventory/InventoryMain.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InventoryMainView view = loader.getController();
        view.init(viewModelProvider.getInventoryMainVM());
        primaryStage.setTitle("Stock inventory");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openEmployeeAddView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("employee/EmployeeAdd.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EmployeeAddView view = loader.getController();
        view.init(viewModelProvider.getEmployeeAddVM());
        primaryStage.setTitle("Add employee");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openInventoryAddView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("inventory/InventoryAdd.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InventoryAddView view = loader.getController();
        view.init(viewModelProvider.getInventoryAddVM());
        primaryStage.setTitle("Add stock item");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openProductRequestView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("inventory/ProductRequest.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        ProductRequestView view = loader.getController();
        view.init(viewModelProvider.getProductRequestVM());
        primaryStage.setTitle("Product request");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
