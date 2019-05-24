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

    public ServerReceiver(Socket socket, DataBaseModel dataBaseModel) {
        this.socket = socket;
        this.dataBaseModel = dataBaseModel;
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
                        System.out.println("ServerReceiver:employeStoredToDB" + dataBaseModel.addEmployeeToDataBase(employe));
                        ;
                        // TODO: change to view event
                        break;
                    case Packet.StockOperation:
                        StockItem stockItem = (StockItem) gson.fromJson(json, StockItem.class);
                        System.out.println("ServerReceiver: itemstoredToDB=" + dataBaseModel.addItemToDataBase(stockItem,"WH"));


                        break;
                    case Packet.RequestOperation:
                        ProductRequestList productRequestList = gson.fromJson(json, ProductRequestList.class);
                        System.out.println("ServerReceiver: ProductRequestList received");
                        System.out.println("ServerReceiver "+productRequestList);
                        int requestID=dataBaseModel.addRequestToDataBase("WH");
                        dataBaseModel.addRequestItemsToDataBase(productRequestList,requestID);
                        break;
                    case Packet.EmployeeQuery:
                        dataBaseModel.employeeQuery();
                        break;
                    case Packet.ItemQuery:
                        dataBaseModel.itemQuery();
                        System.out.println("ServerReceiver: ItemQuery()called in DB");
                        break;
                    case Packet.DeleteItemFromWH:
                        StockItem stockItem1 = gson.fromJson(json,StockItem.class);
                        dataBaseModel.deleteItemByIdAndDepartment(stockItem1.getId(),"WH");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
