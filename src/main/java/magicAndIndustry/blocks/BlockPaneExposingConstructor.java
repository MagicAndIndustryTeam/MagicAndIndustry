package magicAndIndustry.blocks;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;

public class BlockPaneExposingConstructor extends BlockPane 
{
	public BlockPaneExposingConstructor(String blockName, String textureName, String textureTopName, Material mat, boolean transparent, float hardness, float resistance)
	{
		super(textureName, textureTopName, mat, transparent); setHardness(hardness); setResistance(resistance); setBlockName(blockName);
	}
}
