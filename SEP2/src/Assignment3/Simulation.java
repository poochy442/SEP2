package Assignment3;

public class Simulation {

    public static void main(String[] args) {
        TreasureRoom treasureRoom = new TreasureRoom();
        for (int i=0;i<10;i++)
        {
            Accountant a = new Accountant(treasureRoom);
            Thread t = new Thread(a);
            t.start();
        }
        for (int i=0;i<2;i++)
        {
            TaxCollector taxCollector = new TaxCollector(treasureRoom);
            Thread t = new Thread(taxCollector);
            t.start();
        }
        King king = new King(treasureRoom);
        Thread t1 = new Thread(king);
        t1.start();
    }
}
