package readersWritters;

public class Writer implements Runnable {
    private ReadWriteSafe sharedResourceController;
    private String name;

    public Writer(ReadWriteSafe sharedResourceController, String name) {
        this.sharedResourceController = sharedResourceController;
        this.name = name;
    }

    @Override
    public void run() {
        while (true)
        {
            System.out.println(name + " wants write access");
            sharedResourceController.acquireWrite();
            sharedResourceController.doWrite();
            System.out.println(name + " changed to " +sharedResourceController.doRead());
            sharedResourceController.releaseWrite();
            System.out.println(name + " released write accesss");
        }

    }
}
