package magicAndIndustry.machines;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import magicAndIndustry.blocks.BlockRegistrar;
import magicAndIndustry.blocks.MachineSlab;
import magicAndIndustry.utils.Textures;


/**
 * Contains information about machine tiers and registering more to work with the leveling system.
 */
public class MachineTier 
{
	private static HashMap<String, MachineTier> tiers;
	
	// MAI Tiers - the big three
	public static MachineTier cobble = new MachineTier("cobble", 0, Textures.block("stoneSlab"), 3F, 30F, Blocks.stone_slab);
	public static MachineTier iron = new MachineTier("iron", 2, Textures.block("ironAnvilSide"), 3.5F, 40F, BlockRegistrar.cookedSlab);
	public static MachineTier steel = new MachineTier("steel", 4, Textures.block("iron_side"), 4F, 45F, BlockRegistrar.ironSlab);
	
	public static MachineTier aluminum = new MachineTier("aluminum", 1, "", 3F, 30F, null);
	// THESE WOULD BE SO AWESOME 
	public static MachineTier tungsten, titanium;

	
	/** The name of the tier, i.e. "cobble", used in creating StructureBlocks and getting textures. */
	public String name;
	
	/** The level of the tier. Cobble is 0, iron is 2, steel is 4, titanium is 6. Used for balancing. */
	public int level;
	
	/** Texture to be displayed */
	public String altTexture;
	
	/** Hardness of the tier's blocks - the machines may reduce it slightly. */
	public float standardHardness;
	/** Resistance of the tier's blocks - the machines may reduce it slightly. */
	public float standardResistance;
	
	/** Slab to put on machines of this tier */
	public Block slabType;
	
	public MachineTier(String theName, int theLevel, String altTexture, float hardness, float futile, Block machineSlab)
	{
		name = theName; level = theLevel; 
		this.altTexture = altTexture;
		standardHardness = hardness; standardResistance = futile;
		slabType = machineSlab;
	}
	
	
	/**
	 * Returns whether this tier is the same level or greater than another tier.
	 * @param other The other MachineTier to test for.
	 * @return level >= other.level
	 */
	public boolean isStrongEnoughFor(MachineTier other)
	{
		return level >= other.level;
	}
	
	public static void register(MachineTier tier)
	{
		tiers.put(tier.name, tier);
	}
	
	public static MachineTier get(String id)
	{
		return tiers.get(id);
	}
	
	public static void cry()
	{
		tiers = new HashMap<String, MachineTier>();
		register(cobble); register(iron); register(steel);
	}
	
	/**
	 * Return name + "TierSide"
	 * @return name + "TierSide";
	 */
	public String getFaceTexture()
	{
		return Textures.block(name, "side");
	}
	
	public String getStripedTexture()
	{
		return Textures.block(name, "side_stripe");
	}
	
	public String getAltTexture()
	{
		return altTexture;
	}
}
