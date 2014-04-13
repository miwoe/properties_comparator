package miwoe.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

public class CompareProperties {

	Logger logger = Logger.getLogger(getClass());
	public static void main(String[] args) {
		if (args.length != 3) {
			throw new RuntimeException("Error: Use Arguments: source1 source2 mode");
			
		}
		
		File file1 = new File(args[0]);
		File file2 = new File(args[1]);
		if (!file1.exists() || !file2.exists()) {
			throw new RuntimeException("Error: Cannot find files.");
		}
		CompareProperties cp;
		try {
			cp = new CompareProperties(file1, file2);
		} catch (IOException e) {
			throw new RuntimeException("Error: Files could not be read or are no properties files.", e);
		}
		String mode = args[2];
		if (mode.contains("count")) {
			cp.countOnly = true;
		}
		else {
			if (mode.contains("1")) cp.compare21 = true;
			if (mode.contains("2")) cp.compare12 = true;
			if (mode.contains("v")) cp.compareValues = true;
		}
	
		
		HashMap<String, CompareResult> result = cp.compare();
		
		cp.printOutResult(result);
		
	}
	
	PropertiesFile propertiesFile1; PropertiesFile pf1;
	PropertiesFile propertiesFile2; PropertiesFile pf2;
	
	boolean showValue;
	boolean compare12;
	boolean compare21;
	boolean countOnly;
	boolean compareValues;
	
	
	public CompareProperties(File source1, File source2) throws IOException {
		logger.debug("File1: "+ source1.getAbsolutePath());
		Properties prop1 = new Properties();
		FileInputStream in1 = new FileInputStream(source1.getAbsoluteFile());
		prop1.load(in1);
		in1.close();
		propertiesFile1 = new PropertiesFile(source1, prop1);
		
		Properties prop2 = new Properties();
		FileInputStream in2 = new FileInputStream(source2.getAbsoluteFile());
		prop2.load(in2);
		in2.close();
		propertiesFile2 = new PropertiesFile(source2, prop2);
		pf1 = propertiesFile1;
		pf2 = propertiesFile2;
		
	}
	
	public HashMap<String, CompareResult> compare() {
		HashMap<String, CompareResult> result = new HashMap<String, CompareResult>();
		result.put("count", count());
		if (!countOnly) {
			if (compare12) {
				result.putAll(compare(pf1, pf2));
			}
			if (compare21) {
				result.putAll(compare(pf2, pf1));
			}
			
			
		}
		
		return result;
	}
	
	public HashMap<String, CompareResult> compare(PropertiesFile propSource, PropertiesFile propTarget) {
		HashMap<String, CompareResult> result = new HashMap<String, CompareResult>();
		for (Object obj : propSource.properties.keySet()) {
			String key = (String) obj;
			String value = (String) propSource.properties.get(key);
			logger.debug(key + " = " + value);
			if (!propTarget.properties.containsKey(key)) {
				CompareResult cr = new CompareResult();
				cr.message = propTarget.file.getName()+ " does not contain key " + key;
				cr.key = key;
				cr.key = value;
				result.put(key, cr);
			}
			else if (compareValues) {
				String val1 = (String) propTarget.properties.get(key);
				String val2 = (String) propSource.properties.get(key);
				if (!(val1.trim().equals(val2.trim()))) {
					CompareResult cr = new CompareResult();
					cr.message = key+ " does not have same value( '" + val1 + "' <-> '" + val2 +"' )";
					cr.key = key;
					cr.key = value;
					result.put(key, cr);
				}
			}
		}
		return result;
	}
	
	
	public CompareResult count() {
		CompareResult cr = new CompareResult();
		
		
		if (propertiesFile1.properties.size()!=propertiesFile2.properties.size()) {
			cr.message = "Size " + pf1.file.getName() + " = " + pf1.properties.size() + 
					" !=" + "Size " + pf2.file.getName() + " = " + pf2.properties.size();	
		}
		else {
			cr.message = "Size " + pf1.file.getName() + " = " + pf1.properties.size() + 
					" ==" + "Size " + pf2.file.getName() + " = " + pf2.properties.size();
		}
		cr.key = "count(keys)";
		cr.value = Integer.toString(pf1.properties.size() - pf2.properties.size());
		return cr;
	}
	
	
	public void printOutResult(HashMap<String, CompareResult> result) {
		for (String key : result.keySet()) {
			CompareResult cr = result.get(key);
			System.out.println(cr.message);
		}
	}
	

}
