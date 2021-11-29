package io.github.alexeymartynov.guiapi;

import io.github.alexeymartynov.util.UtilItem;
import net.minecraft.server.v1_12_R1.Item;
import net.minecraft.server.v1_12_R1.MinecraftKey;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public enum GuiContent {

    PREVIOUS(26),
    NEXT(8),
    BACK(0),
    PAGE(17),
    ACCEPT(12),
    DECLINE(14);

    private int slot;

    private GuiContent(int slot) { this.slot = slot; }

    public int getSlot() { return slot; }

    public ItemStack getItem()
    {
        GuiApi instance = GuiApi.getInstance();
        return new ItemStack(UtilItem.create(CraftItemStack.asNewCraftStack(Item.REGISTRY.get(new MinecraftKey(instance.getConfig().getString(this.toString() + ".id")))).getType(),
                instance.getConfig().getString(this.toString() + ".name")));
    }
}
