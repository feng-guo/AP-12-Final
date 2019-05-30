package Entities;
import Database.DatabaseConnector;
import Items.ItemsList;
import Items.Stack;

import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.ResultSet;

public class EnemiesList {
    private static HashMap<String, Enemy> enemyHashMap;

    public static void initialize() {
        enemyHashMap = new HashMap<>();
        String s = "SELECT * FROM Enemy";
        ResultSet enemies = DatabaseConnector.query(s);
        addToMap(enemies);
    }

    private static void addToMap(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                String lootQuery = "SELECT * FROM " + resultSet.getString("name");
                ResultSet lootTableSet = DatabaseConnector.query(lootQuery);
                HashMap<Stack, Double> lootTable = new HashMap<>();
                while (lootTableSet.next()) {
                    Stack stack = new Stack(lootTableSet.getInt("stackAmount"), ItemsList.getItem(lootTableSet.getString("item")));
                    lootTable.put(stack, lootTableSet.getDouble("probability"));
                }
                Enemy enemy = new Enemy(Toolkit.getDefaultToolkit().createImage(resultSet.getString("spriteUrl")), resultSet.getInt("length"), resultSet.getInt("width"), resultSet.getInt("maxHealth"), resultSet.getInt("speed"), resultSet.getInt("defense"), resultSet.getInt("dexterity"), resultSet.getString("name"), resultSet.getString("species"), lootTable);
                enemyHashMap.put(resultSet.getString("name"), enemy);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static Enemy getEnemy(String enemy) {
        return enemyHashMap.get(enemy);
    }
}
