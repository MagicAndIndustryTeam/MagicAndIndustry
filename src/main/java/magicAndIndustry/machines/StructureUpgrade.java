package magicAndIndustry.machines;

import java.util.HashMap;

import magicAndIndustry.tileEntity.StructureTileEntity;
import magicAndIndustry.tileEntity.StructureUpgradeEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class StructureUpgrade
{
	/** Get the subclasses by registered ID. */
	private static HashMap<String, Class<? extends StructureUpgrade>> namesToClasses = new HashMap<String, Class<? extends StructureUpgrade>>();
	/** Get a registered ID of a subclass. */
	private static HashMap<Class<? extends StructureUpgrade>, String> classesToNames = new HashMap<Class<? extends StructureUpgrade>, String>();
	
	/**
	 * TODO this will go in the API class, it's an API function.
	 * Register a StructureUpgrade subclass with the registrar. Give it a name to save as an NBT ID.
	 * @param classical The subclass to register
	 * @param name The ID of the structure upgrade.
	 */
	public static void RegisterStructureUpgrade(Class<? extends StructureUpgrade> classical, String name)
	{
		if (namesToClasses.containsKey(name))
			throw new IllegalArgumentException("Duplicate StructureUpgrade id: " + name);
		
		else // We can register it!
		{
			// This way we can check for a class to instantiate by checking the registered hashmap.
			// It'll be registered on initialize.
			namesToClasses.put(name, classical);
			classesToNames.put(classical, name);
		}
	}
	
	public static Class<? extends StructureUpgrade> getUpgradeClassByID(String id)
	{
		return namesToClasses.get(id);
	}

	/** Write the StructureUpgrade data to file. You MUST call super.writeToNBT() or there will be problems! */
	public void writeToNBT(NBTTagCompound tag)
	{
		// Check if its ID has been saved
        String name = classesToNames.get(this.getClass());
        if (name == null)
        	throw new RuntimeException(this.getClass() + " is missing a mapping! Yet it has already been created! This is a terrible bug!");
        
        else // We know it from the list.
        	tag.setString("ID", name);
	}
	
	/** Read data from NBT. StructureUpgrade base class does NOT save any data, calling super.readFromNBT() is not required. */
	public abstract void readFromNBT(NBTTagCompound tags);
	
	/** 
	 * Return an ItemStack for this upgrade. 
	 * It should be of an item which is instanceof IStructureUpgradeItem, or null/other if the upgrade is in a broken/etc state.
	 * This is normally called with true, except for {@link StructureUpgradeEntity#onBlockBroken} which sets it to false.
	 */
	public abstract ItemStack GetItemStack(boolean isBreaking);
	
	/**
	 * Upgrades handle custom behavior through subclassing StructureTileEntity.
	 * For example, one which implements IInventory for item storage or porting.
	 * This will also be responsible for saving all structure upgrade NBT data.
	 * @return The TileEntity to be created when this upgrade is placed.
	 */
	public abstract StructureUpgradeEntity getTileEntity();
	
	/**
	 * Called after the default constructor when an {@link IStructureUpgradeItem} (which this upgrade's ID) is used on a structure block.
	 * Use this to copy over any NBT data an ItemStack representation of the upgrade has.
	 * @param holder The player holding the item.
	 * @param stack An {@link IStructureUpgradeItem} item(we checked) item's ItemStack.
	 */
	public void constructFromItemStack(EntityPlayer holder, ItemStack stack) { }
	
	
	/**
	 * The texture the structure block should have with the upgrade.
	 * @return A texture to render over the structure block
	 */
	@SideOnly(Side.CLIENT)
	public abstract IIcon getBlockOverlay();
}