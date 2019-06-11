package Items;

import Entities.PlayerInstance;

import java.awt.*;

public class Potion extends Consumable{
    private String effect;
    private int duration;

    public Potion(String name, String description, Image sprite, int healthGain, String effect, int duration){
        super(name, description, 16, sprite, healthGain);
        this.effect = effect;
        this.duration = duration;
    }

    public void use(PlayerInstance player) {
        //We will add effects LATER
        player.heal(super.getHealthGain());
    }

    public String getEffect() {
        return effect;
    }

    public int getDuration() {
        return duration;
    }
}
