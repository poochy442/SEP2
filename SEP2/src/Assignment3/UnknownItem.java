package Assignment3;

public class UnknownItem implements Valuable {
    @Override
    public String getName() {
        return "Unknown";
    }

    @Override
    public int getValue() {
        return 0;
    }
}
