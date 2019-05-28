package World;

import Entities.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

abstract public class Location {
    Image background;
    ArrayList<Double> playerIDs;
    ArrayList<Double> npcIDs;
    ArrayList<Double> enemyIDs;
    ArrayList<Double> weaponEffectIDs;
    ArrayList<Double> environmentalIDs;
    ArrayList<Double> itemDropIDs;
    HashMap<Double, PlayerHandler> playerHandlerHashMap;
    HashMap<Double, NPCHandler> npcHandlerHashMap;
    HashMap<Double, EnemyHandler> enemyHandlerHashMap;
    HashMap<Double, WeaponEffectHandler> weaponEffectHandlerHashMap;
    HashMap<Double, EnvironmentalInstance> environmentalInstanceHashMap;
    HashMap<Double, ItemDropInstance> itemDropInstanceHashMap;

    public Location(Image background) {
        this.background = background;
        playerHandlerHashMap = new HashMap<>();
        npcHandlerHashMap = new HashMap<>();
        enemyHandlerHashMap = new HashMap<>();
        weaponEffectHandlerHashMap = new HashMap<>();
        environmentalInstanceHashMap = new HashMap<>();
        itemDropInstanceHashMap = new HashMap<>();
    }


}
