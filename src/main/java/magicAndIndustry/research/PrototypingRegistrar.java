package magicAndIndustry.research;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.item.Item;

public class PrototypingRegistrar
{
	private static ArrayList<String> registeredIDs;
	private static HashMap<Item, String> requirementCollection;
	
	static void Initialize()
	{
		registeredIDs = new ArrayList<String>();
		requirementCollection = new HashMap<Item, String>();
	}
	
	//*
	/**
	 * Register an {@link IPrototypingComponent} for the prototyping system. Include the type of component.
	 * @param item The item to register as a valid component.
	 * @param type The type of component the item is.
	 */
	public static void registerComponent(Item item, String type)
	{
		if (!registeredIDs.contains(type)) registeredIDs.add(type);
		
		if (requirementCollection.containsKey(item))
			throw new RuntimeException("Item " + item.toString() + " (with type " + type + ") has been registered twice!");
		
		requirementCollection.put(item, type);
	}
	//*/
}
