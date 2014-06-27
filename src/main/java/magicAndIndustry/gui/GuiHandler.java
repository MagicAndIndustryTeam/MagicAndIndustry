package magicAndIndustry.gui;

import magicAndIndustry.tileEntity.BlockBreakerEntity;
import magicAndIndustry.tileEntity.EngineeringTableEntity;
import magicAndIndustry.tileEntity.FurnaceCoreEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	/** Machine GUI IDs.*/ 
	public static final int FURNACE = 0, BEVERLY = 1, ALLOY_FURNACE = 2;
	
	/** Other block GUI IDs. */
	public static final int BLOCK_BREAKER = 10;
	
	/** Research GUI IDs. */
	public static final int ENGINEERING_TABLE = 20, BIOLOGY_TABLE = 21, CHEMISTRY_SET = 22;
		
	/** Science book GUI IDs. */
	public static final int BOOK_O_SCIENCE = 30, BOOK_ENGINEERING = 31, BOOK_CHESMISTRY = 32, BOOK_BIOLOGY = 33, BOOK_PHYSICS = 34;
	
	/** Magic book GUI IDs. */
	public static final int BOOK_O_MAGIC = 25;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if (ID < 30)
		{
			TileEntity entity = world.getTileEntity(x, y, z);

			if (entity instanceof FurnaceCoreEntity)
				return new FurnaceContainer(player.inventory, (FurnaceCoreEntity)entity);
			
			if (entity instanceof BlockBreakerEntity)
				return new BlockBreakerContainer(player.inventory, (BlockBreakerEntity)entity);

			if (entity instanceof EngineeringTableEntity)
				return new EngineeringTableContainer(player.inventory, (EngineeringTableEntity)entity);
		}
		if (ID == 30) return new ResearchBookContainer(player);
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if (ID < 30)
		{
			TileEntity entity = world.getTileEntity(x, y, z);

			// Furnace core
			if (entity instanceof FurnaceCoreEntity)
				return new FurnaceGUI(player.inventory, (FurnaceCoreEntity)entity);

			if (entity instanceof EngineeringTableEntity)
				return new EngineeringTableGUI(player.inventory, (EngineeringTableEntity)entity);
		}
		if (ID == 30) return new ResearchBookGUI(player);
		
		return null;
	}
}
