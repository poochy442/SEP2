package flyweight;

public interface Tree {
    String getBarkColour();
    String getLeafColour();
    boolean hasLeafs();
    String [] commonlyFoundInAreas();
}
