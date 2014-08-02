package magicAndIndustry.machines.event;

/**
 * Called by machine cores when requesting power.
 * Machines store power as an integer of watts, upgrades
 * can convert fuels/etc into these
 */
public class PowerRequestEvent
{
	private int energyMax;
	private int energyCurrent;
	
	/**
	 * Gets the maximum amount of power the machine can store.
	 */
	public int getMaxEnergyLevel() { return energyMax; }
	
	/**
	 * Gets the amount of power the machine can add.
	 */
	public int getRemainingSpace() { return energyMax - energyCurrent; }
	
	/**
	 * Adds the specified power to the machine.
	 * If you choose not to floor the amount added, this will
	 * 
	 * @param powerAmount The amonut of power to add
	 * @return Any discarded power
	 */
	public int addEnergy(int powerAmount)
	{
		int total = energyCurrent + powerAmount;
		if (total > energyMax) 
		{
			energyCurrent = energyMax;
			return total - energyMax;
		}
		energyCurrent = total;
		return 0;
	}
	
	public int getCurrentEnergy() { return energyCurrent; }
	
	public boolean isComplete() { return energyMax == energyCurrent; }
	
	public PowerRequestEvent(int max, int current)
	{
		energyMax = max; energyCurrent = current;
	}
	
	public PowerRequestEvent(int max)
	{
		energyMax = max; energyCurrent = 0;
	}
}
