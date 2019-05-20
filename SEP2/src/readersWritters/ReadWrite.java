package readersWritters;

public interface ReadWrite {
    void acquireRead(); //if no-one is writing
    void releaseRead();
    void acquireWrite(); // if no-one is reading or writing
    void releaseWrite();
}
