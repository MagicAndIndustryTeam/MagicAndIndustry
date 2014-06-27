package magicAndIndustry.research.display;

import java.awt.Image;
import java.util.HashMap;

/** An object which can be displayed on a research page. */
public interface IPageDisplay 
{
	/** Return an image to render in the GUI using parameters from the XML - i.e. [display type="image" src="..."] would pass "src". */
	public Image Display(HashMap<String, Object> parameters);
	
	/** 
	 * The tag used in the formatting code in research XMLs in the "type" attribute, i.e. [display type="image" src="..."]. 
	 * Please keep it lower-case. 
	*/
	public String getTypeName();
}
