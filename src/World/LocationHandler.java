/**
 * LocationHandler
 * Class to handle all interactions with entities in the game world
 */
package World;

import Entities.*;
import Items.Item;
import Items.Stack;
import Items.Tool;
import Items.Weapon;

import java.awt.*;
import java.util.*;
import java.util.List;

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
            attackCollision();
            checkEnemies();
            checkEnvironmentals();
            
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
        Thread t= new Thread(weaponEffect);
        t.start();
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

    public Location getLocation() {
        return location;
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
                    ItemDropInstance itemDropInstance = itemDropInstanceHashMap.get(itemDropIDs.get(j));
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
                double id = (double) System.nanoTime();
                ItemDropInstance itemDropInstance = new ItemDropInstance(e.getX(), e.getY(), itemDrop, id);
                addItemDrop(itemDropInstance);
                return;
            }
        }
    }

    /**
     * playerAttack
     * Creates the weapon effect from the player
     * @param x x position of the attack
     * @param y y position of the attack
     * @param playerHandler the handler of the player who attacks
     */
    public void playerAttack(int x, int y, PlayerHandler playerHandler) {
        double currentTime = System.nanoTime()/1e+9;
        double delta = currentTime - playerHandler.getLastWeaponUse();
        double max = (-1/Math.pow(playerHandler.getPlayerInstance().getDexterity()/10, 2)+1) +1.75;
        if (delta > 2.75 - max) {
            Weapon weapon = (Weapon) playerHandler.getPlayerInstance().getInventory().getCurrentItem().getItem();
            int newX = playerHandler.getPlayerInstance().getX() + playerHandler.getPlayerInstance().getEntity().getLength()/2;
            int newY = playerHandler.getPlayerInstance().getY() + playerHandler.getPlayerInstance().getEntity().getWidth()/2;
            WeaponEffect weaponEffect = new WeaponEffect(Toolkit.getDefaultToolkit().createImage("Bread.png"), 30, 30, weapon);
            WeaponEffectInstance weaponEffectInstance = new WeaponEffectInstance(newX, newY, weaponEffect, System.nanoTime()/1e+9, x, y, (double)System.nanoTime());
            WeaponEffectHandler weaponEffectHandler = new WeaponEffectHandler(weaponEffectInstance, this);
            addWeaponEffect(weaponEffectHandler);

            playerHandler.setLastWeaponUse(currentTime);
            
            /*
            enemyHandlerHashMap.forEach((k,enemy)->{
                if ((Math.abs(weaponEffectHandler.getWeaponEffectInstance().getX -enemy.getEnemyInstance().getX)<=100)&&
                        (Math.abs(weaponEffectHandler.getWeaponEffectInstance().getY -enemy.getEnemyInstance().getY)<=100)
                ) {
                    enemy.getEnemyInstance()  .damage(weaponEffectHandler.getWeaponEffectInstance().getWeaponEffect().getDamage());
                    return;
                }
            });
            */
        }
    }

    /**
     * enemyAttack
     * Creates the weapon effect from the enemy
     * @param x x position of the attack
     * @param y y position of the attack
     * @param enemyHandler the handler of the enemy who attacks
     */
    public void enemyAttack(int x, int y, EnemyHandler enemyHandler) {
        double currentTime = System.nanoTime()/1e+9;
        double delta = currentTime - enemyHandler.getLastWeaponUse();
        double max = (-1/Math.pow(enemyHandler.getEnemyInstance().getDexterity()/10, 2)+1) +1.75;
        if (delta > 2.75 - max) {
            Weapon weapon = enemyHandler.getEnemyInstance().getWeapon();
            WeaponEffect weaponEffect = new WeaponEffect(null, 20, 20, weapon); //THIS IS NOT REAL CODE
            WeaponEffectInstance weaponEffectInstance = new WeaponEffectInstance(enemyHandler.getEnemyInstance().getX(), enemyHandler.getEnemyInstance().getY(), weaponEffect, System.nanoTime()/1e+9, x, y, (double)System.nanoTime());
            WeaponEffectHandler weaponEffectHandler = new WeaponEffectHandler(weaponEffectInstance, this);
            weaponEffectIDs.add(weaponEffectInstance.getID());
            weaponEffectHandlerHashMap.put(weaponEffectInstance.getID(),weaponEffectHandler);
            enemyHandler.setLastWeaponUse(currentTime);

            /*
            playerHandlerHashMap.forEach((k,player)->{
                if ((Math.abs(weaponEffectHandler.getWeaponEffectInstance().getX -player.getPlayerInstance().getX)<=100)&&
                        (Math.abs(weaponEffectHandler.getWeaponEffectInstance().getY -player.getPlayerInstance().getY)<=100)
                ) {
                    player.getPlayerInstance().damage(weaponEffectHandler.getWeaponEffectInstance().getWeaponEffect().getDamage());
                    return;
                }
            });
           */
        }
    }
    
    /*
    public void attackCollision(){
     weaponEffectHandlerHashMap.forEach((wKey,weaponEffectHandler)->
                                        {enemyHandlerHashMap.forEach((eKey,enemy) ->{
       if ((Math.abs(weaponEffectHandler.getWeaponEffectInstance().getX -enemy.getEnemyInstance().getX)<=100)&&
           (Math.abs(weaponEffectHandler.getWeaponEffectInstance().getY -enemy.getEnemyInstance().getY)<=100)
          ) {
         enemy.getEnemyInstance().damage(weaponEffectHandler.getWeaponEffectInstance().getWeaponEffect().getDamage());
         //return;
       }                      
     })
                                          
     }
                                        
                                        {playerHandlerHashMap.forEach((pKey,player)->{
                                          if ((Math.abs(weaponEffectHandler.getWeaponEffectInstance().getX -player.getPlayerInstance().getX)<=100)&&
                                              (Math.abs(weaponEffectHandler.getWeaponEffectInstance().getY -player.getPlayerInstance().getY)<=100)
                                             ) {
                                            player.getPlayerInstance().damage(weaponEffectHandler.getWeaponEffectInstance().getWeaponEffect().getDamage());
                                            //return;
                                          }
                                        }
                                                                      })
    }
    */

    /**
     * attackCollision
     * Goes through all weapon effects in play and see if they interact with an enemy
     */
    public void attackCollision(){
        for (int i=0;i<weaponEffectIDs.size();i++){
            WeaponEffectInstance  weaponEffect= weaponEffectHandlerHashMap.get(weaponEffectIDs.get(i)).getWeaponEffectInstance();
            for (int j=0;j<enemyIDs.size();j++){
                EnemyInstance enemy=enemyHandlerHashMap.get(enemyIDs.get(i)).getEnemyInstance();
                if (Math.abs(enemy.getX()+(enemy.getEnemy().getWidth()/2)-weaponEffect.getX()+(weaponEffect.getWeaponEffect().getWidth()/2))<=100 &&
                        Math.abs(enemy.getY()+(enemy.getEnemy().getLength()/2)-weaponEffect.getY()+(weaponEffect.getWeaponEffect().getLength()/2))<=100){
                    enemy.damage(weaponEffect.getWeaponEffect().getDamage());
                }
            }
//            for (int j=0;j<playerIDs.size();j++){
//                PlayerInstance player=playerHandlerHashMap.get(playerIDs.get(i)).getPlayerInstance();
//                if (Math.abs(player.getX()+(player.getPlayer().getWidth()/2)-weaponEffect.getX()+(weaponEffect.getWeaponEffect().getWidth()/2))<=100 &&
//                        Math.abs(player.getY()+(player.getPlayer().getLength()/2)-weaponEffect.getY()+(weaponEffect.getWeaponEffect().getLength()/2))<=100){
//                    player.damage(weaponEffect.getWeaponEffect().getDamage());
//                }
//            }
        }
    }
    private void checkEnvironmentals() {
        for (int i=0; i<environmentalIDs.size(); i++){
            double id = environmentalIDs.get(i);
            if (environmentalInstanceHashMap.get(id).getCurrentDurability() <= 0) {
                destroyEnvironmental(id);
                i--;
            }
        }
    }

    private void destroyEnvironmental(double id) {
        EnvironmentalInstance environmentalInstance = environmentalInstanceHashMap.get(id);
        ItemDrop itemDrop = new ItemDrop(environmentalInstance.getEnvironmental().getDrop());
        ItemDropInstance itemDropInstance = new ItemDropInstance(environmentalInstance.getX(), environmentalInstance.getY(), itemDrop, (double)System.nanoTime());
        addItemDrop(itemDropInstance);
        removeEnvironmental(id);
    }

    /**
     * hitEnvironmental
     * does damage to the environment
     * @param tool the tool used by the player
     * @param playerHandler the player who performed the attack
     */
    public void hitEnvironmental(Tool tool, PlayerHandler playerHandler) {
        double currentTime = System.nanoTime()/1e+9;
        double delta = currentTime - playerHandler.getLastWeaponUse();
        double max = (-1/Math.pow(playerHandler.getPlayerInstance().getDexterity()/10, 2)+1) +1.75;
        if (delta > 2.75 - max) {
            PlayerInstance playerInstance = playerHandler.getPlayerInstance();
            int playerX = (playerInstance.getX() + playerInstance.getEntity().getWidth())/2;
            int playerY = (playerInstance.getY() + playerInstance.getEntity().getLength())/2;
            playerHandler.setLastWeaponUse(currentTime);
            for (int i=0; i<environmentalIDs.size(); i++) {
                EnvironmentalInstance environmentalInstance = environmentalInstanceHashMap.get(environmentalIDs.get(i));
                int environmentalX = (environmentalInstance.getX() + environmentalInstance.getEntity().getWidth())/2;
                int environmentalY = (environmentalInstance.getY() + environmentalInstance.getEntity().getLength())/2;
                int deltaX = Math.abs(environmentalX-playerX);
                int deltaY = Math.abs(environmentalY-playerY);
                int maxDeltaX = environmentalInstance.getEntity().getWidth()/2 + playerInstance.getEntity().getWidth()/2 + 10;
                int maxDeltaY = environmentalInstance.getEntity().getLength()/2 + playerInstance.getEntity().getLength()/2 + 10;
                if (deltaX < maxDeltaX && deltaY < maxDeltaY) {
                    environmentalInstance.hit(tool.getToolHit());
                    return;
                }
            }
        }
    }
}
