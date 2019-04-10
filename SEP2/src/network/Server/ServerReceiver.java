package network.Server;

import com.google.gson.Gson;
import model.EmployeeList;
import model.StockItemList;
import network.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerReceiver implements Runnable {

    private Socket socket;

    public ServerReceiver(Socket socket) {
        this.socket = socket;
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
        while(true){
            try {
                Packet packet = (Packet) in.readObject();
                String json = packet.getJson();
                switch (packet.getOperation()){
                    case Packet.EmployeeOperation:
                        EmployeeList employeeList = (EmployeeList) gson.fromJson(json, EmployeeList.class);
                        // TODO: Add to view / DB and send to other client
                    case Packet.StockOperation:
                        StockItemList stockItemList = (StockItemList) gson.fromJson(json, StockItemList.class);
                        // TODO: Add to view / DB and send to other clients
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
