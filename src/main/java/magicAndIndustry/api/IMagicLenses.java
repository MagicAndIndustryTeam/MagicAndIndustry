package magicAndIndustry.api;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Interface for magical headware allowing the user to more clearly see magicks.
 */
public interface IMagicLenses 
{
	/**
	 * Called on display ticks to determine if the player can see magic things.
	 * @param player The player wearing the gear
	 * @return whether the player can see magicks.
	 */
	public boolean isVisible(EntityPlayer player);
}
