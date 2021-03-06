package io.github.alexeymartynov.guiapi;

import java.util.ArrayList;
import java.util.List;

import io.github.alexeymartynov.util.UtilItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class GuiLeafable extends Gui {
	
	protected Button nextPage = new Button(GuiContent.NEXT);
	protected Button previousPage = new Button(GuiContent.PREVIOUS);
	protected Button currentPage = new Button(GuiContent.PAGE);
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
		inventory.setItem(currentPage.getSlot(), UtilItem.create(currentPage.getType(), 1, (byte) 0,
				currentPage.getItemMeta().getDisplayName() + this.page));
	}
	
	@Override
	public void open(Player player) 
	{
		setPage(page);
		if(inventory.getContents() == null || inventory.getContents().length == 0)
			if(!create(player))
				return;

		player.openInventory(inventory);
	}
	
	public abstract void turnPage();
	
	public void removePageButtons() 
	{
		inventory.setItem(currentPage.getSlot(), null);
		inventory.setItem(nextPage.getSlot(), null);
		inventory.setItem(previousPage.getSlot(), null);
	}
}
