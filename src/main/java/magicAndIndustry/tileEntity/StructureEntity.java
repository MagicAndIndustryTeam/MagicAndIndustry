package magicAndIndustry.tileEntity;

import magicAndIndustry.blocks.StructureBlock;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.FMLLog;

/**
 * Base class for tile entities created by upgrades.
 * Create subclasses of this for all tile entity handling in StructureUpgrades.
 */
public class StructureEntity extends TileEntity
{
	/** Core coordinates. */
	public int coreX = 0, coreY = 0, coreZ = 0;
	
	public StructureEntity()
	{
		super();
	}
	
	public StructureEntity(int prevCoreX, int prevCoreY, int prevCoreZ)
	{
		this();
		coreX = prevCoreX; coreY = prevCoreY; coreZ = prevCoreZ;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		if (hasCore())
		{
			tag.setInteger("CoreX", coreX);
			tag.setInteger("CoreY", coreY);
			tag.setInteger("CoreZ", coreZ);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		coreX = tag.getInteger("CoreX");
		coreY = tag.getInteger("CoreY");
		coreZ = tag.getInteger("CoreZ");
	}
	
	public boolean isNeighbor(ForgeDirection direction)
	{
		// Get coords in the direction!
		int x = xCoord + direction.offsetX;
		int y = yCoord + direction.offsetY;
		int z = zCoord + direction.offsetZ;
		
		Block neighbor = worldObj.getBlock(x, y, z);
		
		// Note: This prevents connecting to building structure blocks.
		return neighbor instanceof StructureBlock;
	}
	
	public boolean hasCore()
	{
		return !(coreX == 0 && coreY == 0 && coreZ == 0);
	}

	/**
	 * Sets the core values of the Machine Structure
	 * @param relX The relative unity of the Machine Structure.
	 * @param relY The relative duty of the Machine Structure.
	 * @param relZ The relative destiny of the Machine Structure.
	 */
	public StructureEntity setCoreValues(int relX, int relY, int relZ)
	{
		coreX = relX; coreY = relY; coreZ = relZ; return this;
	}
}
