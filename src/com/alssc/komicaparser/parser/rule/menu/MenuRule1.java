package com.alssc.komicaparser.parser.rule.menu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alssc.komicaparser.util.datamodel.MenuDataModel;
import com.alssc.komicaparser.util.net.GetSite;


public class MenuRule1 extends MenuRule {
	
	
	public MenuDataModel getMenu(String source) {
		try {
			return parse(source);
		} catch (Exception e) {
			System.out.println("parse fail.");
			return null;
		}
	}
	
	private MenuDataModel parse(String source) {
		GetSite getSite = new GetSite();
		MenuDataModel data = new MenuDataModel();
		Document doc = Jsoup.parse(getSite.downloadSource(source));
		Element et = doc.select("body > font").first();
		Elements eml = et.children();
	
		String tempCategory = "";
		String tempMenu = "";
		String tempMenuLink = "";
				
		for (Element eme : eml) {
			switch (eme.tagName()) {
				case "b":
					tempCategory = eme.text();
					break;
				case "a":
					tempMenu = eme.text();
					tempMenuLink = eme.attr("href");
					data.put(tempCategory, tempMenu, tempMenuLink);
					break;
				default:
					//
			}
		}
		
		return data;		
	}
}

