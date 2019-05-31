package World;

import Entities.*;

import java.util.ArrayList;
import java.util.HashMap;

public class LocationHandler implements Runnable {
    private Location location;
    private ArrayList<Double> playerIDs;
    private ArrayList<Double> npcIDs;
    private ArrayList<Double> enemyIDs;
    private ArrayList<Double> weaponEffectIDs;
    private ArrayList<Double> environmentalIDs;
    private ArrayList<Double> itemDropIDs;
    private HashMap<Double, PlayerHandler> playerHandlerHashMap;
    private HashMap<Double, NPCHandler> npcHandlerHashMap;
    private HashMap<Double, EnemyHandler> enemyHandlerHashMap;
    private HashMap<Double, WeaponEffectHandler> weaponEffectHandlerHashMap;
    private HashMap<Double, EnvironmentalInstance> environmentalInstanceHashMap;
    private HashMap<Double, ItemDropInstance> itemDropInstanceHashMap;

    private ArrayList<Thread> thread; //HANDLE THIS BETTER!!!

    public LocationHandler(Location location) {
        this.location = location;
        playerIDs = new ArrayList<>();
        npcIDs = new ArrayList<>();
        enemyIDs = new ArrayList<>();
        weaponEffectIDs = new ArrayList<>();
        environmentalIDs = new ArrayList<>();
        itemDropIDs = new ArrayList<>();
        playerHandlerHashMap = new HashMap<>();
        npcHandlerHashMap = new HashMap<>();
        enemyHandlerHashMap = new HashMap<>();
        weaponEffectHandlerHashMap = new HashMap<>();
        environmentalInstanceHashMap = new HashMap<>();
        itemDropInstanceHashMap = new HashMap<>();
        thread = new ArrayList<>();
    }
    
    public void run() {
        //Code here
        //Might want to handle threads here better
    }

    public void addPlayer(PlayerHandler player, double id) {
        playerIDs.add(id);
        playerHandlerHashMap.put(id, player);
        location.addPlayer(player.getPlayerInstance(), id);
    }

    public void addNpc(NPCHandler npc, double id) {
        npcIDs.add(id);
        npcHandlerHashMap.put(id, npc);
        location.addNpc(npc.getNpcInstance(), id);
    }

    public void addEnemy(EnemyHandler enemy, double id) {
        enemyIDs.add(id);
        enemyHandlerHashMap.put(id, enemy);
        location.addEnemy(enemy.getEnemyInstance(), id);
    }

    public void addWeaponEffect(WeaponEffectHandler weaponEffect, double id) {
        weaponEffectIDs.add(id);
        weaponEffectHandlerHashMap.put(id, weaponEffect);
        location.addWeaponEffect(weaponEffect.getWeaponEffectInstance(), id);
    }

    public void addEnvironmental(EnvironmentalInstance environmental, double id) {
        environmentalIDs.add(id);
        environmentalInstanceHashMap.put(id, environmental);
        location.addEnvironmental(environmental, id);
    }

    public void addItemDrop(ItemDropInstance itemDrop, double id) {
        itemDropIDs.add(id);
        itemDropInstanceHashMap.put(id, itemDrop);
        location.addItemDrop(itemDrop, id);
    }

    public PlayerHandler getPlayer(double key) {
        return playerHandlerHashMap.get(key);
    }

    public EnemyHandler getEnemy(double key) {
        return enemyHandlerHashMap.get(key);
    }

    public NPCHandler getNPC(double key) {
        return npcHandlerHashMap.get(key);
    }

    public WeaponEffectHandler getWeaponEffectHandler(double key) {
        return weaponEffectHandlerHashMap.get(key);
    }

    public EnvironmentalInstance getEnvironmental(double key) {
        return environmentalInstanceHashMap.get(key);
    }

    public ItemDropInstance getItemDrop(double key) {
        return itemDropInstanceHashMap.get(key);
    }

    public void removePlayer(double id) {
        playerHandlerHashMap.remove(id);
        location.removePlayer(id);
        for (int i=0; i<playerIDs.size(); i++) {
            if (playerIDs.get(i) == id) {
                playerIDs.remove(i);
                return;
            }
        }
    }

    public void removeNpc(double id) {
        npcHandlerHashMap.remove(id);
        location.removeNpc(id);
        for (int i=0; i<npcIDs.size(); i++) {
            if (npcIDs.get(i) == id) {
                npcIDs.remove(i);
                return;
            }
        }
    }

    public void removeEnemy(double id) {
        enemyHandlerHashMap.remove(id);
        location.removeEnemy(id);
        for (int i=0; i<enemyIDs.size(); i++) {
            if (enemyIDs.get(i) == id) {
                enemyIDs.remove(i);
                return;
            }
        }
    }

    public void removeWeaponEffect(double id) {
        weaponEffectHandlerHashMap.remove(id);
        location.removeWeaponEffect(id);
        for (int i=0; i<weaponEffectIDs.size(); i++) {
            if (weaponEffectIDs.get(i) == id) {
                weaponEffectIDs.remove(i);
                return;
            }
        }
    }

    public void removeEnvironmental(double id) {
        environmentalInstanceHashMap.remove(id);
        location.removeEnvironmental(id);
        for (int i=0; i<environmentalIDs.size(); i++) {
            if (environmentalIDs.get(i) == id) {
                environmentalIDs.remove(i);
                return;
            }
        }
    }

    public void removeItemDrop(double id) {
        itemDropInstanceHashMap.remove(id);
        location.removeItemDrop(id);
        for (int i=0; i<itemDropIDs.size(); i++) {
            if (itemDropIDs.get(i) == id) {
                itemDropIDs.remove(i);
                return;
            }
        }
    }

    public void addToThread(EntityHandler e) {
        Thread t = new Thread(e);
        thread.add(t);
    }
}
