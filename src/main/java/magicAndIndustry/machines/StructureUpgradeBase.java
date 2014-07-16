package magicAndIndustry.machines;

import magicAndIndustry.tileEntity.MachineCoreEntity;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * I don't think this is even used.
 */
@Deprecated
public abstract class StructureUpgradeBase 
{
	/**
	 * Called when the item is created.
	 * Should not change.
	 * @return The name the upgrade's item should display.
	 */
	public abstract String getName();
	
	/**
	 * Called when the item is created.
	 * Should not change.
	 * @return A crafting recipe to create this upgrade.
	 */
	public abstract ICrafting getRecipe();
	
	/**
	 * Called when the item is created.
	 * Should not change.
	 * @return If the item should have durability.
	 */
	public abstract boolean shouldHaveDurability();
	
	/**
	 * Called by the item's getIcon().
	 * @param reg The item's getIcon()'s IIconRegister
	 * @return An icon for the item.
	 */
	@SideOnly(Side.CLIENT)
	public abstract IIcon getIcon(IIconRegister reg);
	
	/**
	 * Whether the upgrade should do something when a player
	 * right clicks it while it's in a structure block.
	 * @return True to call {link:onRightClickFromStructure()} on right clicks.
	 */
	public abstract boolean handlesRightClickFromStructure();
	
	/**
	 * Called when a player right clicks the structure block IF
	 * handlesRightClickFromStructure is true.
	 * @param player The player who clicked.
	 * @param x X coord of structure block.
	 * @param y Y coord of structure block.
	 * @param z Z coord of structure block.
	 * @param core The TileEntity representing the core block of the machine.
	 */
	public abstract void onRightClickFromStructure(EntityPlayer player, int x, int y, int z, MachineCoreEntity core);
}
