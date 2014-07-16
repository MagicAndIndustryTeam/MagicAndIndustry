package magicAndIndustry.tileEntity.base;

import java.util.Random;

import magicAndIndustry.blocks.StructureBlock;
import magicAndIndustry.machines.StructureUpgrade;
import magicAndIndustry.tileEntity.StructureEntity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.FMLLog;

/**
 * TileEntity that holds structure upgrades.
 * This base class only implements read/write NBT.
 * <br/>
 * Custom StructureUpgrades should create their own TileEntities to do world-interactive things,
 * such as right clicks, block breaks, manage IInventory, redstone or fluid tank.
 * <br/>
 * Most machine-type code should be called from the StructureUpgrade itself through machine events.
 */
public class StructureUpgradeEntity extends StructureEntity
{
	/**
	 * Structure upgrade stored in this tile entity.
	 */
	public StructureUpgrade upgrade;
	
	/**
	 * This random is initialized with the super() constructor (new Random()). 
	 * Use it for all of your subclass pseudorandomizing needs.
	 */
	public Random rand;
	
	public StructureUpgradeEntity()
	{
		super();
		rand = new Random();
	}
	
	/**
	 * Called when NBT data is read. You MUST call super.readFromNBT() in subclasses.
	 */
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		
		// Check for "Upgrade" tag collection
		if (tag.hasKey("Upgrade", 10))
		{
			NBTTagCompound upgradeTag = tag.getCompoundTag("Upgrade");
			
			if (upgradeTag.hasKey("ID"))
			{
				Class<? extends StructureUpgrade> classic = StructureUpgrade.getUpgradeClassByID(upgradeTag.getString("ID"));
				if (classic != null)
				{
					try 
					{
						upgrade = (StructureUpgrade)classic.newInstance();
					} 
					// If there's an exception, they done messed up their constructor.
					catch (Exception e) 
					{ 
						FMLLog.severe("Exception loading a Structure Upgrade (%s, class name %s) from a Structure Block at %i, %i, %i:", 
							upgradeTag.getString("ID"), classic.getName(), xCoord, yCoord, zCoord);
						FMLLog.severe("Found exception: %s", e.toString());
						e.printStackTrace();
					}
					upgrade.readFromNBT(upgradeTag);
				}
				else // Upgrade isn't registered.
				{
					// Failed to load upgrade. Don't want to crash, just log severe.
					FMLLog.severe("Unable to load StructureUpgrade from Structure Block at %1$i, %2$i, %3$i - found Structure Upgrade with ID %4$s which was not registered!",
						xCoord, yCoord, zCoord, upgradeTag.getString("ID"));
					FMLLog.warning("This data will stay in the file as long as the block is not destroyed - if you recover the mod/registration of the Structure Upgrade, " +
						"you will be able to get the upgrade back by reloading. If you break the block, the upgrade is lost forever.");
				}
			}
			// else no id for the upgrade
		}
		// else no upgrade
	}
	
	/**
	 * Writes the upgrade to NBT. You MUST call super.writeToNBT() in subclasses
	 */
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		if (upgrade != null)
		{
			NBTTagCompound upgradeTag = new NBTTagCompound();
			upgrade.writeToNBT(upgradeTag);
			tag.setTag("Upgrade", upgradeTag);
		}
	}
	
	/**
	 * Called from the structure block when it is right clicked.
	 * These parameters are copied from the block's event - server side only.
	 * The base class {@link StructureUpgradeEntity } does not handle this event.
	 * <br/>
	 * If you would like a client side event please contact us.
	 * @param player The player right-clicking (use, etc) the block.
	 * @param side The side the player hit.
	 * @param hitX Block x coord.
	 * @param hitY Block y coord.
	 * @param hitZ Block z coord.
	 * @return 
	 */
	public boolean onBlockActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		return false;
	}
	
	/**
	 * Called from {@link StructureBlock} when it is destroyed. 
	 * <br/>
	 * Base implementation calls {@link StructureUpgrade#GetItemStack(boolean blockBreal)} with true and adds that to the world.
	 */
	public void onBlockBroken()
	{
		ItemStack stack = upgrade.GetItemStack(true);
		
		if (stack != null)
		{
			float x = rand.nextFloat() * 0.8F + 0.1F, 
				  y = rand.nextFloat() * 0.8F + 0.1F, 
				  z = rand.nextFloat() * 0.8F + 0.1F;

			EntityItem entity = new EntityItem(worldObj, x, y, z, stack);

			if (stack.hasTagCompound()) 
				entity.getEntityItem().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());

			float offset = 0.05F;
			entity.motionX = rand.nextGaussian() * offset;
			entity.motionY = rand.nextGaussian() * offset + 0.2F;
			entity.motionZ = rand.nextGaussian() * offset;
			worldObj.spawnEntityInWorld(entity);
		}
		
		// Clear us out.
		worldObj.removeTileEntity(xCoord, yCoord, zCoord);
		worldObj.setTileEntity(xCoord, yCoord, zCoord, new StructureEntity());
	}
	
	/**
	 * Called when the block is "used" with a wrench.
	 * Please call super.onWrenched - the base class handles dropping the upgrade if the player is shifted.
	 */
	public void onWrenched(EntityPlayer player, int side)
	{
		// Sneak + wrench drops upgrade
		if (player.isSneaking())
		{
			ItemStack stack = upgrade.GetItemStack(false);
			if (stack != null)
			{
				// The items shouldn't be stuck in the blocks.
				ForgeDirection dir = ForgeDirection.getOrientation(side);

				// TODO see if standard code works with players capturing drops.
				float x = rand.nextFloat() * 0.8F + 0.1F, // + (0.4F * dir.offsetX), 
						y = rand.nextFloat() * 0.8F + 0.1F, 
						z = rand.nextFloat() * 0.8F + 0.1F;

				// Create entity item.
				EntityItem entity = new EntityItem(worldObj, x + (0.5D * dir.offsetX), y + (0.5D * dir.offsetY), z + (0.5D * dir.offsetZ), stack);

				// Copy tag compound
				if (stack.hasTagCompound()) 
					entity.getEntityItem().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());

				// set motion
				float offset = 0.05F;
				entity.motionX = rand.nextGaussian() * offset;
				entity.motionY = rand.nextGaussian() * offset + 0.2F;
				entity.motionZ = rand.nextGaussian() * offset;

				player.capturedDrops.add(entity);
				worldObj.spawnEntityInWorld(entity);

			}
			worldObj.removeTileEntity(xCoord, yCoord, zCoord);
			worldObj.setTileEntity(xCoord, yCoord, zCoord, new StructureEntity(coreX, coreY, coreZ));
		}
	}
}
