package Entities;

public class NPCHandler extends CharacterHandler {
    private NPCInstance npcInstance;

    public NPCHandler(NPCInstance npcInstance) {
        super(npcInstance);
        this.npcInstance = npcInstance;
    }

    public NPCInstance getNpcInstance() {
        return npcInstance;
    }

    @Override
    public void move() {
        //code here
    }
}
