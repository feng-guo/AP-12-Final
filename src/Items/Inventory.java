package Items;

import java.util.ArrayList;

public class Inventory {
    private Stack[] inventory;
    private int maxSlots;

    public Inventory(int maxSlots) {
        this.inventory = new Stack[36];
        this.maxSlots = maxSlots;
    }

    public int getMaxSlots() {
        return maxSlots;
    }

    public Stack add(Stack stack) {
        for (int i=0; i<inventory.length; i++) {
            try {
                Item inventoryItem = inventory[i].getItem();
                if (inventoryItem.equals(stack.getItem()) && inventory[i].getStackAmount() < inventoryItem.getMaxStack()) {
                    if (stack.getStackAmount() + inventory[i].getStackAmount() > inventoryItem.getMaxStack() && inventoryItem.getMaxStack() > 1) {
                        int initialAmount = inventory[i].getStackAmount();
                        inventory[i].setStackAmount(inventoryItem.getMaxStack());
                        stack.setStackAmount((initialAmount + stack.getStackAmount()) - inventoryItem.getMaxStack());
                    } else {
                        inventory[i].add(stack.getStackAmount());
                        return null;
                    }
                }
            } catch (NullPointerException e) {
                //Is empty
            }
        }

        for (int i=0; i<inventory.length;i++) {
            if (inventory[i] == null) {
                inventory[i] = stack;
                return null;
            }
        }
        return stack;
    }

    public boolean add(Stack stack, int i) {
        if (inventory[i] == null) {
            inventory[i] = stack;
            return true;
        } else {
            return false;
        }
    }

    public Stack remove(int index) {
        if (index >= inventory.length) {
            return null;
        } else {
            Stack toReturn = inventory[index];
            inventory[index] = null;
            return toReturn;
        }
    }

    public Stack drop(int index, int amount) {
        if (index >= inventory.length) {
            return null;
        } else if (inventory[index].getStackAmount() < amount) {
            return inventory[index];
        } else {
            inventory[index].setStackAmount(inventory[index].getStackAmount() - amount);
            return new Stack(amount, inventory[index].getItem());
        }
    }

    public Stack get(int i) {
        return inventory[i];
    }
}
