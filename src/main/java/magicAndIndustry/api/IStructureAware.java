package magicAndIndustry.api;

import net.minecraft.world.World;

/**
 * For blocks which need behavior when joining/leaving machine structures.
 */
public interface IStructureAware 
{
	/**
	 * Called for each block in a machine structure after the structure is confirmed.
	 * @param world World the block is in
	 * @param x coord of block
	 * @param y coord of block
	 * @param z coord of block
	 * @param coreX coord of core
	 * @param coreY coord of core
	 * @param coreZ coord of core
	 */
	public void onStructureCreated(World world, int x, int y, int z, int coreX, int coreY, int coreZ);
	
	/**
	 * Called for each (remaining) block in a machine structure when it is no longer valid.
	 * @param world The world the block is in
	 * @param x coord of block
	 * @param y coord of block
	 * @param z coord of block
	 * @param coreX coord of core
	 * @param coreY coord of core
	 * @param coreZ coord of core
	 */
	public void onStructureBroken(World world, int x, int y, int z, int coreX, int coreY, int coreZ);
}
