package network.Server;

import com.google.gson.Gson;
import jdbc.DataBaseModel;
import model.*;
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
        this.dataBaseModel = dataBaseModel;
        this.dataModel = dataModel;


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
                if (incoming == null) {
                    Thread.sleep(1000);
                    continue;
                }
                Packet packet = (Packet) incoming;
                String json = packet.getJson();
                switch (packet.getOperation()) {
                    case Packet.EmployeeOperation:
                        Employee employe = (Employee) gson.fromJson(json, Employee.class);
                        System.out.println("EmployeeList received");
                        System.out.println(employe);
                        dataBaseModel.addEmployeeToDataBase(employe);
                        // TODO: change to view event
                        break;
                    case Packet.StockOperation:
                        StockItem stockItem = (StockItem) gson.fromJson(json, StockItem.class);
                        dataBaseModel.addItemToDataBase(stockItem);
                        //TODO CREATE METHOD TO ADD TO DB IN DATABASEMDOEL
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
