package Assignment3;

import java.util.ArrayList;

public class Accountant implements Runnable {
    private TreasureRoom treasureRoom;
    private int sum;

    public Accountant(TreasureRoom treasureRoom) {
        this.treasureRoom = treasureRoom;
        this.sum=0;
    }

    @Override
    public void run() {
        while(true)
        {
            sum=0;
            treasureRoom.getReadAccess();
            ArrayList<Valuable> list = treasureRoom.getList();


            for (int i=0;i<list.size();i++)
            {
                sum+=list.get(i).getValue();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Acountant >the total sum is : "+sum);
            treasureRoom.releseRead();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
