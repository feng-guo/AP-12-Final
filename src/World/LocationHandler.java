package World;

import Entities.*;
import Items.Item;
import Items.Stack;

import java.lang.reflect.Array;
import java.util.*;

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
        while(true) {
            checkPlayerItemDrops();
            revalidatePlayerItemDrops();
            checkEnemies();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {

            }
        }
    }

    public void addPlayer(PlayerHandler player) {
        double id = player.getEntityInstance().getID();
        playerIDs.add(id);
        playerHandlerHashMap.put(id, player);
        location.addPlayer(player.getPlayerInstance(), id);
    }

    public void addNpc(NPCHandler npc) {
        double id = npc.getEntityInstance().getID();
        npcIDs.add(id);
        npcHandlerHashMap.put(id, npc);
        location.addNpc(npc.getNpcInstance(), id);
    }

    public void addEnemy(EnemyHandler enemy) {
        double id = enemy.getEntityInstance().getID();
        enemyIDs.add(id);
        addToThread(enemy);
        enemyHandlerHashMap.put(id, enemy);
        location.addEnemy(enemy.getEnemyInstance(), id);
    }

    public void addWeaponEffect(WeaponEffectHandler weaponEffect) {
        double id = weaponEffect.getEntityInstance().getID();
        weaponEffectIDs.add(id);
        weaponEffectHandlerHashMap.put(id, weaponEffect);
        location.addWeaponEffect(weaponEffect.getWeaponEffectInstance(), id);
    }

    public void addEnvironmental(EnvironmentalInstance environmental) {
        double id = environmental.getID();
        environmentalIDs.add(id);
        environmentalInstanceHashMap.put(id, environmental);
        location.addEnvironmental(environmental, id);
    }

    public void addItemDrop(ItemDropInstance itemDrop) {
        double id = itemDrop.getID();
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
        t.start();
        thread.add(t);
    }

    private void checkPlayerItemDrops() {
        for (int i=0; i<playerIDs.size(); i++) {
            PlayerInstance player = playerHandlerHashMap.get(playerIDs.get(i)).getPlayerInstance();
            int playerX = (player.getX()*2 + player.getEntity().getWidth())/2;
            int playerY = (player.getY()*2 + player.getEntity().getLength())/2;
            if (player.getNearbyItem() == null) {
                for (int j = 0; j < itemDropIDs.size(); j++) {
                    ItemDropInstance itemDropInstance = itemDropInstanceHashMap.get(itemDropIDs.get(i));
                    int dropX = (itemDropInstance.getX()*2 + itemDropInstance.getEntity().getWidth())/2;
                    int dropY = (itemDropInstance.getY()*2 + itemDropInstance.getEntity().getLength())/2;
                    if (Math.abs(playerX-dropX) < 64 && Math.abs(playerY-dropY) < 64) {
                        player.setNearbyItem(itemDropInstance);
                    }
                }
            }
        }
    }

    private void revalidatePlayerItemDrops() {
        for (int i=0; i<playerIDs.size(); i++) {
            PlayerInstance player = playerHandlerHashMap.get(playerIDs.get(i)).getPlayerInstance();
            if (player.getNearbyItem() != null) {
                int playerX = (player.getX()*2 + player.getEntity().getWidth())/2;
                int playerY = (player.getY()*2 + player.getEntity().getLength())/2;
                ItemDropInstance itemDropInstance = player.getNearbyItem();
                int dropX = (itemDropInstance.getX()*2 + itemDropInstance.getEntity().getWidth())/2;
                int dropY = (itemDropInstance.getY()*2 + itemDropInstance.getEntity().getLength())/2;
                if (Math.abs(playerX-dropX) > 64 || Math.abs(playerY-dropY) > 64) {
                    player.setNearbyItem(null);
                }
            }
            if (playerIDs.size() > 1) {
                System.out.println("Penis");
            }
        }
    }

    private void checkEnemies() {
        for (int i=0; i<enemyIDs.size(); i++) {
            EnemyInstance e = enemyHandlerHashMap.get(enemyIDs.get(i)).getEnemyInstance();
            if (e.getCurrentHealth() <= 0) {
                killEnemy(e);
                removeEnemy(enemyIDs.get(i));
                i--;
            }
        }
    }

    private void killEnemy(EnemyInstance e) {
        HashMap<Double, Stack> itemHashMap = e.getEnemy().getLootTable();
        double random = Math.random();
        Object[] bad = itemHashMap.keySet().toArray();
        Double[] keys = new Double[bad.length];
        for (int i=0; i<keys.length; i++) {
            keys[i] = (Double)bad[i];
        }
        List<Double> list = Arrays.asList(keys);
        Collections.sort(list);
        for (int i=0; i<list.size(); i++) {
            if (random < list.get(i)) {
                ItemDrop itemDrop = new ItemDrop(itemHashMap.get(list.get(i)));
                ItemDropInstance itemDropInstance = new ItemDropInstance(e.getX(), e.getY(), itemDrop, (double)System.nanoTime());
                addItemDrop(itemDropInstance);

                return;
            }
        }
    }

    public void killEverything() {
        for ( int i= 0; i<enemyIDs.size(); i++) {
            enemyHandlerHashMap.get(enemyIDs.get(i)).getEnemyInstance().setCurrentHealth(0);
        }
    }
}
