package magicAndIndustry.research.display;

import java.awt.Image;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;

/**
 * A page display which can show a vanilla crafting recipe.
 */
public class CraftingDisplay implements IPageDisplay
{
	public IRecipe CraftingRecipe;
	
	/** Creates a display from an IRecipe */
	public CraftingDisplay(IRecipe recipe)
	{
		CraftingRecipe = recipe;
	}
	
	/** Creates a new display from a GameRegistry style recipe register using Minecraft's recipe registering code. */
	public CraftingDisplay(ItemStack output, Object... recipe)
	{
		String gridString = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (recipe[i] instanceof String[])
        {
            String[] lines = (String[])((String[])recipe[i++]);

            for (int l = 0; l < lines.length; ++l)
            {
                String line = lines[l];
                ++k;
                j = line.length();
                gridString = gridString + line;
            }
        }
        else
        {
            while (recipe[i] instanceof String)
            {
                String line = (String)recipe[i++];
                ++k;
                j = line.length();
                gridString = gridString + line;
            }
        }

        HashMap<Character, ItemStack> itemCharMap;

        for (itemCharMap = new HashMap<Character, ItemStack>(); i < recipe.length; i += 2)
        {
            char Char = (Character)recipe[i];
            ItemStack currStack = null;

            if (recipe[i + 1] instanceof Item)
            {
                currStack = new ItemStack((Item)recipe[i + 1]);
            }
            else if (recipe[i + 1] instanceof Block)
            {
                currStack = new ItemStack((Block)recipe[i + 1], 1, 32767);
            }
            else if (recipe[i + 1] instanceof ItemStack)
            {
                currStack = (ItemStack)recipe[i + 1];
            }

            itemCharMap.put(Char, currStack);
        }

        ItemStack[] itemsGrid = new ItemStack[j * k];

        for (int i_grid = 0; i_grid < j * k; ++i_grid)
        {
            char c0 = gridString.charAt(i_grid);

            if (itemCharMap.containsKey(Character.valueOf(c0)))
            {
                itemsGrid[i_grid] = ((ItemStack)itemCharMap.get(Character.valueOf(c0))).copy();
            }
            else
            {
                itemsGrid[i_grid] = null;
            }
        }

        CraftingRecipe = new ShapedRecipes(j, k, itemsGrid, output);
	}

	@Override
	public Image Display(HashMap<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTypeName() 
	{
		return "crafting";
	}
}
