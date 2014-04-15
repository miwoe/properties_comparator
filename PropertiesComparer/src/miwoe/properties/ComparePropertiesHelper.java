package miwoe.properties;

public class ComparePropertiesHelper {

	public static String lineBreak = "\n";
	
	public static String getHelpContent() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("CompareProperties program developed by Michael Woelm - Version 0.2 - 15.04.2014");
		sb.append(lineBreak);
		sb.append("-------------------------------------------------------------------------------");
		sb.append(lineBreak);
		sb.append(lineBreak);
		sb.append("#Usage#" + lineBreak);
		sb.append("Command: CompareProperties file1 file2 mode"+ lineBreak);
		sb.append(lineBreak);
		sb.append("#file1#: Absolute Path" + lineBreak);
		sb.append("#file2#: Absolute Path" + lineBreak);
		
		sb.append("#mode#" + lineBreak);
		sb.append("countOnly   : Compares only the number of properties between file1 and file2." + lineBreak);
		sb.append("1           : Checks if property is in file2 but not in file1" + lineBreak);
		sb.append("2           : Checks if property is in file1 but not in file2" + lineBreak);
		sb.append("12          : Bidirectional..."+lineBreak);
		sb.append("v           : Checks also values, if a property is in both files (trimmed spaces)"+lineBreak);
		sb.append(lineBreak);
		sb.append("Example: CompareProperties dev.properties test.properties 12v"+lineBreak);
		sb.append("  Compares dev.properties with test.properties by key and value.");
		sb.append(lineBreak);
		sb.append("Example: CompareProperties dev.properties test.properties 2"+lineBreak);
		sb.append("  Checks if test.properties is missing any property used in dev.properties.");
		
		
		return sb.toString();
	}
	
}
