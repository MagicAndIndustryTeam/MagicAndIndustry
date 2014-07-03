package magicAndIndustry.api;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Have your headgear which allows the viewing of complex machine displays extend this interface.
 */
public interface IMachineGlasses 
{
	/**
	 * Called on display ticks with the player to determine if the player can see complex machine interfaces. Return true to allow it.
	 * @return True to allow the player to see complex machine interfaces.
	 * @param player The player wearing the gear
	 */
	public boolean isVisionary(EntityPlayer player);
}
