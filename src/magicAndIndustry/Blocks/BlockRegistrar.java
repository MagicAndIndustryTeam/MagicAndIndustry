package magicAndIndustry.Blocks;

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
		
		stoneHeatPlate = new StoneHeatPlate(Material.rock).setBlockName("stoneHeatPlate").setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(stoneHeatPlate, stoneHeatPlate.getUnlocalizedName());
		
		cobbleFurnaceCore = new CobbleFurnaceCore(Material.rock).setBlockName("cobbleFurnaceCore").setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(cobbleFurnaceCore, cobbleFurnaceCore.getUnlocalizedName());
	}
	
	public static Block cobbleStruct;
	public static Block stoneHeatPlate;
	public static Block cobbleFurnaceCore;
}
