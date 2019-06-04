package readersWritters;

/**
 * This Class is a reader for the database, implementing the reader/writer design pattern
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */
public class Reader implements Runnable {
    private ReadWriteSafe sharedResourceController;
    private String name;

    /**
     * Creates a Reader with the given parameters
     * @param sharedResourceController The {@link ReadWriteSafe} to use
     * @param name foo
     */
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
