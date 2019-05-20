package readersWritters;

public class RunReadersPref {
    public static void main(String[] args) {

        ReadWriteSafe readWriteSafe = new ReadWriteSafe();

        for (int i=0;i<10;i++)
        {
            Reader r = new Reader(readWriteSafe,"R"+i);
            Thread t1 = new Thread(r);
            t1.start();
        }
        for (int i=0;i<4;i++)
        {
            Writer w1 = new Writer(readWriteSafe,"W"+i);
           Thread t1 = new Thread(w1);
           t1.start();
        }


    }
}
