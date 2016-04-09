package com.alssc.komicaparser.parser.rule.menu;

import com.alssc.komicaparser.util.datamodel.MenuDataModel;

public abstract class MenuRule {

	
	public abstract MenuDataModel getMenu(String source);
	
	public void test() {
		System.out.println("In MenuRule class.");
	}
}
