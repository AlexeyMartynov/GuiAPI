package io.guthub.alexeymartynov.guiapi;

import io.guthub.alexeymartynov.util.UtilItem;
import net.minecraft.server.v1_12_R1.Item;
import net.minecraft.server.v1_12_R1.MinecraftKey;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public enum GuiContent {

    PREVIOUS,
    NEXT,
    BACK,
    PAGE,
    ACCEPT,
    DECLINE;

    public ItemStack getItem()
    {
        GuiApi instance = GuiApi.getInstance();
        return new ItemStack(UtilItem.create(CraftItemStack.asNewCraftStack(Item.REGISTRY.get(new MinecraftKey(instance.getConfig().getString(this.toString() + ".id")))).getType(),
                instance.getConfig().getString(this.toString() + ".name")));
    }
}
