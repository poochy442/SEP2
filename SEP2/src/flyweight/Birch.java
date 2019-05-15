package flyweight;

public class Birch implements Tree {
    public Birch() {
    }

    @Override
    public String getBarkColour() {
        return "Black";
    }

    @Override
    public String getLeafColour() {
        return "Purple";
    }

    @Override
    public boolean hasLeafs() {
        return false;
    }

    @Override
    public String[] commonlyFoundInAreas() {
        return new String[] {"Spain","North Asia","Australia"};
    }
}