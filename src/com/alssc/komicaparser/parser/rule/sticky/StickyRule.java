package com.alssc.komicaparser.parser.rule.sticky;

import com.alssc.komicaparser.util.datamodel.StickyDataModel;

public abstract class StickyRule {

	
	abstract public StickyDataModel getSticky(String source);
	
	public void test() {
		System.out.println("In StickyRule class.");
	}
}
