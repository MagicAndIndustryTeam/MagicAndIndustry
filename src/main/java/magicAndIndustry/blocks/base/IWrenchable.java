package magicAndIndustry.blocks.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/** Magic And Industry interface for blocks to react to wrenches with. */
public interface IWrenchable 
{
	/**
	 * Called when the block is right clicked with the wrench - both server and client side.
	 * @param player The player with the wrench.
	 * @param world The world in which everything goes down/
	 * @param x X coord.
	 * @param y Y coord.
	 * @param z Z coord, in fact.
	 * @param meta The metadata of the block being wrenched (I got that for you).
	 * @param side The side of the block that the player hits.
	 */
	public void OnWrenched(EntityPlayer player, World world, int x, int y, int z, int meta, int side);
}
