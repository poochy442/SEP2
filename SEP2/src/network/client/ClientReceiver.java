package network.client;

import com.google.gson.Gson;
import model.EmployeeList;
import model.IDataModel;
import model.StockItemList;
import network.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientReceiver implements Runnable {

    private Socket socket;
    private IDataModel dataModel;

    public ClientReceiver(Socket socket, IDataModel dataModel) {
        this.socket = socket;
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
                    case Packet.EmployeeResponseOperation:
                        EmployeeList employeeList = (EmployeeList) gson.fromJson(json, EmployeeList.class);
                        for (int i = 0; i < employeeList.size(); i++) {
                            dataModel.addEmployeeToClient(employeeList.get(i));
                        }
                        // TODO: Fix back/forth firing of responses
                        break;
                    case Packet.StockResponseOperation:
                        StockItemList stockItemList = (StockItemList) gson.fromJson(json, StockItemList.class);
                        for (int i = 0; i < stockItemList.size(); i++) {
                            dataModel.addItemToClient(stockItemList.get(i));
                        }
                        // TODO: Fix back/forth firing of responses
                        break;
                    case Packet.EmployeeQuery:
                        EmployeeList employeeList1 = (EmployeeList) gson.fromJson(json, EmployeeList.class);
                        for (int i=0;i<employeeList1.size();i++)
                        {
                            dataModel.addEmployeeToClient(employeeList1.get(i));
                        }
                        break;
                    case Packet.ItemQuery:
                        StockItemList stockItemList1 = (StockItemList) gson.fromJson(json, StockItemList.class);
                        for (int i=0;i<stockItemList1.size();i++)
                        {
                            dataModel.addItemToClient(stockItemList1.get(i));
                        }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
