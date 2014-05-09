package Blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockRegistrar 
{
	public static void RegisterBlocksForKnocks()
	{
		// All blocks done on one/line, can do line checks to make
		// sure we have all of the blocks registered.
		cobbleStruct = new CobbleStructure(Material.rock).setBlockName("cobbleStructure").setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(cobbleStruct, cobbleStruct.getUnlocalizedName());
		
	}
	
	public static Block cobbleStruct;
}
