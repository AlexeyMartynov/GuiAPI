package io.github.alexeymartynov.guiapi;

import io.github.alexeymartynov.util.UtilItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GuiAccept extends Gui {

	public static Button accept = new Button(GuiContent.ACCEPT);
	public static Button decline = new Button(GuiContent.DECLINE);
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
		inventory.setItem(back.getSlot(), null);
		inventory.setItem(accept.getSlot(), accept);
		inventory.setItem(decline.getSlot(), decline);
		return true;
	}
}
