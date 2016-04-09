package com.alssc.komicaparser.util.xmlhelper;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.alssc.komicaparser.resources.Config;
import com.alssc.komicaparser.util.datamodel.XMLDataModel;

public class ConfigXMLDataHelper {

	private Document doc;

	
	public ConfigXMLDataHelper() {
		XMLDocInit();
	}
	
	private void XMLDocInit() {
		File inputFile = new File(Config.CFG_XML_FILE);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  XMLDataModel parseMetaDataTag(XMLDataModel dataModule) {
		Node mtNode = doc.getElementsByTagName("meta-data").item(0);
		Element elem = (Element) mtNode;
		
		dataModule.setMetaTitle(elem.getElementsByTagName("title").item(0).getTextContent());
		dataModule.setMetaTime(elem.getElementsByTagName("time").item(0).getTextContent());
		dataModule.setMetaVersion(elem.getElementsByTagName("version").item(0).getTextContent());
		dataModule.setMetaUsesKpt(((Element) elem.getElementsByTagName("uses-kpt").item(0)).getAttribute("targetKpt"));
		
		return dataModule;
	}
	
	public XMLDataModel parseSourceTag(XMLDataModel dataModule) {
		NodeList mtNodeList = doc.getElementsByTagName("source");
		
		for (int sourceCount = 0; sourceCount < mtNodeList.getLength(); sourceCount++) {
			Element childElem = (Element) mtNodeList.item(sourceCount);			
			
			String key = childElem.getElementsByTagName("title").item(0).getTextContent().toUpperCase();
			dataModule.putKeys(key);
			dataModule.putTitleMap(key, childElem.getElementsByTagName("title").item(0).getTextContent());
			dataModule.putSiteUrleMap(key,childElem.getElementsByTagName("site-url").item(0).getTextContent());
			dataModule.putMenuUrlMap(key, childElem.getElementsByTagName("menu-url").item(0).getTextContent());
			
			for (int mappingCount = 0; mappingCount < childElem.getElementsByTagName("use-mapping").getLength(); mappingCount++) {
				Element umChild = (Element) childElem.getElementsByTagName("use-mapping").item(mappingCount);
			
				switch (umChild.getAttribute("type")) {
				case "menu":
					for (int propertyCount = 0; propertyCount < umChild.getElementsByTagName("property").getLength(); propertyCount++) {
						dataModule.putMenuMap(((Element) umChild.getElementsByTagName("property").item(propertyCount)).getAttribute("key").toUpperCase(),
								((Element) umChild.getElementsByTagName("property").item(propertyCount)).getAttribute("value"));
					}				
					break;
				case "bulletin":
					for (int propertyCount = 0; propertyCount < umChild.getElementsByTagName("property").getLength(); propertyCount++) {
						dataModule.putBulletinMap(((Element) umChild.getElementsByTagName("property").item(propertyCount)).getAttribute("key"),
													((Element) umChild.getElementsByTagName("property").item(propertyCount)).getAttribute("value"));
					}				
					break;
				case "sticky":
					for (int propertyCount = 0; propertyCount < umChild.getElementsByTagName("property").getLength(); propertyCount++) {
						dataModule.putStickyMap(((Element) umChild.getElementsByTagName("property").item(propertyCount)).getAttribute("key"),
													((Element) umChild.getElementsByTagName("property").item(propertyCount)).getAttribute("value"));
					}
					break;
				default:
					// throw new IllegalArgumentException("Invalid day of the week: " + dayOfWeekArg);
				}
			}
		}
		
		return dataModule;
	}
	
	public XMLDataModel parseAllTag(XMLDataModel dataModule) {
		dataModule = parseMetaDataTag(dataModule);
		dataModule = parseSourceTag(dataModule);
		return dataModule;
	}
	
}
