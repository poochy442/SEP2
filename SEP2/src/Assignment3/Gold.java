package Assignment3;

public class Gold implements Valuable {
    @Override
    public String getName() {
        return "Gold";
    }

    @Override
    public int getValue() {
        return 100;
    }
}
