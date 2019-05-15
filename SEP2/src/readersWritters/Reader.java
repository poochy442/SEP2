package readersWritters;

public class Reader implements Runnable {
    private ReadWriteSafe sharedResourceController;
    private String name;

    public Reader(ReadWriteSafe sharedResourceController, String name) {
        this.sharedResourceController = sharedResourceController;
        this.name = name;
    }

    @Override
    public void run() {
        while (true)
        {
            System.out.println(name + " wants read access");
            sharedResourceController.acquireRead();
            int i= sharedResourceController.doRead();
            System.out.println(name+ " read " + i);
            sharedResourceController.releaseRead();
            System.out.println(name + " release read access");

        }
    }
}
