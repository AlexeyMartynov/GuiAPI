package io.github.alexeymartynov.guiapi;

import org.bukkit.inventory.ItemStack;

public class Button extends ItemStack {

    private GuiContent content;
    private int slot;

    public Button(GuiContent content)
    {
        this.content = content;
        slot = content.getSlot();
        ItemStack item = content.getItem();
        setType(item.getType());
        setItemMeta(item.getItemMeta());
        setData(item.getData());
        setAmount(item.getAmount());
    }

    public void setSlot(int slot) { this.slot = slot; }

    public GuiContent getContent() { return content; }

    public int getSlot() { return slot; }
}
