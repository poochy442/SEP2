package flyweight;

public class Spruce implements Tree {
    public Spruce() {
    }

    @Override
    public String getBarkColour() {
        return "Blue";
    }

    @Override
    public String getLeafColour() {
        return "Red";
    }

    @Override
    public boolean hasLeafs() {
        return true;
    }

    @Override
    public String[] commonlyFoundInAreas() {
        return new String[] {"Denmark","Cuba","Springfield"};
    }
}