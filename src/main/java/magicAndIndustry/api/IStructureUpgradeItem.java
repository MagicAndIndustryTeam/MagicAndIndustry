package magicAndIndustry.api;

import magicAndIndustry.machines.StructureUpgrade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/** All Structure Upgrade items must implement this interface in order to add Structure Upgrades to things when placed inside/right clicked on. */
public interface IStructureUpgradeItem 
{
	/** 
	 * Get the structure upgrade ID which this item represents.
	 * @return The registered Structure Upgrade ID of the StructureUpgrade for this item.
	 */
	public String getStructureUpgradeID();
	
	/**
	 * Called when a Structure Block is being right clicked with the item. Return a StructureUpgrade instance used to create a new TileEntity.
	 * @param stack The structure upgrade item (that the player is holding).
	 * @param player The player holding the upgrade
	 * @param world The world that the player is in
	 * @param x Player x
	 * @param y Player y
	 * @param z Player zee
	 * @return A StructureUpgrade, used to get a tileentity to place in the structure block.
	 */
	public StructureUpgrade getUpgrade(ItemStack stack, EntityPlayer player, World world, int x, int y, int z);
}
