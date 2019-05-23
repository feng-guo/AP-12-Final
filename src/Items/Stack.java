package Items;

public class Stack {
    private int stackAmount;
    private int maxStack;
    private Item item;

    public Stack (int stackAmount, Item item) {
        //This assumes that the amount <= maxStack
        this.stackAmount = stackAmount;
        this.item = item;
        this.maxStack = item.getMaxStack();
    }

    public int getStackAmount() {
        return stackAmount;
    }

    public void setStackAmount(int stackAmount) {
        this.stackAmount = stackAmount;
    }

    public Item getItem() {
        return item;
    }

    public int add(int amount) {
        //Adds items to stack, returns extra if over stackMax
        if (stackAmount+amount <= maxStack) {
            stackAmount += amount;
            return 0;
        } else {
            int initAmount = stackAmount;
            stackAmount = maxStack;
            return initAmount+amount - stackAmount;
        }
    }

    public boolean remove(int amount) {
        //Returns boolean if it is successful
        if (amount > stackAmount) {
            return false;
        } else {
            stackAmount -= amount;
            return true;
        }
    }
}
