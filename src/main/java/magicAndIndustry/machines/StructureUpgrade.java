package magicAndIndustry.machines;

import java.util.HashMap;

import magicAndIndustry.machines.structure.StructureUpgradeRegistrar;
import magicAndIndustry.tileEntity.StructureEntity;
import magicAndIndustry.tileEntity.StructureUpgradeEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Base class for structure upgrades to inherit from. To create a structure upgrade: <br/>
 * 1. Create base class and implement abstract methods.<br/>
 * 2. Call {@link StructureUpgradeRegistrar#RegisterStructureUpgrade()} with its class during PostInit or PreInit (after this mod). <br/>
 * 3. Make sure to have a proper item and {@link StructureUpgradeEntity} to go along with it. <br/>
 * 4. ???? <br/>
 * 5. Profit
 */
public abstract class StructureUpgrade
{
	/** Write the StructureUpgrade data to file. You MUST call super.writeToNBT() or there will be problems! */
	public void writeToNBT(NBTTagCompound tag)
	{
		// Check if its ID has been saved
        String name = StructureUpgradeRegistrar.getStructureID(this.getClass());
        
        if (name == null)
        	throw new RuntimeException(this.getClass() + " is missing a mapping! Yet it has already been created! This is a terrible bug!");
        
        else // We know it from the list.
        	tag.setString("ID", name);
	}
	
	/** Read data from NBT. StructureUpgrade base class does NOT save any data, calling super.readFromNBT() is not required. */
	@Deprecated // Should be structure upgrade code.
	public abstract void readFromNBT(NBTTagCompound tags);
	
	/** 
	 * Return an ItemStack for this upgrade. Used when upgrade tile entites are being wrenched. <br/>
	 * It should be of an item which is instanceof IStructureUpgradeItem, or null/other if the upgrade is in a broken/etc state. <br/>
	 * This is called with isBreaking = true by {@link StructureUpgradeEntity#onBlockBroken}.
	 */
	@Deprecated // Should be upgrade entity code.
	public abstract ItemStack GetItemStack(boolean isBreaking);
	
	/**
	 * Upgrades create custom tile entities to handle thinks like inventory slots. </br>
	 * This method is called from an empty StructureBlock when a player right clicks it with an upgrade item.<br/>
	 * @param usedItem The item the player is holding when they create the tile entity. It is an instanceof IStructureUpgradeItem.
	 * @return The TileEntity to be created when this upgrade is placed.
	 */
	public abstract StructureUpgradeEntity getTileEntity(ItemStack usedItem);
	
	/**
	 * Called after the default constructor when an {@link IStructureUpgradeItem} (which this upgrade's ID) is used on a structure block.
	 * Use this to copy over any NBT data an ItemStack representation of the upgrade has.
	 * @param holder The player holding the item.
	 * @param stack An {@link IStructureUpgradeItem} item(we checked) item's ItemStack.
	 */
	@Deprecated // Should use upgrade entity code.
	public void readNBTFromItemStack(EntityPlayer holder, ItemStack stack) { }
	
	
	/**
	 * The texture the structure block should have with the upgrade. This is used if the upgrade's structure entity does not override rendering.
	 * @return A texture to render over the structure block
	 */
	@SideOnly(Side.CLIENT)
	public abstract IIcon getBlockOverlay();
}