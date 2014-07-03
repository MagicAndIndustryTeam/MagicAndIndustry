package magicAndIndustry.blocks;

import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;

/**
 * Constructor:
 * name, hardness, resistance, harvestLevel, dropItem, minDrop, maxDrop, expDrop.
 * @author Joshua
 *
 */
public class FortuneOre extends IngotOre
{
	private Item dropItem;
	private int dropMin, dropMax;
	private int dropExp;
	private Random rand = new Random();
	
	/**
	 * Constructor for registry.
	 * @param name Block name
	 * @param harvestLevel Pickaxe harvest level
	 * @param hardness Block hardness
	 * @param resistance Block resistance
	 * @param itemDrop Item to drop
	 * @param minDrop Minimum to drop 
	 * @param maxDrop Maximum to drop - set to -1 to default on minimum.
	 * @param expDrop Exp to drop
	 */
	public FortuneOre(String name, float hardness, float resistance, int harvestLevel, int minDrop, int maxDrop, int expDrop)
	{
		super(name, hardness, resistance, harvestLevel);
		dropMin = minDrop;
		dropMax = maxDrop;
		dropExp = expDrop;
	}
	
	/**
	 * Called in ItemRegistrar so the items can be registered first.
	 */
	public void setDrop(Item item)
	{
		dropItem = item;
	}
	
	@Override
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		return dropItem;
	}
	
	@Override
	public int quantityDroppedWithBonus(int fortune, Random rand)
	{
		return quantityDropped(rand) + rand.nextInt(fortune + 1);
	}
	
	@Override
	public int quantityDropped(Random rand)
	{
		if (dropMax == -1) return dropMin;
		return dropMin + rand.nextInt(dropMax - dropMin);
	}
	
	@Override
	public int getExpDrop(IBlockAccess world, int meta, int fortune)
	{
		if (dropExp == 0) return 0;
		if (getItemDropped(meta, rand, fortune) != Item.getItemFromBlock(this))
		{
			return 1 + rand.nextInt(dropExp);
		}
		return 0;
	}
	
	@Override
	protected ItemStack createStackedBlock(int meta)
	{
		return new ItemStack(this);
	}
}
