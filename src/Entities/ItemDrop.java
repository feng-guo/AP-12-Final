package Entities;

import Items.Stack;

public class ItemDrop extends Entity {
    private Stack stack;

    public ItemDrop(Stack stack) {
        super(stack.getItem().getSprite(), 64, 64);
        this.stack = stack;
    }

    public Stack getStack() {
        return stack;
    }
}
