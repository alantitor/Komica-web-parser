package com.alssc.komicaparser.util.datamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.alssc.komicaparser.util.Pair;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class MenuDataModel extends BasicDataModel {

	private Multimap<String, Pair<String, String>> data = ArrayListMultimap.create();
	
	
	public void put(String key, String title, String url) {
		Pair<String, String> pair = new Pair<String, String>(title, url);
		data.put(key, pair);
	}
	
	public Set<String> getCategory() {
		return data.keySet();
	}
	
	public List<Pair<String, String>> getDataByCategory(String key) {
		return (List<Pair<String, String>>) data.get(key);
	}
	
	public Pair<String, String> getDataByName(String key) {
		String[] t = key.split("\\.", 3); // category name
		Collection<Pair<String, String>> c = data.get(t[1]);
		
		for (Pair<String, String> pair : c) {
			if (pair.leftValue.equals(t[2])) {
				return pair;
			}
		}
		
		return null;
	}
	
	public String toString() {
		String text = "";
		
		for (String key : data.keySet()) {
			for (Pair<String, String> pair  : data.get(key)) {	
				text += key + ": " + "(" + pair.getLeftValue() + ", " + pair.getRightValue() + ")\n";
			}
		}
		
		return text;
	}

	@Override
	public boolean checkDataState() {
		if (data == null || data.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
}
