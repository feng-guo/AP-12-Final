/**
 * Class that defines a handler for specific instances of NPCs
 * @author David Bao
 * @author Armanya Dalmia
 * @author Feng Guo
 * @author Victor Lin
 * @author Arjun Pillai
 */
package Entities;

import World.LocationHandler;

public class NPCHandler extends CharacterHandler {
    private NPCInstance npcInstance;

    public NPCHandler(NPCInstance npcInstance, LocationHandler location) {
        super(npcInstance, location);
        this.npcInstance = npcInstance;
    }

    public NPCInstance getNpcInstance() {
        return npcInstance;
    }

    @Override
    public void move() {
        //code here
    }

    @Override
    public void run() {
        //code here
    }
}
