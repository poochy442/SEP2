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
        int width = 6;
        char fill = '0';
        int id = 0;
        for(int i = 0; i < dataModel.getEmployeeList().size(); i++)
        {
            id = i++;
        }
        String finalID = new String(new char[width - id]).replace('\0', fill) + id;
        return finalID;
    }

    public String getIDStockItem()
    {
        int width = 6;
        char fill = '0';
        int id = 0;
        for(int i = 0; i < dataModel.getStockItemList().size(); i++)
        {
            id = i++;
        }
        String finalID = new String(new char[width - id]).replace('\0', fill) + id;
        return finalID;
    }
}
