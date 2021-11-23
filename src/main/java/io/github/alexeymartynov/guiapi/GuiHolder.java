package io.github.alexeymartynov.guiapi;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class GuiHolder implements InventoryHolder {
	
	public Inventory inventory;
	
	public GuiHolder(String title, int size) 
	{
		inventory = Bukkit.createInventory(this, size, title);
	}
	
	@Override
	public Inventory getInventory() 
	{
		return inventory;
	}
	
	public abstract void onGuiClick(InventoryClickEvent event);
}
