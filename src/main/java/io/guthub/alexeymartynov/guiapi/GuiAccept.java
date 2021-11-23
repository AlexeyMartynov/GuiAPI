package io.guthub.alexeymartynov.guiapi;

import io.guthub.alexeymartynov.util.UtilItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GuiAccept extends Gui {

	public static ItemStack accept = GuiContent.ACCEPT.getItem();
	public static ItemStack decline = GuiContent.DECLINE.getItem();
	protected String acceptCommand;
	protected String declineCommand;

	private boolean console;
	
	public GuiAccept(String title, String acceptCommand, String declineCommand, boolean console) 
	{
		super(title, 27);
		this.acceptCommand = acceptCommand;
		this.declineCommand = declineCommand;
		this.console = console;
	}

	@Override
	public void onGuiClick(InventoryClickEvent event) 
	{
		ItemStack item = event.getCurrentItem();
		Player player = (Player) event.getWhoClicked();
		
		event.setCancelled(true);		
		if(item == null || item.getType() == Material.AIR)
			return;
		
		if(UtilItem.areTheSameItems(item, accept))
		{
			if(acceptCommand != null) 
			{
				if(console)
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), acceptCommand);
				else 
					Bukkit.dispatchCommand(player, acceptCommand);
			}
		}
		else 
		{
			if(declineCommand != null) 
			{
				if(console)
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), declineCommand);
				else
					Bukkit.dispatchCommand(player, acceptCommand);
			}
		}
		
		player.closeInventory();
	}

	@Override
	public boolean create(Player player) 
	{
		inventory.setItem(0, null);
		inventory.setItem(12, accept);
		inventory.setItem(14, decline);
		return true;
	}
}
