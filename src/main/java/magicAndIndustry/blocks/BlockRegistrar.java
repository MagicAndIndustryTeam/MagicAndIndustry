package magicAndIndustry.blocks;

import magicAndIndustry.MagicAndIndustry;
import magicAndIndustry.machines.MachineTier;
import magicAndIndustry.tileEntity.AutomationStructureEntity;
import magicAndIndustry.tileEntity.BlockBreakerEntity;
import magicAndIndustry.tileEntity.DiscoBlockEntity;
import magicAndIndustry.tileEntity.EngineeringTableEntity;
import magicAndIndustry.tileEntity.FurnaceCoreEntity;
import magicAndIndustry.tileEntity.ScienceCraftingTableEntity;
import magicAndIndustry.tileEntity.StructureEntity;
import magicAndIndustry.tileEntity.TankUpgradeEntity;
import magicAndIndustry.utils.Textures;
import magicAndIndustry.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockRegistrar 
{
	public static void RegisterBlocksForKnocks()
	{
		//
		// Ores
		//
		oreRaisin = doFortunateOre(new FortuneOre("raisin", 3F, 20F, 0, 4, 6, 1));
		oreMeteoric = doIngotOre(new IngotOre("meteoric", 3F, 20F, 2));
		oreAluminum = doIngotOre(new IngotOre("aluminum", 2.5F, 20F, 2));
		
		//
		// Compressed Blocks
		//
		blockRaisin = doIngotBlock("raisin", 3F);
		blockRaisinBunch = doIngotBlock("raisin_bunch", 3F);
		blockMeteoric = doIngotBlock("meteoric", 5F);
		blockSteel = doIngotBlock("steel", 5F);
		blockPigIron = doIngotBlock("pig_iron", 5F);
		blockAluminum = doIngotBlock("aluminum", 5F);
		
		
		//
		// Machines
		//
		// Cobble tier
		cobbleStructure = doBlock(new StructureBlock(MachineTier.cobble));
		stoneHeatPlate = doBlock(new HeatPlate(MachineTier.cobble), false, false);
		cobbleFurnace = doBlock(new FurnaceCoreBlock(MachineTier.cobble, false).setStepSound(Block.soundTypePiston), false, false);
		cobbleFurnaceLit = doBlock(new FurnaceCoreBlock(MachineTier.cobble, true).setStepSound(Block.soundTypePiston), false, false, false, "cobble_furnace_lit");
		
		// Iron tier
		ironStructure = doBlock(new StructureBlock(MachineTier.iron));
		ironHeatPlate = doBlock(new HeatPlate(MachineTier.iron), false, false);
		ironFurnace = doBlock(new FurnaceCoreBlock(MachineTier.iron, false).setStepSound(Block.soundTypeAnvil), false, false);
		ironFurnaceLit = doBlock(new FurnaceCoreBlock(MachineTier.iron, true).setStepSound(Block.soundTypeAnvil), false, false, false, "iron_furnace_lit");
		ironSlab = doBlock(new MachineSlab(false, MachineTier.iron), false, false);	ironSlab_full = doBlock(new MachineSlab(true, MachineTier.iron), false, false);
		cookedSlab = doBlock(new MachineSlab(false, MachineTier.steel), false, false); cookedSlab_full = doBlock(new MachineSlab(true, MachineTier.steel), false, false);

		// Steel tier
		steelStructure = doBlock(new StructureBlock(MachineTier.steel));
		steelHeatPlate = doBlock(new HeatPlate(MachineTier.steel), false, false);
		steelFurnace = doBlock(new FurnaceCoreBlock(MachineTier.steel, false).setStepSound(Block.soundTypeAnvil), false, false);
		steelFurnaceLit = doBlock(new FurnaceCoreBlock(MachineTier.steel, true).setStepSound(Block.soundTypeAnvil), false, false, false, "steel_furnace_lit");
		//steelSlab = doBlock(new MachineSlab(false, "steel"));
		
		// Others
		industrialGlass = doBlock(new IndustrialGlass());
		cobbleStructureSafe = doBlock(new SafeStructure(MachineTier.cobble), false, false, false);
		ironStructureSafe = doBlock(new SafeStructure(MachineTier.iron), false, false, false);
		steelStructureSafe = doBlock(new SafeStructure(MachineTier.steel), false, false, false);
		
		//
		// Research Main Things
		//
		engineeringTable = doBlock(new EngineeringTable()); //doBlock(new DEFAULT_EngineeringTable());
		scienceCraftingTable = doBlock(new ScienceCraftingTable());
		
		
		//
		// Randoms
		//
		discoBlock = doBlock(new DiscoBlockBlock());
		steelBars = doBlock(new BlockPaneExposingConstructor("fenceSteel", Textures.block("steel_bars"), Textures.block("steel_bars"), Material.iron, true, 6F, 20F));
		
		
		//
		// Testing
		//
		blockBreaker = doBlock(new BlockBreaker(MachineTier.iron), false);
		
		// 
		// Register Tile Entities
		//
		GameRegistry.registerTileEntity(StructureEntity.class, "MAIStructure");
		
		GameRegistry.registerTileEntity(TankUpgradeEntity.class, "MAITankUpgrade");
		GameRegistry.registerTileEntity(AutomationStructureEntity.class, "MAIAutomationStructure");
		
		GameRegistry.registerTileEntity(EngineeringTableEntity.class, "MAIEngineeringTable");
		GameRegistry.registerTileEntity(ScienceCraftingTableEntity.class, "MAIScienceCraftingTable");
		GameRegistry.registerTileEntity(FurnaceCoreEntity.class, "MAIfurnace");
		GameRegistry.registerTileEntity(BlockBreakerEntity.class, "MAIBlockBreaker");
		GameRegistry.registerTileEntity(DiscoBlockEntity.class, "MAIDiscoBlock");
	}
	
	// Machine blocks
	public static Block cobbleFurnace, ironFurnace, steelFurnace;
	public static Block cobbleFurnaceLit, ironFurnaceLit, steelFurnaceLit;
	
	public static Block stoneHeatPlate, ironHeatPlate, steelHeatPlate;
	
	public static Block cobbleStructure, ironStructure, steelStructure;
	
	/** Crushers. Get it? */
	public static Block cobbleBeverly, ironBeverly, steelBeverly;
	public static Block cobbleBeverlyLit, ironBeverlyLit, steelBeverlyLit;
	
	public static Block ironSlab, cookedSlab, ironSlab_full, cookedSlab_full;
	
	public static Block cobbleStructureSafe, ironStructureSafe, steelStructureSafe;
	
	// Machines
	public static Block blockBreaker; // TODO replace with others n stuff
	
	//Ores
	public static IngotOre oreMeteoric, oreAluminum;
	public static FortuneOre oreRaisin;
	
	// Ingot Blocks
	public static Block blockRaisin, blockSteel, blockRaisinBunch, blockPigIron, blockMeteoric;
	public static Block blockAluminum;
	
	// Other Blocks
	public static Block industrialGlass;
	
	// Research
	public static Block engineeringTable, scienceCraftingTable;
	
	// Random
	public static Block discoBlock, superFurnace, steelBars;
	
	// Testing
	//public static Block blockBreaker;
	
	private static Block doBlock(Block brock, boolean oneTextureToRuleThemAll, boolean oreDict, boolean creativeTab, String registerName)
	{
		if (creativeTab)
			brock.setCreativeTab(MagicAndIndustry.tab);
		if (oneTextureToRuleThemAll)
			brock.setBlockTextureName(Textures.block(brock));
		
		// Using .substring(5) allows for /give player magicandindustry:blockname instead of magicandindustry:tile.blockname
		GameRegistry.registerBlock(brock, registerName);
		return brock;
	}
	
	private static Block doBlock(Block brock, boolean texture)
	{
		return doBlock(brock, texture, false, true, brock.getUnlocalizedName().substring(5));
	}
	
	private static Block doBlock(Block brock, boolean texture, boolean oreDict, boolean creative)
	{
		return doBlock(brock, texture, oreDict, creative, brock.getUnlocalizedName().substring(5));
	}
	
	private static Block doBlock(Block brock, boolean texture, boolean ordict)
	{
		return doBlock(brock, texture, ordict, true, brock.getUnlocalizedName().substring(5));
	}
	
	private static Block doBlock(Block brock)
	{
		return doBlock(brock, true, false, true, brock.getUnlocalizedName().substring(5));
	}
	
	private static IngotOre doIngotOre(IngotOre ore)
	{
		return (IngotOre)doBlock(ore, false, true); //, true, ore.getUnlocalizedName().substring(5));
	}

	private static FortuneOre doFortunateOre(FortuneOre ore)
	{
		return (FortuneOre)doBlock(ore, false, true); //, true, ore.getUnlocalizedName().substring(5));
	}
	
	private static Block doIngotBlock(String name, float hardness)
	{
		Block brock = new Block_Exposing_Constructor(Material.rock);
		brock.setBlockName(name + "_block");
		// Keep textures organized.
		brock.setBlockTextureName(Textures.block("block", name));
		brock.setHardness(hardness); brock.setResistance(30);
		return doBlock(brock, false, true);
	}
	
	public static Block furnaceForType(String type)
	{
		if (type.equals("cobble")) return cobbleFurnace;
		if (type.equals("steel")) return steelFurnace;
		if (type.equals("iron")) return ironFurnace;
		return cobbleFurnace;
	}
	
	public static Block litFurnaceForType(String type) 
	{
		if (type.equals("cobble")) return cobbleFurnaceLit;
		if (type.equals("steel")) return steelFurnaceLit;
		if (type.equals("iron")) return ironFurnaceLit;
		return cobbleFurnaceLit;
	}
	
	public static Block machineSlabForType(String type)
	{
		if (type.equals("steel")) return cookedSlab_full;
		return ironSlab_full;
	}

	public static Block crusherForType(MachineTier tier, boolean dammitWesley)
	{
		if (tier.name == "cobble") return dammitWesley ? cobbleBeverlyLit : cobbleBeverly;
		if (tier.name == "iron") return dammitWesley ?  ironBeverlyLit : ironBeverly;
		if (tier.name == "steel") return dammitWesley ? steelBeverlyLit : steelBeverly;
		return null;
	}
	

}
