package NPCMechanics;

import Items.Item;

public class Trade {
    private Item playerOffer;
    private Item NPCoffer;
    private int playerOfferAmount;
    private int NPCOfferAmount;

    public Trade(Item playerOffer, Item NPCoffer, int playerOfferAmount, int NPCOfferAmount) {
        this.playerOffer = playerOffer;
        this.NPCoffer = NPCoffer;
        this.playerOfferAmount = playerOfferAmount;
        this.NPCOfferAmount = NPCOfferAmount;
    }

    public Item getPlayerOffer() {
        return playerOffer;
    }

    public Item getNPCoffer() {
        return NPCoffer;
    }

    public int getPlayerOfferAmount() {
        return playerOfferAmount;
    }

    public int getNPCOfferAmount() {
        return NPCOfferAmount;
    }
}
