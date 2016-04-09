package com.alssc.komicaparser.util;

import com.alssc.komicaparser.util.datamodel.XMLDataModel;
import com.alssc.komicaparser.util.xmlhelper.ConfigXMLDataHelper;
import com.alssc.komicaparser.util.xmlhelper.MappingXMLDataHelper;


public class DataLoader {
	
	XMLDataModel xmlDataModule;

	
	public DataLoader() {
		xmlDataModule = new XMLDataModel();
	}
	
	public void loadAll() {
		loadConfig();
		loadMapping();
	}
	
	public void loadConfig() {
		ConfigXMLDataHelper dataHelper = new ConfigXMLDataHelper();
		xmlDataModule = dataHelper.parseAllTag(xmlDataModule);
	}
	
	public void loadMapping() {
		MappingXMLDataHelper dataHelper = new MappingXMLDataHelper();
		xmlDataModule = dataHelper.parseTag(xmlDataModule);
	}
	
	public XMLDataModel getXMLDataModule() {
		return this.xmlDataModule;
	}
}
