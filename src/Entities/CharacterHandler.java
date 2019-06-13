/**
 * abstract class to handle the characters
 * @author David Bao
 * @author Armanya Dalmia
 * @author Feng Guo
 * @author Victor Lin
 * @author Arjun Pillai
 */
package Entities;

import World.Location;
import World.LocationHandler;

public abstract class CharacterHandler extends EntityHandler {
    private CharacterInstance characterInstance;

    public CharacterHandler(CharacterInstance characterInstance, LocationHandler location) {
        super(characterInstance, location);
        this.characterInstance = characterInstance;
    }

    /**
     * getCharacterInstance
     * @return CharacterInstance the character associated with the handler
     */
    public CharacterInstance getCharacterInstance() {
        return characterInstance;
    }

    //remove
    abstract public void move();

    //public abstract void run();
}
