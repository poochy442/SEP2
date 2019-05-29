package jdbc;

public class Setup {

    public static void main(String[] args) {

        DataBaseModel dataBaseModel = new DataBaseModel();
        dataBaseModel.createDepartmentTable();
        dataBaseModel.addDepartmentToDataBase("WH","Warehouse");
        dataBaseModel.addDepartmentToDataBase("HQ","Headquarters");
        dataBaseModel.addDepartmentToDataBase("RT","Retailer");
        dataBaseModel.createSep2Schema();
        dataBaseModel.createStockItemTable();
        dataBaseModel.createEmployeeTable();
        dataBaseModel.createRequestTable();
        dataBaseModel.createSalesListTable();
        dataBaseModel.createSalesTable();
        dataBaseModel.createItemRequestTable();

    }
}