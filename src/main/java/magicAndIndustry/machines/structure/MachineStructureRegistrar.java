package magicAndIndustry.machines.structure;

import java.util.HashMap;

import magicAndIndustry.MagicAndIndustry;
import magicAndIndustry.blocks.BlockRegistrar;
import magicAndIndustry.utils.RelativeFaceCoords;
import net.minecraft.init.Blocks;

/**
 * Register possible machine structure configurations for your machines here.
 * Note that the internal database is created during Magic And Industry's preInit event.
 */
public class MachineStructureRegistrar
{	
	public static StructureRequirementBase needAir = new BlockRequirement(Blocks.air),
		ironBars = new BlockRequirement(Blocks.iron_bars),
		heatPlate = new HeatPlateRequirement(),
		slab = new MachineSlabRequirement(),
		structure = new StructureBlockRequirement(),
		structGlass = new StructOrGlassRequirement(),
		blockBreaker = new BlockRequirement(BlockRegistrar.blockBreaker);
	
	private static HashMap<String, MachineStructure[]> machineStructureMap;
	private static HashMap<String, MachineStructure> structureIDMap;

	/**
	 * Gets the registered possible machine structures for a machine.
	 * @param machineID The ID of the machine
	 * @return
	 */
	public static MachineStructure[] getStructuresForMachineID(String machineID)
	{
		return machineStructureMap.get(machineID);
	}	
	
	public static MachineStructure getMachineStructureByID(String structureID)
	{
		return structureIDMap.get(structureID);
	}

