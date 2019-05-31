package Entities;

import World.Location;

public abstract class CharacterHandler extends EntityHandler {
    private CharacterInstance characterInstance;

    public CharacterHandler(CharacterInstance characterInstance, Location location) {
        super(characterInstance, location);
        this.characterInstance = characterInstance;
    }

    public CharacterInstance getCharacterInstance() {
        return characterInstance;
    }

    abstract public void move();

    //public abstract void run();
}
