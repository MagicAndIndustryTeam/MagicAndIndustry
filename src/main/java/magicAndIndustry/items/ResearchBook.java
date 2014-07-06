package magicAndIndustry.items;

import magicAndIndustry.MagicAndIndustry;
import magicAndIndustry.gui.ResearchBookContainer;
import magicAndIndustry.utils.Textures;
import magicAndIndustry.utils.Utils;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ResearchBook extends Item
{
	@SideOnly(Side.CLIENT)
	private IIcon iconOpened;
	
	private final String type;
	
	public ResearchBook(String type)
	{
		super(); this.type = type;
		setUnlocalizedName("book_" + type);
		maxStackSize = 1;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if (!world.isRemote && !player.isSneaking())
		{
			player.openGui(MagicAndIndustry.instance, 30, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
		return item;
	}
	
	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player,  World world, int x, int y, int z, int side, float hitx, float hity, float hitz)
	{
		return player.isSneaking();
	}
}
