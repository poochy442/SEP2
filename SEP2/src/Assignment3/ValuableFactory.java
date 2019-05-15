package Assignment3;

import java.util.HashMap;

public class ValuableFactory {
    static HashMap<String, Valuable> bag= new HashMap<>();


    public static Valuable getValuable(String valuableType) {
        Valuable item = bag.get(valuableType);
        if (item == null) {
            switch (valuableType) {
                case "Bronze": {
                    item = new Bronze();
                    break;
                }
                case "Silver": {
                    item = new Silver();
                    break;
                }
                case "Gold": {
                    item = new Gold();
                    break;
                }
                case "Diamond": {
                    item = new Diamond();
                    break;
                }
                default:
                    item = new UnknownItem();
                    break;

            }
            bag.put(valuableType, item);
        }
        return item;
    }

    public static Valuable getValuableByIndex(int index) {
        String name = "";
        switch (index) {
            case 0: {
                name = "Bronze";
                break;
            }
            case 1: {
                name = "Silver";
                break;
            }
            case 2: {
                name = "Gold";
                break;
            }
            case 3: {
                name = "Diamond";
                break;
            }
            default:
                name ="noName";
                break;


        }
        return getValuable(name);

    }



    public static void addValuable(String valuableType) {
        Valuable item = bag.get(valuableType);

        if (item == null) {
            switch (valuableType) {
                case "Bronze": {
                    item = new Bronze();
                    break;
                }
                case "Silver": {
                    item = new Silver();
                    break;
                }
                case "Gold": {
                    item = new Gold();
                    break;
                }
                case "Diamond": {
                    item = new Diamond();
                    break;
                }
                default:
                    item = new UnknownItem();
                    break;

            }
        }

        bag.put(valuableType, item);

    }

    public static Valuable getAndRemoveValuable(String valuableType) {

        Valuable item = bag.get(valuableType);
        bag.remove(valuableType);
        return item;


    }
}
