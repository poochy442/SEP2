package network.Server;

import com.google.gson.Gson;
import jdbc.DataBaseModel;
import model.EmployeeList;
import model.IDataModel;
import model.Request;
import model.StockItemList;
import network.Packet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerReceiver implements Runnable {

    private Socket socket;
    private DataBaseModel dataBaseModel;
    private IDataModel dataModel;

    public ServerReceiver(Socket socket, DataBaseModel dataBaseModel, IDataModel dataModel) {
        this.socket = socket;
        this.dataBaseModel=dataBaseModel;
        this.dataModel=dataModel;


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
                        EmployeeList employeeList = (EmployeeList) gson.fromJson(json, EmployeeList.class);
                        System.out.println("EmployeeList received");
                        System.out.println(employeeList);
                        System.out.println();
                        // TODO: change to view event
                        break;
                    case Packet.StockOperation:
                        StockItemList stockItemList = (StockItemList) gson.fromJson(json, StockItemList.class);
                        System.out.println("StockItemList received");
                        System.out.println(stockItemList);
                        // TODO: Change to view event
                        break;
                    case Packet.RequestOperation:
                        Request request = (Request) gson.fromJson(json, Request.class);
                        System.out.println("Request received");
                        System.out.println(request);
                        System.out.println();
                        // TODO: Add alert to view, accept/decline request
                        // Use getStockItem and GetQuantity to send the correct amounts to view
                        break;
                    case Packet.EmployeeQuery:
                        dataBaseModel.employeeQuery();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
