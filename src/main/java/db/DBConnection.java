package db;

import model.Item;

import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private static DBConnection instance;
    private List<Item> itemArrayList;

    private DBConnection() {
        itemArrayList = new ArrayList<>();
    }

    public static DBConnection getInstance() {
        return instance == null ? instance = new DBConnection() : instance;
    }

    public List getConnection() {
        return itemArrayList;
    }

}
