package Entities;

import World.Location;

public class NPCHandler extends CharacterHandler {
    private NPCInstance npcInstance;

    public NPCHandler(NPCInstance npcInstance, Location location) {
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
