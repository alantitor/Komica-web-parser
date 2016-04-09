package com.alssc.komicaparser.parser.access;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.alssc.komicaparser.util.datamodel.BulletinDataModel;


public class AccessBulletin {

	private Object toj;
	private Map<String, Object> objectMapping = new HashMap<String, Object>();
	
	
	public boolean init(String className) {		
		if (objectMapping.containsKey(className)) {
			this.toj = objectMapping.get(className);
		} else {
			try {
				toj = Class.forName(className).newInstance();
				objectMapping.put(className, toj);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}
	
	public BulletinDataModel getBulletin(String source) {
		try {
			Method method = toj.getClass().getMethod("getBulletin", String.class);
			return (BulletinDataModel) method.invoke(toj, source);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
