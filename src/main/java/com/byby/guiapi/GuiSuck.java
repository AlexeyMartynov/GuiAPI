package com.byby.guiapi;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiSuck extends Gui {

    public GuiSuck() {
        super("fdsafd", 27);
    }

    @Override
    public void onGuiClick(InventoryClickEvent event) {

    }

    @Override
    public boolean create(Player player) {
        return true;
    }
}
