package magicAndIndustry.items;

import magicAndIndustry.blocks.BlockRegistrar;
import magicAndIndustry.utils.Textures;
import magicAndIndustry.utils.Utils;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EngineeringTableItem extends DoubleBlockItem 
{
	public EngineeringTableItem()
	{
		super("engineering_table_item", BlockRegistrar.engineeringTable);
		this.setTextureName(Textures.item("engineering_table_item"));
	}
	
	/*
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg)
	{
		itemIcon= reg.registerIcon(Utils.Texture(this));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int damage)
	{
		return itemIcon;
	}
	*/
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitx, float hity, float hitz)
	{
		if (super.onItemUse(stack, player, world, x, y, z, side, hitx, hity, hitz) && !world.isRemote)
		{
			// TODO figure out side facing.
			return true;
		}
		return true;
	}
}
