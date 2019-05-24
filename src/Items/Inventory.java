package Items;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Stack> inventory;
    private int maxSlots;

    public Inventory(int maxSlots) {
        this.inventory = new ArrayList<>();
        this.maxSlots = maxSlots;
    }

    public int getMaxSlots() {
        return maxSlots;
    }

    public Stack add(Stack stack) {
        for (int i=0; i<inventory.size(); i++) {
            Item inventoryItem = inventory.get(i).getItem();
            if (inventoryItem.equals(stack.getItem()) && inventory.get(i).getStackAmount() < inventoryItem.getMaxStack()) {
                if (stack.getStackAmount() + inventory.get(i).getStackAmount() > inventoryItem.getMaxStack() && inventoryItem.getMaxStack() > 1) {
                    int initialAmount = inventory.get(i).getStackAmount();
                    inventory.get(i).setStackAmount(inventoryItem.getMaxStack());
                    stack.setStackAmount((initialAmount + stack.getStackAmount()) - inventoryItem.getMaxStack());
                } else {
                    inventory.get(i).setStackAmount(inventoryItem.getMaxStack());
                    return null;
                }
            }
        }
        if (inventory.size() < maxSlots) {
            inventory.add(stack);
            return null;
        } else {
            return stack;
        }
    }

    public Stack remove(int index) {
        if (index >= inventory.size()) {
            return null;
        } else {
            return inventory.get(index);
        }
    }
    
    public Stack drop(int index, int amount) {
        if (index >= inventory.size()) {
            return null;
        } else if (inventory.get(index).getStackAmount() < amount) {
            return inventory.get(index);
        } else {
            inventory.get(index).setStackAmount(inventory.get(index).getStackAmount() - amount);
            return new Stack(amount, inventory.get(index).getItem());
        }
    }
}
