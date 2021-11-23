package io.github.alexeymartynov.guiapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class GuiHandler implements Listener {
	
	@EventHandler
	public void onGuiClick(InventoryClickEvent event) 
	{ 
		if(event.getInventory().getHolder() instanceof GuiHolder) 
		{
			GuiHolder holder = (GuiHolder) event.getInventory().getHolder();
			holder.onGuiClick(event); 
		}
	}
}
