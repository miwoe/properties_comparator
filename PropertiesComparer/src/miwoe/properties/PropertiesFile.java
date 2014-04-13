package miwoe.properties;

import java.io.File;
import java.util.Properties;

public class PropertiesFile {
	
	File file;
	Properties properties;
	public PropertiesFile(File file, Properties properties) {
		super();
		this.file = file;
		this.properties = properties;
	}
	public File getFile() {
		return file;
	}
	public Properties getProperties() {
		return properties;
	}

	
}
