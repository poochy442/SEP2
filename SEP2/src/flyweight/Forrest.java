package flyweight;

import java.util.ArrayList;
import java.util.List;

public class Forrest {
    List<Tree> trees;

    public Forrest(int oak,int birch,int spruce) {
        trees = new ArrayList<>();
        for (int i=0;i<oak;i++)
        {
            Tree oakTree = TreeFactory.getTree("oak");
            trees.add(oakTree);
        }
        for (int i=0;i<birch;i++)
        {
            Tree birchTree = TreeFactory.getTree("birch");
            trees.add(birchTree);
        }
        for (int i=0;i<spruce;i++)
        {
            Tree spruceTree = TreeFactory.getTree("spruce");
            trees.add(spruceTree);
        }

    }
    public List<Tree>getTrees ()
    {
        return trees;
    }

    public static void main(String[] args) {
        Forrest forrest = new Forrest(3,3,3);

        Tree o1 = TreeFactory.getTree("oak");
        Tree o2 = TreeFactory.getTree("oak");
        System.out.println( o1==o2);
        System.out.println(o1.equals(o2));

    }
}
