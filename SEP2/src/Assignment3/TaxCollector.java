package Assignment3;

import java.util.ArrayList;

public class TaxCollector implements Runnable {
    private TreasureRoom treasureRoom;

    public TaxCollector(TreasureRoom treasureRoom) {
        this.treasureRoom = treasureRoom;

    }

    private ArrayList<Valuable> valuables= new ArrayList<>();


    private int randomNumber(int min, int max) {
        int time = (int) Math.random() * (max - min) + min;
        return time;
    }


    @Override
    public void run() {


         while (true) {
            int randomNumber = randomNumber(50, 200);
            int totalValue = 0;

            while (totalValue < randomNumber) {
                int random = randomNumber(0, 4);
                String name = "";
                Valuable v = ValuableFactory.getValuableByIndex(random);
                valuables.add(v);
                totalValue += v.getValue();

            }
             System.out.println("TaxCollector: waiting for write");
            treasureRoom.getWriteAccess();
             System.out.println("TaxCollector: writing");
            for (int i = 0; i < valuables.size(); i++) {
                treasureRoom.addValuable(valuables.get(i).getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            treasureRoom.releseWrite();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
