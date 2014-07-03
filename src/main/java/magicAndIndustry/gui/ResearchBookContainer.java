package magicAndIndustry.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ResearchBookContainer extends Container 
{
	private EntityPlayer player;
	
	// Variables for chapter, etc?
	// Should be clientside.
	
	public ResearchBookContainer(EntityPlayer playerIn)
	{
		player = playerIn;
	}
	
	public void onContainerClosed()
	{
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer var1) { return true; }
}
