package flyweight;

public class Oak implements Tree {
    public Oak() {
    }

    @Override
    public String getBarkColour() {
        return "Brown";
    }

    @Override
    public String getLeafColour() {
        return "Light Green";
    }

    @Override
    public boolean hasLeafs() {
        return true;
    }

    @Override
    public String[] commonlyFoundInAreas() {
        return new String[] {"Europe","North America","Asia"};
    }
}
