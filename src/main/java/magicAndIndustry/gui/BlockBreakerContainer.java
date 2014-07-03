package magicAndIndustry.gui;

import magicAndIndustry.tileEntity.BlockBreakerEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;


public class BlockBreakerContainer extends Container
{
	private BlockBreakerEntity breaker;

	public BlockBreakerContainer(InventoryPlayer inventory, BlockBreakerEntity entity)
	{
		
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return false;
	}

}
