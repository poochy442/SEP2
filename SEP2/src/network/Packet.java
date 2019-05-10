package network;

import java.io.Serializable;

public class Packet implements Serializable {

    private String operation, Json;
    public static final String EmployeeOperation = "Employees",
            StockOperation = "StockItems",
            RequestOperation = "Request",
            EmployeeResponseOperation = "EmployeeResponse",
            StockResponseOperation = "StockResponse"
            ,EmployeeQuery="EmployeeQuery";

    public Packet(String operation, String json) {
        this.operation = operation;
        Json = json;
    }

    public String getOperation() {
        return operation;
    }

    public String getJson() {
        return Json;
    }
}
