package Entities;

public abstract class CharacterHandler extends EntityHandler {
    private CharacterInstance characterInstance;

    public CharacterHandler(CharacterInstance characterInstance) {
        super(characterInstance);
        this.characterInstance = characterInstance;
    }

    public CharacterInstance getCharacterInstance() {
        return characterInstance;
    }

    abstract public void move();
}
