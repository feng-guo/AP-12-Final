package World;

import Entities.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

abstract public class Location {
    private Image background;
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

    public Location(Image background) {
        this.background = background;
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
    }
}
