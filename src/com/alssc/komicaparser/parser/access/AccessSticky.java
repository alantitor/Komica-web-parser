package com.alssc.komicaparser.parser.access;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.alssc.komicaparser.util.datamodel.StickyDataModel;


public class AccessSticky {

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
	
	public StickyDataModel getSticky(String source) {
		try {
			Method method = toj.getClass().getMethod("getSticky", String.class);
			return (StickyDataModel) method.invoke(toj, source);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}		
}
