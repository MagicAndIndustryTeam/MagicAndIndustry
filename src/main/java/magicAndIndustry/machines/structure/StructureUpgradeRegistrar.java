package magicAndIndustry.machines.structure;

import java.util.HashMap;

import magicAndIndustry.machines.StructureUpgrade;

public class StructureUpgradeRegistrar 
{
	/** Get the subclasses by registered ID. */
	private static HashMap<String, Class<? extends StructureUpgrade>> namesToClasses = new HashMap<String, Class<? extends StructureUpgrade>>();
	/** Get a registered ID of a subclass. */
	private static HashMap<Class<? extends StructureUpgrade>, String> classesToNames = new HashMap<Class<? extends StructureUpgrade>, String>();
	
	/**
	 * TODO this will go in the API class, it's an API function.
	 * Register a StructureUpgrade subclass with the registrar. Give it a name to save as an NBT ID.
	 * @param classical The subclass to register
	 * @param name The ID of the structure upgrade.
	 */
	public static void RegisterStructureUpgrade(Class<? extends StructureUpgrade> classical, String name)
	{
		if (namesToClasses.containsKey(name))
			throw new IllegalArgumentException("Duplicate StructureUpgrade id: " + name);
		
		else // We can register it!
		{
			// This way we can check for a class to instantiate by checking the registered hashmap.
			// It'll be registered on initialize.
			namesToClasses.put(name, classical);
			classesToNames.put(classical, name);
		}
	}
	
	public static Class<? extends StructureUpgrade> getUpgradeClassByID(String id)
	{
		return namesToClasses.get(id);
	}
	
	public static String getStructureID(Class<? extends StructureUpgrade> classical)
	{
		return classesToNames.get(classical);
	}
}
