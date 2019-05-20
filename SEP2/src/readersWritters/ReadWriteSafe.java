package readersWritters;

public class ReadWriteSafe implements ReadWrite {

    private int activeReaders,activeWriters,waitingReaders,sharedData;

    public ReadWriteSafe() {
        activeReaders=0;
        activeWriters=0;
        waitingReaders=0;
        sharedData=0;
    }

    @Override
    public synchronized void acquireRead() {
        waitingReaders++;
        while (activeWriters>0)
        {
            try {
                System.out.println("Couldn't acquire read, wait");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        waitingReaders--;
        activeReaders++;


    }

    @Override
    public synchronized void releaseRead() {
        activeReaders--;
        if (activeReaders == 0)
        {
            notify(); //notify one waiting writer
        }

    }

    @Override
    public synchronized void acquireWrite() {
        while (activeReaders>0||activeWriters>0||waitingReaders>0)
        {
            try {
                System.out.println("Couldn't acquire write, wait");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        activeWriters++;

    }

    @Override
    public synchronized void releaseWrite() {
        activeWriters--;
        notifyAll();

    }
    public void doWrite()
    {
        sharedData++;
    }
    public int doRead()
    {
        return sharedData;
    }
}
