package network.Server;

import com.google.gson.Gson;
import jdbc.DataBaseModel;
import model.*;
import network.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * <h1>ServerReceiver Class responsible for the incoming traffic of the {@link Server}</h1>
 * This class implements a modified Producer/Consumer pattern, with the {@link network.client.ClientSender} as the Producer.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class ServerReceiver implements Runnable {

    private Socket socket;
    private DataBaseModel dataBaseModel;
    private IDataModel dataModel;
    private int clientNo;

    /**
     * Creates a ServerReceiver with the specified {@link Socket} and {@link DataBaseModel}.
     *
     * @param socket        The {@link Socket} for the ServerReceiver to use.
     * @param dataBaseModel The {@link DataBaseModel} for the ServerReceiver to use.
     */
    public ServerReceiver(Socket socket, DataBaseModel dataBaseModel, int clientNo) {
        this.socket = socket;
        this.dataBaseModel = dataBaseModel;
        this.clientNo = clientNo;
    }

    public int getClientNo() {
        return clientNo;
    }

    /**
     * The run method inherited from the {@link Runnable}.
     * This method creates an {@link java.io.ObjectOutputStream} with the input stream of the {@link Socket}.
     * It then loops running {@link Thread#sleep(long)} and checking whether something has been sent to it.
     * If something has been sent, it decrypts the {@link Packet} and performs the logic associated with the
     * request contained.
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
                    case Packet.EmployeeOperation:
                        Employee employee = (Employee) gson.fromJson(json, Employee.class);
                        System.out.println("ServerReceiver: employee stored to database = " + dataBaseModel.addEmployeeToDataBase(employee, clientNo));

                        ;
                        // TODO: change to view event
                        break;
                    case Packet.StockOperation:
                        StockItem stockItem = (StockItem) gson.fromJson(json, StockItem.class);
                        System.out.println("ServerReceiver: item stored to database = " + dataBaseModel.addItemToDataBase(stockItem, stockItem.getLocation(), clientNo));


                        break;
                    case Packet.RequestOperation:
                        String departmentID1 =json;
                        String requestID = ""+dataBaseModel.requestProductMaxID(departmentID1);
                        dataBaseModel.setRequestStatus(departmentID1,requestID, clientNo, "Complete");
                        break;
                    case Packet.EmployeeQuery:
                        String departmentID = json;
                        dataBaseModel.employeeQuery(departmentID, clientNo);

                        break;
                    case Packet.ItemQuery:
                        String depID = json;
                        dataBaseModel.itemQuery(depID, clientNo);
                        System.out.println("ServerReceiver: ItemQuery()called in DB");
                        break;
                    case Packet.DeleteItemFromWH:
                        StockItem stockItem1 = gson.fromJson(json, StockItem.class);
                        System.out.println("Server Receiver: Stock item deleted from database = " +
                                dataBaseModel.deleteItemByIdAndDepartment(stockItem1.getId(), stockItem1.getLocation(), clientNo));
                        break;
                    case Packet.DeleteItemFromHQ:
                        StockItem stockItem2 = gson.fromJson(json, StockItem.class);
                        dataBaseModel.deleteItemByIdAndDepartment(stockItem2.getId(), stockItem2.getLocation(), clientNo);
                        break;
                    case Packet.DeleteEmployee:
                        employee = gson.fromJson(json, Employee.class);
                        System.out.println("Employee: " + employee.getId() + " deleted = " + dataBaseModel.deleteEmployee(employee));
                        break;
                    case Packet.AddSale:
                        StockItem stockItem3 = gson.fromJson(json, StockItem.class);
                        System.out.println("ServerReceiver: sale added to database = " + dataBaseModel.addSaleToDataBase(stockItem3, clientNo));
                        break;
                    case Packet.salesQuery:
                        dataBaseModel.salesQuery(clientNo);
                        System.out.println("ServerReceiver: SalesQuery");
                        break;
                    case Packet.addProductRequest:
                        ProductRequest productRequest = gson.fromJson(json, ProductRequest.class);
                        String department = productRequest.getStockItem().getLocation();

                        int departmentid = dataBaseModel.requestProductMaxID(department);

                        if (departmentid == 0) {
                            departmentid = dataBaseModel.requestCountQuery();

                            dataBaseModel.addRequestToDataBase(productRequest.getStockItem().getLocation(), clientNo);
                            departmentid++;
                        }


                        System.out.println(dataBaseModel.addRequestItemToDatabase(productRequest, departmentid, clientNo));
                        break;
                    case Packet.requestQuery:
                        dataBaseModel.requestQuery(clientNo, json);
                    case Packet.deliveriesQuery:
                        dataBaseModel.
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
