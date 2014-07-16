package magicAndIndustry.machines;

import java.util.HashMap;

import magicAndIndustry.MagicAndIndustry;
import magicAndIndustry.blocks.FurnaceCoreBlock;
import magicAndIndustry.tileEntity.FurnaceCoreEntity;
import magicAndIndustry.tileEntity.base.MachineCoreEntity;

public class MachineRegistrar
{
	private static HashMap<String, Class<? extends MachineCoreEntity>> entityMap;
	
	public static void registrate()
	{
		entityMap = new HashMap<String, Class<? extends MachineCoreEntity>>();
		
		entityMap.put("furnace", FurnaceCoreEntity.class);
	}
	
	public static void registerMachineType(String id, Class<? extends MachineCoreEntity> tileEntityClass)
	{
		if (entityMap.containsKey(id)) throw new RuntimeException("Duplicate Machine Registration of ID " + id);
		
		entityMap.put(id, tileEntityClass);
	}
	
	public static MachineCoreEntity getMachineByID(String id)
	{
		Class<? extends MachineCoreEntity> value = entityMap.get(id);
		if (value == null) return null;
		
		try
		{
			return value.newInstance();
		}
		catch (Exception ex)
		{
			MagicAndIndustry.logger.error("Unable to initialize " + value.getName() + ", registered ID " + id);
			return null;
		}
	}
	
	public static Class<? extends MachineCoreEntity> getMachineClassByID(String id)
	{
		return entityMap.get(id);
	}
}
