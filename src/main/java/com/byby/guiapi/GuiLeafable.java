package com.byby.guiapi;

import java.util.ArrayList;
import java.util.List;

import com.byby.util.UtilItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class GuiLeafable extends Gui {
	
	protected ItemStack nextPage = GuiContent.NEXT.getItem();
	protected ItemStack previousPage = GuiContent.PREVIOUS.getItem();
	protected List<Integer> skipIndexes = new ArrayList<Integer>();
	protected int page = 1;
	protected int size;

	public GuiLeafable(String title, int size) 
	{ 
		super(title, size);
		this.size = size; 
		inventory.setItem(8, nextPage); 
		inventory.setItem(26, previousPage);
		if(!skipIndexes.isEmpty())
			return;
		
		for(int index = 0; index < size; index++) 
		{
			skipIndexes.add(index);
			index += 8;
			skipIndexes.add(index);
		}
	}

	public void setPage(int page) 
	{
		if(page < 1) 
			page = 1;

		this.page = page;
		turnPage();
		inventory.setItem(17, UtilItem.create(GuiContent.PAGE.getItem().getType(), 1, (byte) 0,
				GuiContent.PAGE.getItem().getItemMeta().getDisplayName() + this.page));
	}
	
	@Override
	public void open(Player player) 
	{
		setPage(page); 
		create(player);
		player.openInventory(inventory);
	}
	
	public abstract void turnPage();
	
	public void removePageButtons() 
	{
		inventory.setItem(8, null);
		inventory.setItem(17, null);
		inventory.setItem(26, null);
	}
}
