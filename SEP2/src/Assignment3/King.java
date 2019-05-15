package Assignment3;

import java.util.ArrayList;

public class King implements Runnable {
    private TreasureRoom treasureRoom;

    public King(TreasureRoom treasureRoom) {
        this.treasureRoom = treasureRoom;
    }

    private int randomNumber(int min, int max) {
        int time = (int) Math.random() * (max - min) + min;
        return time;
    }

    @Override
    public void run() {
        while (true) {
            ArrayList<Valuable> valuables = new ArrayList<>();
            Valuable v=null;
            int randomNumber = randomNumber(50, 150);
            int totalValue = 0;
            treasureRoom.getWriteAccess();
            while (totalValue < randomNumber) {
                 v = treasureRoom.getAndRemoveValuable(0);
                if (v == null) {
                    for (int i = 0; i < valuables.size(); i++) {
                        treasureRoom.addValuable(valuables.get(i).getName());
                    }
                    break;
                }

                valuables.add(v);
                totalValue += v.getValue();

            }
            if (totalValue < randomNumber) {
                System.out.println("Party canceled sorry guys");
            } else System.out.println("Let's go party with the king");

            treasureRoom.releseWrite();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
