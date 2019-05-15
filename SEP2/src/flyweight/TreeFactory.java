package flyweight;

import java.util.HashMap;

public class TreeFactory {
    static HashMap<String,Tree> trees = new HashMap<>();

    public static Tree getTree (String treeType)
    {
        Tree tree = trees.get(treeType);
        if (tree == null) {
            switch (treeType)
            {
                case "oak": {
                    tree= new Oak();
                }
                case "birch" : {
                    tree= new Birch();
                }
                case "spruce": {
                    tree = new Spruce();
                }
            }
            trees.put(treeType,tree);
        }
        return tree;
    }
}
