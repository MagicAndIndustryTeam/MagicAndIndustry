package magicAndIndustry.items;

import magicAndIndustry.blocks.BlockRegistrar;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingRegistrar 
{
	public static void RegisterCraftification()
	{
		// Cobble Furnace
		
		GameRegistry.addRecipe(new ItemStack(BlockRegistrar.cobbleStructure), new Object[]
		{
			"XX ", "XX ", 'X', Blocks.cobblestone
		});
		GameRegistry.addRecipe(new ItemStack(BlockRegistrar.cobbleFurnaceCore), new Object[]
		{
				"CCC",
				"B B",
				"SSS", 
				'C', BlockRegistrar.cobbleStructure, 'B', Blocks.iron_bars, 'S', Blocks.stone_slab
				
		});
		GameRegistry.addRecipe(new ItemStack(BlockRegistrar.stoneHeatPlate, 3), new Object[]
		{
			"SSS",
			"C C",
			"CCC", 'S', Blocks.stone_slab, 'C', BlockRegistrar.cobbleStructure
		});
		GameRegistry.addRecipe(new ItemStack(BlockRegistrar.cobbleStructureSafe, 4), new Object[]
		{
			"XX ", "XX ", 'X', BlockRegistrar.cobbleStructure
		});
		
		// Iron Furnace
		
		GameRegistry.addRecipe(new ItemStack(BlockRegistrar.ironStructure), new Object[]
		{
			"XX ", "XX ", 'X', Items.iron_ingot
		});
		GameRegistry.addRecipe(new ItemStack(BlockRegistrar.ironFurnaceCore), new Object[]
		{
			"III",
			"B B",
			"SSS", 'I', BlockRegistrar.ironStructure, 'B', Blocks.iron_bars, 'S', Blocks.stone_slab
		});
		GameRegistry.addRecipe(new ItemStack(BlockRegistrar.ironHeatPlate, 3), new Object[]
		{
			"SSS",
			"I I",
			"III", 'S', Blocks.stone_slab, 'I', BlockRegistrar.ironStructure
		});
		GameRegistry.addRecipe(new ItemStack(BlockRegistrar.ironStructureSafe, 4), new Object[]
		{
			"XX ", "XX ", 'X', BlockRegistrar.ironStructure
		});
		
		// Steel Furnace
		
		GameRegistry.addRecipe(new ItemStack(BlockRegistrar.steelStructure), new Object[]
		{
			"XX ", "XX ", 'X', ItemRegistrar.ingotSteel
		});
		GameRegistry.addRecipe(new ItemStack(BlockRegistrar.steelFurnaceCore), new Object[]
		{
			"TTT",
			"B B",
			"SSS", 'T', BlockRegistrar.steelStructure, 'B', Blocks.iron_bars, 'S', Blocks.stone_slab
		});
		
		// Steel
		
		GameRegistry.addRecipe(new ItemStack(ItemRegistrar.steelSword), new Object[]
		{
			" X ",
			" X ",
			" Y ", 'X', ItemRegistrar.ingotSteel, 'Y', Items.stick
		});
		GameRegistry.addRecipe(new ItemStack(ItemRegistrar.steelPickaxe), new Object[]
		{
			"XXX",
			" Y ",
			" Y ", 'X', ItemRegistrar.ingotSteel, 'Y', Items.stick
		});
		GameRegistry.addRecipe(new ItemStack(ItemRegistrar.steelShovel), new Object[]
		{
			" X ",
			" Y ",
			" Y ", 'X', ItemRegistrar.ingotSteel, 'Y', Items.stick
		});
		GameRegistry.addRecipe(new ItemStack(ItemRegistrar.steelAxe), new Object[]
		{
			"XX ",
			"XY ",
			" Y ", 'X', ItemRegistrar.ingotSteel, 'Y', Items.stick
		});
		GameRegistry.addRecipe(new ItemStack(BlockRegistrar.blockSteel), new Object[]
		{
			"XXX", "XXX", "XXX", 'X', ItemRegistrar.ingotSteel 
		});
		
		// Meteoric Iron
		
		GameRegistry.addRecipe(new ItemStack(ItemRegistrar.meteorSword), new Object[]
		{
			" X ",
			" X ",
			" Y ", 'X', ItemRegistrar.ingotMeteorIron, 'Y', Items.stick
		});
		GameRegistry.addRecipe(new ItemStack(ItemRegistrar.meteorShovel), new Object[]
		{
			" X ",
			" Y ",
			" Y ", 'X', ItemRegistrar.ingotMeteorIron, 'Y', Items.stick
		});
		GameRegistry.addRecipe(new ItemStack(ItemRegistrar.meteorPickaxe), new Object[]
		{
			"XXX",
			" Y ",
			" Y ", 'X', ItemRegistrar.ingotMeteorIron, 'Y', Items.stick
		});
		GameRegistry.addRecipe(new ItemStack(BlockRegistrar.blockMeteoric), new Object[]
		{
			"XXX", "XXX", "XXX", 'X', ItemRegistrar.ingotMeteorIron
		});
		
		
		GameRegistry.addRecipe(new ItemStack(ItemRegistrar.wrench), new Object[]
		{
			" X ",
			" X ",
			" X ", 'X', Items.iron_ingot
		});
		
		ItemStack bagOfWinds = new ItemStack(ItemRegistrar.bagOfWinds);
		bagOfWinds.setTagCompound(new NBTTagCompound());
		bagOfWinds.stackTagCompound.setInteger("Uses", 0);
		GameRegistry.addRecipe(bagOfWinds, new Object[]
		{
				"LLL", // TODO move to magicking
				"LSL",
				"LLL", 'L', Items.leather, 'S', Items.string
		});
		
		ItemStack raisin = new ItemStack(ItemRegistrar.raisin);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistrar.raisinBunch), new Object[]
		{
			raisin, raisin, raisin
		});
		GameRegistry.addRecipe(new ItemStack(BlockRegistrar.blockRaisin), new Object[]
		{
			"XXX", "XXX", "XXX", 'X', ItemRegistrar.raisin
		});
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistrar.bookOScience), new Object[]
		{
			new ItemStack(ItemRegistrar.bookBiology), new ItemStack(ItemRegistrar.bookChemistry),
			new ItemStack(ItemRegistrar.bookEngineering), new ItemStack(ItemRegistrar.bookPhysics)
		});
		
		GameRegistry.addRecipe(new ItemStack(ItemRegistrar.engineeringTable), new Object[]
		{
			"III",
			"ICI",
			"I I", 'I', Items.iron_ingot, 'C', Blocks.chest
		});
	}
}
