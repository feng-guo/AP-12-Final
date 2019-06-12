package Entities;

import World.Location;
import World.LocationHandler;

public abstract class CharacterHandler extends EntityHandler {
    private CharacterInstance characterInstance;

    public CharacterHandler(CharacterInstance characterInstance, LocationHandler location) {
        super(characterInstance, location);
        this.characterInstance = characterInstance;
    }

    public CharacterInstance getCharacterInstance() {
        return characterInstance;
    }

    abstract public void move();

    //public abstract void run();
}
