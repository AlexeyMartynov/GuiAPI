package io.github.alexeymartynov.guiapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class Gui extends GuiHolder { 
	
	protected Button back = new Button(GuiContent.BACK);
	
	private static Map<Player, List<Inventory>> queue = new HashMap<>();
	
	public static void onEnable(JavaPlugin plugin)
	{
		new BukkitRunnable() 
        {
        	public void run() 
        	{
        		if(queue.isEmpty())
        			return;
        		
        		for(Player player : queue.keySet()) 
        		{
	        		if(player.getOpenInventory() instanceof PlayerInventory) continue;
	            	
	        		List<Inventory> list = queue.get(player);
	            	player.openInventory(list.get(0));
	            	list.remove(0);
	            	if(list.isEmpty())
	            		queue.remove(player);
	            	else queue.put(player, list);
        		}
        	}
        }.runTaskTimer(plugin, 30, 30);
	}
	
	private static void addToQueue(Player player, Inventory inventory) 
	{
		List<Inventory> list = !queue.containsKey(player) ? new ArrayList<>() : queue.get(player);
		list.add(inventory);
		queue.put(player, list);
	}
	
	public Gui(String title, int size)
	{
		super(title, size);
		inventory.setItem(0, back); 
	}
	
	public abstract void onGuiClick(InventoryClickEvent event);
	
	public abstract boolean create(Player player);
	
	public void open(Player player) 
	{
		if(GuiApi.getInstance() == null)
		{
			Bukkit.getLogger().severe("GuiAPI is not enabled!");
			return;
		}

		if(inventory.getContents() == null || inventory.getContents().length == 0)
			if(!create(player))
				return;

        addToQueue(player, inventory);
	}
}
