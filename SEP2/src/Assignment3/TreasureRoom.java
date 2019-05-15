package Assignment3;

import java.util.ArrayList;

public class TreasureRoom {

    private ArrayList<Valuable> list;
    private int waitingWriters, activeWriters, activeReaders;

    public TreasureRoom() {
        waitingWriters = activeReaders = activeWriters = 0;
        list = new ArrayList<>();
    }

    public synchronized void getWriteAccess() {
        waitingWriters++;
        if (activeReaders > 0 || activeWriters > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        waitingWriters--;
    }

    public synchronized void getReadAccess() {

        if (activeWriters > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void releseRead() {
        activeReaders--;

        if (activeReaders == 0) {
            notify();
        }
    }

    public synchronized void releseWrite() {
        activeWriters--;
        notifyAll();
    }

    public void addValuable(String name) {
        Valuable valuable = ValuableFactory.getValuable(name);
        list.add(valuable);
    }

    public void removeValuable(String name) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                list.remove(i);
            }
        }

    }

    public Valuable getAndRemoveValuable(int index) {
        Valuable other = null;
        if (list.size() != 0) {
            other = list.get(index);
        }


        return other;
    }

    public ArrayList<Valuable> getList() {
        ArrayList<Valuable> copy = list;
        return copy;
    }

}


