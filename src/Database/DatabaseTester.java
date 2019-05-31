package Database;

import Items.ItemsList;

public class DatabaseTester {
    public static void main(String[] args) {
        DatabaseTester test = new DatabaseTester();
    }

    public DatabaseTester() {
        DatabaseConnector.connect();
        ItemsList list = new ItemsList();
        System.out.println(list.getItem("Stick").getName() + list.getItem("Stick").getDescription());
    }
}
