package miwoe.properties;

import java.io.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.TreeSet;

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

	public void writeFile() throws IOException {
		OutputStream out = null;
		try {
			out = new FileOutputStream( file );
			Properties tmp = new Properties() {
				@Override
				public synchronized Enumeration<Object> keys() {
					return Collections.enumeration(new TreeSet<Object>(super.keySet()));
				}
			};
			tmp.putAll(properties);
			tmp.store(new FileWriter(file), null);
		}
		finally {
			if (out != null) out.close();
		}

	}

	
}
