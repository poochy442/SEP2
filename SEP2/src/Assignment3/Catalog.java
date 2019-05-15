package Assignment3;

import java.util.ArrayList;

public class Catalog {

    private static Catalog instance;
    private ArrayList<String> log;

    public Catalog() {
        log = new ArrayList<>();

    }

    public static Catalog getInstance() {
        if (instance == null) {
            instance = new Catalog();
        }
        return instance;
    }

    public void log(String txt) {
        log.add(txt);
        System.out.println("Logger :"+txt);
    }
}


