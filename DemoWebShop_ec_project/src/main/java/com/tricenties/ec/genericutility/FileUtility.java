package com.tricenties.ec.genericutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	
	public String getProperty(String key) throws IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/testdata/commondata.properties");
		Properties prop=new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}
}
