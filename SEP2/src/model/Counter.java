package model;

/**
 * <h1>Counter Class used for ID creation</h1>
 * The Counter Class stores an int for both employees added and Stock Items added.
 * These are used an incremented when creating a new Employee or Stock Item ID.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class Counter {
    /**
     * The counter for Stock Items
     */
    private int counterStockItem;
    /**
     * The counter for Employees
     */
    private int counterEmployee;
    private DataModel dataModel;

    /**
     * Creates a Counter using the specified DataModel
     * @param dataModel the DataModel the Counter will use
     */
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

    /**
     * Increments the counter for the Employee and returns an ID using that value
     * @return The created Employee ID
     */
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

    /**
     * Increments the counter for the Employee and returns an ID using that value
     * @return The created Stock Item ID
     */
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