	/**
	 * Registers machine structure(s) for a machine with a specific ID.
	 * Also registers the machine configurations by ID!
	 * If the ID already exists, the new structure(s) are added to its collection.
	 * @param machineID The ID of the machine.
	 * @param structures A collection of possible machine structures for its core.
	 */
	public static void registerMachineConfiguration(String machineID, MachineStructure... structures)
	{
		// If we already have structures, add more
		if (machineStructureMap.containsKey(machineID))
		{
			// Get the entry, make a new array
			MachineStructure[] entry = machineStructureMap.get(machineID);
			MachineStructure[] newEntry = new MachineStructure[entry.length + structures.length];

			// Copy stuff over
			int i=0;
			for (; i < entry.length; i++)
				newEntry[i] = entry[i];
			for (int j = 0; j < structures.length; j++, i++)
				newEntry[i] = structures[j];

			// Update the map.
			machineStructureMap.remove("machineID");
			machineStructureMap.put(machineID, newEntry);
		}
		else machineStructureMap.put(machineID, structures);
		
		for (MachineStructure structure : structures)
		{
			if (!structureIDMap.containsKey(structure.ID))
			{
				structureIDMap.put(structure.ID, structure);
			}
			// else log silently
			else MagicAndIndustry.logger.warn("A MachineStructure, " + machineID + ", was registered twice - it will be ignored this time. This is perfectly normal and unavoidable behavior.");
		}
	}
	/**
	 * Creates internal database in MAI and also registers our machine structures.
	 */
	public static void assimilate()
	{
		machineStructureMap = new HashMap<String, MachineStructure[]>();
		structureIDMap = new HashMap<String, MachineStructure>();
		
		registerMachineConfiguration("furnace", 
			new MachineStructure("furnace",
				// Center
				new PReq(needAir,      1,  0, 0),
				
				// Middle
				new PReq(heatPlate,   1,  0, -1), new PReq(heatPlate,   1, -1, -1), new PReq(heatPlate,   1,  1, -1),
				new PReq(ironBars,     1, -1, 0),                                   new PReq(ironBars,     1,  1, 0),

				// Back
				new PReq(structure, 2,  0,  0), new PReq(structure, 2, -1,  0), new PReq(structure, 2,  1,  0), 
				new PReq(structure, 2, -1, -1), new PReq(structure, 2,  0, -1), new PReq(structure, 2,  1, -1),

				// Middle
				new PReq(structure, 0,  1,  0),                                    new PReq(structure, 0, -1,  0),
				new PReq(structure, 0,  1, -1), new PReq(structure, 0, -1, -1), new PReq(structure, 0,  0, -1),

				// Top
				new PReq(slab, 0, 0, 1), new PReq(slab, 0, -1, 1), new PReq(slab, 0,  1, 1),
				new PReq(slab, 1, 0, 1), new PReq(slab, 1, -1, 1), new PReq(slab, 1,  1, 1),
				new PReq(slab, 2, 0, 1), new PReq(slab, 2, -1, 1), new PReq(slab, 2,  1, 1)
				).setStripes(new RelativeFaceCoords(0, -1, 0), new RelativeFaceCoords(0, 1, 0)));
		
		// BEHIND | SIDE | BELOW
		registerMachineConfiguration("crusher", 
			new MachineStructure("beverlyCrusher",
				// Bottom
				new PReq(structure, 0, 1, 0),                                 new PReq(structure, 0, -1, 0),
				new PReq(structure, 1, -1, 0), new PReq(heatPlate, 1, 0 ,0), new PReq(structure, 1, 1, 0),
				new PReq(structure, 2, -1, 0), new PReq(structure, 1, 0 ,0), new PReq(structure, 2, 1, 0),
				
				// Floor 2
				new PReq(structure, 0, -1, -1), new PReq(structGlass, 0, 0, -1), new PReq(structure, 0, 1, -1),
				new PReq(structGlass, 1, -1, -1), new PReq(needAir, 1, 0, -1), new PReq(structGlass, 1, 1, -1),
				new PReq(structure, 2, -1, -1), new PReq(structGlass, 2, 0, -1), new PReq(structure, 2, 1, -1),
						
				// Floor 3
				new PReq(structure, 0, -1, -2), new PReq(structGlass, 0, 0, -2), new PReq(structure, 0, 1, -2),
	           new PReq(structGlass, 1, -1, -2), new PReq(blockBreaker, 1, 0, -2), new PReq(structGlass, 1, 1, -2),
				new PReq(structure, 2, -1, -2), new PReq(structGlass, 2, 0, -2), new PReq(structure, 2, 1, -2),
				
				// Top
				new PReq(slab, 0, -1, -3), new PReq(slab, 0, 0, -3), new PReq(slab, 0, 1, -3),
				new PReq(slab, 1, -1, -3), new PReq(slab, 1, 0, -3), new PReq(slab, 1, 1, -3),
				new PReq(slab, 2, -1, -3), new PReq(slab, 2, 0, -3), new PReq(slab, 2, 1, -3)
				).setStripes(new RelativeFaceCoords(0, -1, 0), new RelativeFaceCoords(0, 1, 0)),
				
				
			new MachineStructure("wesleyCrusher",
				// Top
				new PReq(slab, 0, -1, -1), new PReq(slab, 0, 0, -1), new PReq(slab, 0, 1, -1),
				new PReq(slab, 1, -1, -1), new PReq(slab, 1, 0, -1), new PReq(slab, 1, 1, -1),
				new PReq(slab, 2, -1, -1), new PReq(slab, 2, 0, -1), new PReq(slab, 2, 1, -1),
				
				// 3rd
				new PReq(structure, 0, -1, 0),                                 new PReq(structure, 0, 1, 0),
				new PReq(structure, 1, -1, 0), new PReq(blockBreaker, 1, 0, 0), new PReq(structure, 1, 1, 0),
				new PReq(structure, 2, -1, 0), new PReq(structure, 2, 0, 0), new PReq(structure, 2, 1, 0),
				
				// 2nd
				new PReq(structGlass, 0, -1, 1), new PReq(structGlass, 0, 0, 1), new PReq(structGlass, 0, 1, 1),
				new PReq(structGlass, 1, -1, 1), new PReq(needAir, 1, 0, 1), new PReq(structGlass, 1, 1, 1),
				new PReq(structure, 2, -1, 1), new PReq(structure, 2, 0, 1), new PReq(structure, 2, 1, 1),
				
				// ground
				new PReq(structure, 0, -1, 2), new PReq(structure, 0, 0, 2), new PReq(structure, 0, 1, 2), 
				new PReq(structure, 1, -1, 2), new PReq(heatPlate, 1, 0, 2), new PReq(structure, 1, 1, 2),
				new PReq(structure, 2, -1, 2), new PReq(structure, 2, 0, 2), new PReq(structure, 2, 1, 2)
					).setStripes(new RelativeFaceCoords(0, -1, 0), new RelativeFaceCoords(0, 1, 0)));
	}		
}
