package magicAndIndustry.machines.event;

/**
 * Used by StructureUpgrades to influence the cost and efficiency of their processing operations.
 */
public class ProcessingEvent
{
	private float processingRate;
	private int processingFuel;
	
	/**
	 * Set the time (as a percent) for processing, multiplied to the base cost. <br/>
	 * For example, handling this event with {@code setProcessingCost(0.03F)} would
	 * cause a ~3% increase in the amount of time it would take to process something. <br/>
	 * Each upgrade's cost is multiplied cumulatively.
	 * @param percent The percentage to increase the time cost by.
	 */
	public void setProcessingCost(float percent)
	{
	}
	
	/**
	 * Set the cost (as a percent) for energy needed every 10 ticks to process an item. <br/>
	 * For example, handling this event with {@code setEnergyCost(-0.1F)} would bring the
	 * energy cost of this operation down by ~10%. <br/>
	 * Each upgrade's cost is multiplied cumulatively.
	 * @param percent The percentage to increase the energy cost by.
	 */
	public void setEnergyCost(float percent)
	{
	}
	
	public int getEnergyCost() { return 0; }
	
	public int getProceesingTime() { return 0;}
	
	/**
	 * There aren't any variables to initialize.
	 */
	public ProcessingEvent()
	{
		
	}
}
