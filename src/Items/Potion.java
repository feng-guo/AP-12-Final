package Items;

import java.awt.*;

class Potion extends Consumable{
    private String effect;
    private int duration;

    public Potion(String name, String description, Image sprite, int healthGain, String effect, int duration){
        super(name, description, sprite, healthGain);
        this.effect = effect;
        this.duration = duration;
    }

    public void use(){
        //if effect is whatever, do this
    }

    public String getEffect() {
        return effect;
    }

    public int getDuration() {
        return duration;
    }
}
