package model;

public class Counter {
    private int counterStockItem;
    private int counterEmployee;
    private DataModel dataModel;

    public Counter(DataModel dataModel)
    {
        this.dataModel = dataModel;
        counterStockItem = 0;
        counterEmployee = 0;
    }

    private void incrementEmployee()
    {
        counterEmployee++;
    }

    private void incrementStockItem()
    {
        counterStockItem++;
    }

    public String getIDEmployee()
    {
        char fill = '0';
        int id = -1;
        for(int i = 0; i < dataModel.getEmployeeList().size(); i++)
        {
            id = i;
        }
        id++;
        String finalID = new String(new char[6]).replace('\0', fill) + id;
        return finalID;
    }

    public String getIDStockItem()
    {
        char fill = '0';
        int id = -1;
        for(int i = 0; i < dataModel.getStockItemList().size(); i++)
        {
            id = i;
        }
        id++;
        String finalID = new String(new char[6]).replace('\0', fill) + id;
        return finalID;
    }
    //TODO: U can modify here how ID for employee and stock item should look like
}
