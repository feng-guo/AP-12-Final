package World;

import Entities.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Location {
    private Image background;
    private ArrayList<Double> playerIDs;
    private ArrayList<Double> npcIDs;
    private ArrayList<Double> enemyIDs;
    private ArrayList<Double> weaponEffectIDs;
    private ArrayList<Double> environmentalIDs;
    private ArrayList<Double> itemDropIDs;
    private HashMap<Double, PlayerInstance> playerInstanceHashMap;
    private HashMap<Double, NPCInstance> npcInstanceHashMap;
    private HashMap<Double, EnemyInstance> enemyInstanceHashMap;
    private HashMap<Double, WeaponEffectInstance> weaponEffectInstanceHashMap;
    private HashMap<Double, EnvironmentalInstance> environmentalInstanceHashMap;
    private HashMap<Double, ItemDropInstance> itemDropInstanceHashMap;
    private ArrayList<Structure> structures;
    //fix later
    private int[][] map;
    /**
           {
            {1,1,1,1,1},
            {1,0,0,0,1},
            {1,0,0,0,1},
            {1,0,0,0,1},
            {1,1,1,1,1}};**/

    public Location(Image background, int[][] map) {
        this.map = map;
        this.background = background;
        playerIDs = new ArrayList<>();
        npcIDs = new ArrayList<>();
        enemyIDs = new ArrayList<>();
        weaponEffectIDs = new ArrayList<>();
        environmentalIDs = new ArrayList<>();
        itemDropIDs = new ArrayList<>();
        playerInstanceHashMap= new HashMap<>();
        npcInstanceHashMap = new HashMap<>();
        enemyInstanceHashMap = new HashMap<>();
        weaponEffectInstanceHashMap = new HashMap<>();
        environmentalInstanceHashMap = new HashMap<>();
        itemDropInstanceHashMap = new HashMap<>();
        structures = new ArrayList<>();
    }

    public void addPlayer(PlayerInstance player, double id) {
        playerIDs.add(id);
        playerInstanceHashMap.put(id, player);
    }

    public void addNpc(NPCInstance npc, double id) {
        npcIDs.add(id);
        npcInstanceHashMap.put(id, npc);
    }

    public void addEnemy(EnemyInstance enemy, double id) {
        enemyIDs.add(id);
        enemyInstanceHashMap.put(id, enemy);
    }

    public void addWeaponEffect(WeaponEffectInstance weaponEffect, double id) {
        weaponEffectIDs.add(id);
        weaponEffectInstanceHashMap.put(id, weaponEffect);
    }

    public void addEnvironmental(EnvironmentalInstance environmental, double id) {
        environmentalIDs.add(id);
        environmentalInstanceHashMap.put(id, environmental);
    }

    public void addItemDrop(ItemDropInstance itemDrop, double id) {
        itemDropIDs.add(id);
        itemDropInstanceHashMap.put(id, itemDrop);
    }

    public Image getBackground() {
        return background;
    }

    public ArrayList<Double> getPlayerIDs() {
        return playerIDs;
    }

    public ArrayList<Double> getNpcIDs() {
        return npcIDs;
    }

    public ArrayList<Double> getEnemyIDs() {
        return enemyIDs;
    }

    public ArrayList<Double> getWeaponEffectIDs() {
        return weaponEffectIDs;
    }

    public ArrayList<Double> getEnvironmentalIDs() {
        return environmentalIDs;
    }

    public ArrayList<Double> getItemDropIDs() {
        return itemDropIDs;
    }

    public PlayerInstance getPlayer(double key) {
        return playerInstanceHashMap.get(key);
    }

    public EnemyInstance getEnemy(double key) {
        return enemyInstanceHashMap.get(key);
    }

    public NPCInstance getNPC(double key) {
        return npcInstanceHashMap.get(key);
    }

    public EnvironmentalInstance getEnvironmental(double key) {
        return environmentalInstanceHashMap.get(key);
    }

    public WeaponEffectInstance getWeaponEffectInstance(double key) {
        return weaponEffectInstanceHashMap.get(key);
    }

    public ItemDropInstance getItemDrop(double key) {
        return itemDropInstanceHashMap.get(key);
    }

    public ArrayList<Structure> getStructures() {
        return structures;
    }

    public void removePlayer(double id) {
        playerInstanceHashMap.remove(id);
        for (int i=0; i<playerIDs.size(); i++) {
            if (playerIDs.get(i) == id) {
                playerIDs.remove(i);
                return;
            }
        }
    }

    public void removeNpc(double id) {
        npcInstanceHashMap.remove(id);
        for (int i=0; i<npcIDs.size(); i++) {
            if (npcIDs.get(i) == id) {
                npcIDs.remove(i);
                return;
            }
        }
    }

    public void removeEnemy(double id) {
        enemyInstanceHashMap.remove(id);
        for (int i=0; i<enemyIDs.size(); i++) {
            if (enemyIDs.get(i) == id) {
                enemyIDs.remove(i);
                return;
            }
        }
    }

    public void removeWeaponEffect(double id) {
        weaponEffectInstanceHashMap.remove(id);
        for (int i=0; i<weaponEffectIDs.size(); i++) {
            if (weaponEffectIDs.get(i) == id) {
                weaponEffectIDs.remove(i);
                return;
            }
        }
    }

    public void removeEnvironmental(double id) {
        environmentalInstanceHashMap.remove(id);
        for (int i=0; i<environmentalIDs.size(); i++) {
            if (environmentalIDs.get(i) == id) {
                environmentalIDs.remove(i);
                return;
            }
        }
    }

    public void removeItemDrop(double id) {
        itemDropInstanceHashMap.remove(id);
        for (int i=0; i<itemDropIDs.size(); i++) {
            if (itemDropIDs.get(i) == id) {
                itemDropIDs.remove(i);
                return;
            }
        }
    }

    public void removeStructure(String name) {
        for (int i=0;i<structures.size();i++) {
            if (structures.get(i).getName().equals(name)) {
                structures.remove(i);
                return;
            }
        }
    }

    public int[][] getMap() {
        return this.map;
    }
}
