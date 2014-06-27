package magicAndIndustry.research;

public class ResearchPaper 
{
	/** The abstract part of the paper. Not encoded. */
	public String Abstract;
	
	/** The content of the paper. Uses MAIEncoding - see documentation for details. */
	public String Content;
	
	/** Specifies what the paper displays: plain text, some kind of machine, etc. */
	public static enum PaperType
	{
		/** Displays only text/information. */
		Text,
		/** Displays data on a vanilla crafting recipe. */
		Vanilla_Craft,
		Engineering_Craft,
		
	}
}
