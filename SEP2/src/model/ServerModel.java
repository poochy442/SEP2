package model;

import java.beans.PropertyChangeSupport;

public class ServerModel {
    private  PropertyChangeSupport changeSupport;
    public ServerModel ()
    {
         changeSupport = new PropertyChangeSupport(this);
    }
    public void sendEmployeeToClients (Employee employee)
    {
        changeSupport.firePropertyChange("SendEmployeeToClients",null,employee);
    }
}
