package network.client;

import com.google.gson.Gson;
import model.EmployeeList;
import model.IDataModel;
import model.StockItemList;
import network.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * The ClientReveiver Class is responsible for all Incoming traffic to the {@link Client}¨.
 * It uses a modified Producer/Consumer pattern, with the {@link network.Server.ServerSender} being the Producer.
 * The Class will loop between putting running the {@link Thread#sleep(long)} method and checking for incoming traffic.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class ClientReceiver implements Runnable {

    private Socket socket;
    private IDataModel dataModel;

    /**
     * Creates the ClientReceiver with the specified {@link Socket} and {@link model.DataModel}.
     *
     * @param socket    The {@link Socket} to use.
     * @param dataModel The {@link model.DataModel} to use.
     */
    public ClientReceiver(Socket socket, IDataModel dataModel) {
        this.socket = socket;
        this.dataModel = dataModel;
    }

    /**
     * The run method inherited from {@link Runnable}.
     * This method is responsible for the logic of the ClientReceiver.
     */
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
                            dataModel.addEmployeeFromServer(employeeList.get(i));
                        }
                        // TODO: Fix back/forth firing of responses
                        break;
                    case Packet.StockResponseOperation:
                        StockItemList stockItemList = (StockItemList) gson.fromJson(json, StockItemList.class);
                        for (int i = 0; i < stockItemList.size(); i++) {
                            dataModel.addItemFromServer(stockItemList.get(i));
                        }
                        // TODO: Fix back/forth firing of responses
                        break;
                    case Packet.EmployeeQuery:
                        EmployeeList employeeList1 = (EmployeeList) gson.fromJson(json, EmployeeList.class);
                        for (int i = 0; i < employeeList1.size(); i++) {
                            dataModel.addEmployeeFromServer(employeeList1.get(i));
                        }
                        break;
                    case Packet.ItemQuery:
                        StockItemList stockItemList1 = (StockItemList) gson.fromJson(json, StockItemList.class);
                        for (int i = 0; i < stockItemList1.size(); i++) {
                            dataModel.addItemFromServer(stockItemList1.get(i));
                        }
                        break;
                    case Packet.salesQuery:
                        StockItemList stockItemList2 = (StockItemList) gson.fromJson(json, StockItemList.class);
                        for (int i=0;i<stockItemList2.size();i++)
                        {
                            dataModel.addToSales(stockItemList2.get(i),false);
                        }
                        break;

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
