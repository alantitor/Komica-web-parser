package com.alssc.komicaparser.util.datamodel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StickyDataModel extends BasicDataModel {

	private String title = "";
	private String meta = "";
	private String avatarUrl = "";
	private String introduction = "";
	
	// key: number.
	private List<String> cellKeys = new LinkedList<String>(); // key.
	private Map<String, String> cellTitleMap = new HashMap<String, String>();
	private Map<String, String> cellAvatarUrlMap = new HashMap<String, String>(); // image url.
	private Map<String, String> cellMetaMap = new HashMap<String, String>(); // meta data.
	private Map<String, String> cellNoMap = new HashMap<String, String>(); // no.
	private Map<String, String> cellContextMap = new HashMap<String, String>(); // context.
	
	
	public void putHeadData(String title, String meta, String avatarUrl, String introduction) {
		this.title = title;
		this.meta = meta;
		this.avatarUrl = avatarUrl;
		this.introduction = introduction;
	}
	
	public void putCellData(String key, String title, String avatarUrl, String meta, String no, String context) {
		this.cellKeys.add(key);
		this.cellTitleMap.put(key, title);
		this.cellAvatarUrlMap.put(key, avatarUrl);
		this.cellMetaMap.put(key, meta);
		this.cellNoMap.put(key, no);
		this.cellContextMap.put(key, context);
	}
	
	// get data.
	
	public String toString() {
		String text = "";
		text += "content:\n";
		
		text += "title: " + this.title + "\n";
		text += "meta: " + this.meta + "\n";
		text += "avatar url: " + this.avatarUrl + "\n";
		text += "introduction: " + this.introduction + "\n";
		
		for (int i = 0; i < cellKeys.size(); i++) {
			text += "-----\n";
			text += "key: " + i + "\n";
			text += "title: " + cellTitleMap.get(cellKeys.get(i)) + "\n";
			text += "avatar url: " + cellAvatarUrlMap.get(cellKeys.get(i)) + "\n";
			text += "meta: " + cellMetaMap.get(cellKeys.get(i)) + "\n";
			text += "no: " + cellNoMap.get(cellKeys.get(i)) + "\n";
			text += "context:\n" + cellContextMap.get(cellKeys.get(i)) + "\n";
		}
		
		return text;
	}
	
	@Override
	public boolean checkDataState() {
		if (cellKeys.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
}
