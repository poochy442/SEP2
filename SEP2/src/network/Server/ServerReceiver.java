package network.Server;

import com.google.gson.Gson;
import jdbc.DataBaseModel;
import model.Employee;
import model.ProductRequest;
import model.StockItemList;
import network.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerReceiver implements Runnable {

    private Socket socket;
    DataBaseModel dataBaseModel;

    public ServerReceiver(Socket socket, DataBaseModel dataBaseModel) {
        this.socket = socket;
        this.dataBaseModel=dataBaseModel;
    }

    @Override
    public void run() {
        ObjectInputStream in = null;
        Gson gson = null;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            gson = new Gson();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Object incoming = in.readObject();
                if(incoming == null){
                    Thread.sleep(5000);
                    continue;
                }
                Packet packet = (Packet) incoming;
                String json = packet.getJson();
                switch (packet.getOperation()) {
                    case Packet.EmployeeOperation:
                        Employee employe = (Employee) gson.fromJson(json, Employee.class);

                        System.out.println("ServerReceiver:employeStoredToDB" + dataBaseModel.addEmployeeToDataBase(employe));
                        // TODO: change to view event
                        break;
                    case Packet.StockOperation:
                        StockItemList stockItemList = (StockItemList) gson.fromJson(json, StockItemList.class);
                        System.out.println("StockItemList received");
                        System.out.println(stockItemList);
                        System.out.println();
                        // TODO: Change to view event
                        break;
                    case Packet.RequestOperation:
                        ProductRequest productRequest = (ProductRequest) gson.fromJson(json, ProductRequest.class);
                        System.out.println("ProductRequest received");
                        System.out.println(productRequest);
                        System.out.println();
                        // TODO: Add alert to view, accept/decline productRequest
                        // Use getStockItem and GetQuantity to send the correct amounts to view
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
