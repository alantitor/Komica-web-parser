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

public class MappingXMLDataHelper {

	private Document doc;
	String PATH = Config.MAPPING_XML_PATH;
	String FILEPATTERN = "mapping";
	XMLDataModel dataModule;

	
	private void XMLDocInit(String filename) {	
		File inputFile = new File(filename);
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
	
	private void parseContain() {
		Node mtNode = doc.getElementsByTagName("komica-mapping").item(0);
		Element elem = (Element) mtNode;
		
		String packageValue = ((Element) elem.getElementsByTagName("class").item(0)).getAttribute("package");
		String typeValue = ((Element) elem.getElementsByTagName("class").item(0)).getAttribute("type");
		
		NodeList actionNL = elem.getElementsByTagName("action");
		String key = "";
		String rule = "";
		for (int count = 0; count < actionNL.getLength(); count++) {
			key = ((Element) ((Element)actionNL.item(count)).getElementsByTagName("key").item(0)).getAttribute("value");
			rule = ((Element) ((Element)actionNL.item(count)).getElementsByTagName("rule").item(0)).getAttribute("value");
			
			updateValue(packageValue, typeValue, key, rule);
			
			key = "";
			rule = "";
		}
	}
	
	/**
	 * 
	 * @param pv package path
	 * @param tv data type
	 * @param kv key value
	 * @param rv rule name
	 */
	private void updateValue(String pv, String tv, String kv, String rv) {
		//System.out.println(pv + ", " + tv + ", " + kv + ", " + rv);
		
		switch(tv) {
			case "menu":
				dataModule.updateMenuMapByValue(kv, pv + "." + rv);
				break;
			case "bulletin":
				dataModule.updateBulletinMapByValue(kv, pv + "." + rv);
			case "sticky":
				dataModule.updateStickyMapByValue(kv, pv + "." + rv);
				break;
			default:
				//
		}
	}
	
	public XMLDataModel parseTag(XMLDataModel dataModule) {
		this.dataModule = dataModule;
		
		File dir = new File(Config.MAPPING_XML_PATH);
		String[] dirList = dir.list();
		String filename;
		
		if (dirList == null) {
			// does not exist or is not a directory.
		} else {
			for (int i = 0; i < dirList.length; i++) {
				filename = dirList[i];
				
				// open xml file that define mapping rule. find "mappingxxx" file.
				if (filename.toLowerCase().contains(FILEPATTERN.toLowerCase())) {
					//System.out.println(Config.MAPPING_XML_PATH + filename);
					XMLDocInit(PATH + filename);
					parseContain();
				}
			}
		}		
		
		return this.dataModule;
	}
}
