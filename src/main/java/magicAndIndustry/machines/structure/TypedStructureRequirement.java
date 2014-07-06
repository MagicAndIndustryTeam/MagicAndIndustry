package magicAndIndustry.machines.structure;


public abstract class TypedStructureRequirement extends StructureRequirementBase
{
	/** The type (iron/steel/etc) of core. */
	public String type;
	
	public TypedStructureRequirement(String coreType)
	{
		type = coreType;
	}
}
