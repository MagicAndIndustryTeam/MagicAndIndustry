package magicAndIndustry.machines;

import java.util.HashMap;

import magicAndIndustry.machines.structure.StructureUpgradeRegistrar;
import magicAndIndustry.tileEntity.StructureEntity;
import magicAndIndustry.tileEntity.StructureTileEntity;
import magicAndIndustry.tileEntity.base.StructureUpgradeEntity;
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
	/**
	 * Upgrades create custom tile entities to handle thinks like inventory slots. </br>
	 * This method is called from an empty StructureBlock when a player right clicks it with an upgrade item.<br/>
	 * @param usedItem The item the player is holding when they create the tile entity. It is an instanceof IStructureUpgradeItem.
	 * @return The TileEntity to be created when this upgrade is placed.
	 */
	public abstract StructureUpgradeEntity getTileEntity(ItemStack usedItem);
	
	
	/**
	 * The texture the structure block should have with the upgrade. This is used if the upgrade's structure entity does not override rendering.
	 * @return A texture to render over the structure block
	 */
	@SideOnly(Side.CLIENT)
	public abstract IIcon getBlockOverlay();
	
	/**
	 * Called when a structure upgrade (matching this one's tile entity) is broken. </br>
	 * Determines what ItemStack the tile entity will drop.
	 * @param entity The tile entity whose block is being broken
	 * @return An ItemStack (or null) which will be dropped in addition to the structure block.
	 */
	public abstract ItemStack getItemOnStructureBreak(StructureTileEntity entity);
}