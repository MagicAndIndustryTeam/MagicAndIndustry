package magicAndIndustry.machines.structure;

import java.util.HashMap;

import magicAndIndustry.blocks.BlockRegistrar;
import net.minecraft.init.Blocks;

/**
 * Register possible machine structure configurations for your machines here.
 * Note that the internal database is created during Magic And Industry's preInit event.
 */
public class MachineStructureRegistrar
{	
	public static StructureRequirementBase needAir = new BlockRequirement(Blocks.air),
		ironBars = new BlockRequirement(Blocks.iron_bars),
		stonePlate = new BlockRequirement(BlockRegistrar.stoneHeatPlate),
		stoneSlab = new BlockRequirement(Blocks.stone_slab),
		structure = new StructureBlockRequirement(),
		structGlass = new StructOrGlassRequirement(),
		blockBreaker = new BlockRequirement(BlockRegistrar.blockBreaker);
	
	private static HashMap<String, MachineStructure[]> idStructureMap;

	/**
	 * Gets the registered possible machine structures for a machine.
	 * @param machineID The ID of the machine
	 * @return
	 */
	public static MachineStructure[] getStructuresForMachineID(String machineID)
	{
		return idStructureMap.get(machineID);
	}	

	/**
	 * Registers machine structure(s) for a machine with a specific ID.
	 * If the ID already exists, the new structure(s) are added to its collection.
	 * @param machineID The ID of the machine.
	 * @param structures A collection of possible machine structures for its core.
	 */
	public static void registerMachineConfiguration(String machineID, MachineStructure... structures)
	{
		// If we already have structures, add more
		if (idStructureMap.containsKey(machineID))
		{
			// Get the entry, make a new array
			MachineStructure[] entry = idStructureMap.get(machineID);
			MachineStructure[] newEntry = new MachineStructure[entry.length + structures.length];

			// Copy stuff over
			int i=0;
			for (; i < entry.length; i++)
				newEntry[i] = entry[i];
			for (int j = 0; j < structures.length; j++, i++)
				newEntry[i] = structures[j];

			// Update the map.
			idStructureMap.remove("machineID");
			idStructureMap.put(machineID, newEntry);
		}
		else idStructureMap.put(machineID, structures);
	}
	/**
	 * Creates internal database in MAI and also registers our machine structures.
	 */
	public static void assimilate()
	{
		idStructureMap = new HashMap<String, MachineStructure[]>();
		
		registerMachineConfiguration("furnace", 
			new MachineStructure(
				// Center
				new PReq(needAir,      1,  0, 0),
				
				// Middle
				new PReq(stonePlate,   1,  0, -1), new PReq(stonePlate,   1, -1, -1), new PReq(stonePlate,   1,  1, -1),
				new PReq(ironBars,     1, -1, 0),                                   new PReq(ironBars,     1,  1, 0),

				// Back
				new PReq(structure, 2,  0,  0), new PReq(structure, 2, -1,  0), new PReq(structure, 2,  1,  0), 
				new PReq(structure, 2, -1, -1), new PReq(structure, 2,  0, -1), new PReq(structure, 2,  1, -1),

				// Middle
				new PReq(structure, 0,  1,  0),                                    new PReq(structure, 0, -1,  0),
				new PReq(structure, 0,  1, -1), new PReq(structure, 0, -1, -1), new PReq(structure, 0,  0, -1),

				// Top
				new PReq(stoneSlab, 0, 0, 1), new PReq(stoneSlab, 0, -1, 1), new PReq(stoneSlab, 0,  1, 1),
				new PReq(stoneSlab, 1, 0, 1), new PReq(stoneSlab, 1, -1, 1), new PReq(stoneSlab, 1,  1, 1),
				new PReq(stoneSlab, 2, 0, 1), new PReq(stoneSlab, 2, -1, 1), new PReq(stoneSlab, 2,  1, 1)
				));
		
		// BEHIND | SIDE | BELOW
		registerMachineConfiguration("crusher", 
			new MachineStructure( // Snirk version, bottom up
				// Bottom
				new PReq(structure, 0, 1, 0),                                 new PReq(structure, 0, -1, 0),
				new PReq(structure, 1, -1, 0), new PReq(stonePlate, 1, 0 ,0), new PReq(structure, 1, 1, 0),
				new PReq(structure, 2, -1, 0), new PReq(structure, 1, 0 ,0), new PReq(structure, 2, 1, 0),
				
				// Floor 1
				new PReq(structure, 0, -1, -1), new PReq(structGlass, 0, 0, -1), new PReq(structure, 0, 1, -1),
				new PReq(structGlass, 1, -1, -1), new PReq(needAir, 1, 0, -1), new PReq(structGlass, 1, 1, -1),
				new PReq(structure, 2, -1, -1), new PReq(structGlass, 2, 0, -1), new PReq(structure, 2, 1, -1),
						
				// Floor 2
				new PReq(structure, 0, -1, -2), new PReq(structGlass, 0, 0, -2), new PReq(structure, 0, 1, -2),
	           new PReq(structGlass, 1, -1, -2), new PReq(blockBreaker, 1, 0, -2), new PReq(structGlass, 1, 1, -2),
				new PReq(structure, 2, -1, -2), new PReq(structGlass, 2, 0, -2), new PReq(structure, 2, 1, -2),
				
				// Top
				new PReq(stoneSlab, 0, -1, -3), new PReq(stoneSlab, 0, 0, -3), new PReq(stoneSlab, 0, 1, -3),
				new PReq(stoneSlab, 1, -1, -3), new PReq(stoneSlab, 1, 0, -3), new PReq(stoneSlab, 1, 1, -3),
				new PReq(stoneSlab, 2, -1, -3), new PReq(stoneSlab, 2, 0, -3), new PReq(stoneSlab, 2, 1, -3)),
				
				
			new MachineStructure( // Komo version, top down
				new PReq(stoneSlab, 0, -1, -1), new PReq(stoneSlab, 0, 0, -1), new PReq(stoneSlab, 0, 1, -1),
				new PReq(stoneSlab, 1, -1, -1), new PReq(stoneSlab, 1, 0, -1), new PReq(stoneSlab, 1, 1, -1),
				new PReq(stoneSlab, 2, -1, -1), new PReq(stoneSlab, 2, 0, -1), new PReq(stoneSlab, 2, 1, -1),
				
				new PReq(structure, 0, -1, 0),                                 new PReq(structure, 0, 1, 0),
				new PReq(structure, 1, -1, 0), new PReq(blockBreaker, 1, 0, 0), new PReq(structure, 1, 1, 0),
				new PReq(structure, 2, -1, 0), new PReq(structure, 2, 0, 0), new PReq(structure, 2, 1, 0),
				
				new PReq(structGlass, 0, -1, 1), new PReq(structGlass, 0, 0, 1), new PReq(structGlass, 0, 1, 1),
				new PReq(structGlass, 1, -1, 1), new PReq(needAir, 1, 0, 1), new PReq(structGlass, 1, 1, 1),
				new PReq(structure, 2, -1, 1), new PReq(structure, 2, 0, 1), new PReq(structure, 2, 1, 1),
				
				new PReq(structure, 0, -1, 2), new PReq(structure, 0, 0, 2), new PReq(structure, 0, 1, 2), 
				new PReq(structure, 1, -1, 2), new PReq(stonePlate, 1, 0, 2), new PReq(structure, 1, 1, 2),
				new PReq(structure, 2, -1, 2), new PReq(structure, 2, 0, 2), new PReq(structure, 2, 1, 2)
					));

		/*
		ironFurnace = MachineStructure.Transform(cobbleFurnace, 
				new ReqTransform(stonePlate, new BlockRequirement(BlockRegistrar.ironHeatPlate)),
				new ReqTransform(cobbleStruct, new BlockRequirement(BlockRegistrar.ironStructure)),
				new ReqTransform(stoneSlab, new BlockRequirement(BlockRegistrar.cookedSlab)));

		steelFurnace = MachineStructure.Transform(cobbleFurnace, 
				new ReqTransform(stonePlate, new BlockRequirement(BlockRegistrar.steelHeatPlate)),
				new ReqTransform(cobbleStruct, new BlockRequirement(BlockRegistrar.steelStructure)),
				new ReqTransform(stoneSlab, new BlockRequirement(BlockRegistrar.ironSlab))); */
	}		
}
