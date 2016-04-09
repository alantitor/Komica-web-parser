package com.alssc.komicaparser.parser.access;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.alssc.komicaparser.util.datamodel.MenuDataModel;


public class AccessMenu {

	// system setting.
	private Object toj;
	private Map<String, Object> objectMap = new HashMap<String, Object>();
	
	
	public boolean init(String className) {
		if (objectMap.containsKey(className)) {
			this.toj = objectMap.get(className);
			return true;
		} else {
			try {
				toj = Class.forName(className).newInstance();
				objectMap.put(className, toj);
				return true;
			} catch (Exception e) {
				e.printStackTrace();		
				return false;
			}
		}
	}
	
	public MenuDataModel getMenu(String source) {		
		try {
			Method method = toj.getClass().getMethod("getMenu", String.class);
			return (MenuDataModel) method.invoke(toj, source);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
