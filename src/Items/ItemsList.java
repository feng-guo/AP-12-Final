package Items;

import Database.DatabaseConnector;

import java.awt.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.ResultSet;

public class ItemsList {
    private static HashMap<String, Item> itemHashMap;

    public static void initialize() {
        itemHashMap = new HashMap<>();
        String s = "SELECT * FROM Item WHERE itemType = ";
        ResultSet materials = DatabaseConnector.query(s + "'Material'");
        ResultSet food = DatabaseConnector.query(s + "'Food'");
        ResultSet potions = DatabaseConnector.query(s + "'Potion'");
        ResultSet armour = DatabaseConnector.query(s + "'Armour'");
        ResultSet weapons = DatabaseConnector.query(s + "'Weapon'");
        ResultSet tools = DatabaseConnector.query(s + "'Tool'");
        addToMap(materials, "Material");
        addToMap(food, "Food");
        addToMap(potions, "Potion");
        addToMap(armour, "Armour");
        addToMap(weapons, "Weapon");
        addToMap(tools, "Tool");
    }

    private static void addToMap(ResultSet set, String itemType) {
        try {
            if ("Material".equals(itemType)) {
                while (set.next()) {
                    Item material = new Material(set.getString("name"), set.getString("description"), Toolkit.getDefaultToolkit().createImage(set.getString("spriteUrl")));
                    itemHashMap.put(set.getString("name"), material);
                }
            } else if ("Food".equals(itemType)) {
                while (set.next()) {
                    Item food = new Food(set.getString("name"), set.getString("description"),Toolkit.getDefaultToolkit().createImage(set.getString("spriteUrl")),set.getInt("healthGained"),set.getInt("restorationValue"));
                    itemHashMap.put(set.getString("name"), food);
                }
            } else if ("Potion".equals(itemType)) {
                while (set.next()) {
                    Item potion = new Potion(set.getString("name"), set.getString("description"), Toolkit.getDefaultToolkit().createImage(set.getString("spriteUrl")), set.getInt("healthGain"), set.getString("effect"), set.getInt("duration"));
                    itemHashMap.put(set.getString("name"), potion);
                }
            } else if ("Armour".equals(itemType)) {
                while (set.next()) {
                    if (!set.getBoolean("armourStats")) {
                        Item armour = new Armour(set.getString("name"), set.getString("description"), Toolkit.getDefaultToolkit().createImage(set.getString("spriteUrl")), set.getInt("durability"), set.getInt("armourPoints"), set.getString("location"));
                        itemHashMap.put(set.getString("name"), armour);
                    } else {
                        Item armour = new Armour(set.getString("name"), set.getString("description"), Toolkit.getDefaultToolkit().createImage(set.getString("spriteUrl")), set.getInt("durability"), set.getInt("armourPoints"), set.getString("location"), set.getInt("health"), set.getInt("speed"), set.getInt("dexterity"));
                        itemHashMap.put(set.getString("name"), armour);
                    }
                }
            } else if ("Weapon".equals(itemType)) {
                while (set.next()) {
                    Item weapon = new Weapon(set.getString("name"), set.getString("description"), Toolkit.getDefaultToolkit().createImage(set.getString("spriteUrl")), set.getInt("durability"), set.getInt("damage"), set.getInt("range"));
                    itemHashMap.put(set.getString("name"), weapon);
                }
            } else if ("Tool".equals(itemType)) {
                while (set.next()) {
                    Item tool = new Tool(set.getString("name"), set.getString("description"), Toolkit.getDefaultToolkit().createImage(set.getString("spriteUrl")), set.getInt("durability"), set.getInt("damage"), set.getInt("range"), set.getInt("toolHit"));
                    itemHashMap.put(set.getString("name"), tool);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static Item getItem(String item) {
        return itemHashMap.get(item);
    }
}
