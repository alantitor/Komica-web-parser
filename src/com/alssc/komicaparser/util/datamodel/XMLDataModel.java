package com.alssc.komicaparser.util.datamodel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class XMLDataModel {

	// configuration meta data.
	private String metaTitle;
	private String metaTime;
	private String metaVersion;
	private String metaUsesKpt;
	// key data.
	private List<String> keys = new LinkedList<String>();
	// source data.
	private Map<String, String> titleMap = new HashMap<String, String>(); // use source.title as key
	private Map<String, String> siteUrlMap = new HashMap<String, String>();
	private Map<String, String> menuUrlMap = new HashMap<String, String>();		
	// mapping data.
	private Map<String, String> menuMap = new HashMap<String, String>(); // use category + name as key
	//private BiMap<String, String> menuMap = HashBiMap.create();
	private Map<String, String> bulletinMap = new HashMap<String, String>();
	//private BiMap<String, String> bulletinMap = HashBiMap.create();
	private Map<String, String> stickyMap = new HashMap<String, String>();
	//private BiMap<String, String> stickyMap = HashBiMap.create();
	
	private String currentKey = "";
	
	
	@Override
	public String toString() {
		String str = "";
	
		str += "metaTitle: " + metaTitle + "\n";
		str += "metaTime: " + metaTime + "\n";
		str += "metaVersion: " + metaVersion + "\n";
		str += "metaUsesKpt: " + metaUsesKpt + "\n";
		
		str += "keys: " + keys + "\n";
		
		str += "titleMap: " + titleMap + "\n";
		str += "siteUrlMap: " + siteUrlMap + "\n";
		str += "menuUrlMap: " + menuUrlMap + "\n";
		
		str += "menuMap: " + menuMap + "\n";
		str += "bulletinMap: " + bulletinMap + "\n";
		str += "stickyMap: " + stickyMap + "\n";
		
		return str;
	}
	
	/**
	 * save meta data.
	 * @return
	 */
	public String getMetaTitle() {
		return metaTitle;
	}
	
	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}
	
	public String getMetaTime() {
		return metaTime;
	}
	
	public void setMetaTime(String metaTime) {
		this.metaTime = metaTime;
	}
	
	public String getMetaVersion() {
		return metaVersion;
	}
	
	public void setMetaVersion(String metaVersion) {
		this.metaVersion = metaVersion;
	}
	
	public String getMetaUsesKpt() {
		return metaUsesKpt;
	}
	
	public void setMetaUsesKpt(String metaUsesKpt) {
		this.metaUsesKpt = metaUsesKpt;
	}
	
	/**
	 * save map keys.
	 * @param key
	 */
	public void putKeys(String key) {
		this.currentKey = key;
		this.keys.add(key);
	}
	
	public List<String> getKeys() {
		return this.keys;
	}
	
	/**
	 * save source title map.
	 * @param key
	 * @param value
	 */
	public void putTitleMap(String key, String value) {
		this.titleMap.put(key, value);
	}
	
	public String getTitleMap(String key) {
		return (String) this.titleMap.get(key);
	}

	public void putSiteUrleMap(String key, String value) {
		this.siteUrlMap.put(key, value);
	}
	
	public String getSiteUrlMap(String key) {
		return (String) this.siteUrlMap.get(key);
	}
	
	public void putMenuUrlMap(String key, String value) {
		this.menuUrlMap.put(key, value);
	}
	
	public String getMenuUrlMap(String key) {
		return (String) this.menuUrlMap.get(key);
	}
	
	public void putMenuMap(String key, String value) {
		this.menuMap.put(key, value);
	}
	
	public String getMenuMap(String key) {
		return (String) this.menuMap.get(key);
	}
	
	public void putBulletinMap(String key, String value) {
		this.bulletinMap.put(this.currentKey + "." + key, value);
	}
	
	public String getBulletinMap(String key) {
		return (String) this.bulletinMap.get(key);
	}
	
	public void putStickyMap(String key, String value) {
		this.stickyMap.put(this.currentKey + "." + key, value);
	}
	
	public String getStickyMap(String key) {
		return (String) this.stickyMap.get(key);
	}
	
	// extend features.
	
	public void updateMenuMapByValue(String oldValue, String newValue) {
		for (Map.Entry<String, String> e : menuMap.entrySet()) {
			String key = e.getKey();
			String value = e.getValue();
			
			if (value.equals(oldValue)) {
				//System.out.println("info: " + oldValue + ", " + newValue);
				menuMap.put(key, newValue);
			}
		}
	}
	
	public void updateBulletinMapByValue(String oldValue, String newValue) {
		for (Map.Entry<String, String> e : bulletinMap.entrySet()) {
			String key = e.getKey();
			String value = e.getValue();
			
			if (value.equals(oldValue)) {
				bulletinMap.put(key, newValue);
			}
		}
	}
	
	public void updateStickyMapByValue(String oldValue, String newValue) {
		for (Map.Entry<String, String> e : stickyMap.entrySet()) {
			String key = e.getKey();
			String value = e.getValue();
			
			if (value.equals(oldValue)) {
				stickyMap.put(key, newValue);
			}
		}
	}
}
