package magicAndIndustry.blocks.base;

import magicAndIndustry.utils.Textures;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Used for all MAI ore blocks which drop themselves.
 */
public class IngotOre extends Block
{
	public IngotOre(String name, float hardness, float resistance, int harvestLevel)
	{
		super(Material.rock);
		setBlockName(name + "_ore");
		setBlockTextureName(Textures.block("ore_" + name));
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel("pickaxe", harvestLevel);
	}
}
