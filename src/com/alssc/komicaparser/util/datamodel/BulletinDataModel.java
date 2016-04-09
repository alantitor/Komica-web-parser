package com.alssc.komicaparser.util.datamodel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class BulletinDataModel extends BasicDataModel {

	// key: number.
	private List<String> cellKeys = new LinkedList<String>(); // key.
	private Map<String, String> cellTitleMap = new HashMap<String, String>();// title.
	private Map<String, String> cellAvatarUrlMap = new HashMap<String, String>(); // image.
	private Map<String, String> cellMetaMap = new HashMap<String, String>(); // meta data.
	private Map<String, String> cellStickyUrlMap = new HashMap<String, String>(); // link to sticky.
	private Map<String, String> cellIntroductionMap = new HashMap<String, String>(); // introduction.
	
	private List<String> pageList = new LinkedList<String>(); // save page url
	private int currentPage = -1;	// current page index
	

	public void putData(String key, String title, String avatarUrl, String meta, String stickyUrl, String introduction) {
		cellKeys.add(key);
		cellTitleMap.put(key, title);
		cellAvatarUrlMap.put(key, avatarUrl);
		cellMetaMap.put(key, meta);
		cellStickyUrlMap.put(key, stickyUrl);
		cellIntroductionMap.put(key, introduction);
	}
	
	public void putCurrPage(int index) {
		this.currentPage = index;
	}
	
	public void putPage(List<String> pageList) {
		this.pageList = pageList;
	}
	
	// get data
	
	public String toString() {
		String text = "";
		
		text += "content:\n";
		for (int i = 0; i < cellKeys.size(); i++) {
			text += "-----\n";
			text += "key: " + i + "\n";
			text += "title: " + cellTitleMap.get(cellKeys.get(i)) + "\n";
			text += "avatar url: " + cellAvatarUrlMap.get(cellKeys.get(i)) + "\n";
			text += "meta: " + cellMetaMap.get(cellKeys.get(i)) + "\n";
			text += "sticky url: " + cellStickyUrlMap.get(cellKeys.get(i)) + "\n";
			text += "introduction:\n" + cellIntroductionMap.get(cellKeys.get(i)) + "\n";
			text += "\n";
		}
		
		text += "currPage: " + currentPage + "\n";
		text += "pageLit:\n";
		for (int i = 0; i < pageList.size(); i++) {
			text += "page " + i + ": " + pageList.get(i) + "\n";
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
