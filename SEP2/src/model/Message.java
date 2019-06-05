package model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Message {
    private String message;
    private Timestamp timestamp;
    private String departmentID;

    public Message(String message, Timestamp timestamp, String departmentID) {
        this.message = message;
        this.timestamp = timestamp;
        this.departmentID = departmentID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String toString()
    {
        String s = "";
        String user = "";
        if(departmentID.equals("WH"))
        {
            user = "Warehouse manager";
        }
        else if(departmentID.equals("RT"))
        {
            user = "Retailer manager";
        }
        else
        {
            user = "Headquarter manager";
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(timestamp.getTime());
        String timeString = format.format(date);

        return timeString + " - " + user + ": " + "\n" + message;
    }
}
