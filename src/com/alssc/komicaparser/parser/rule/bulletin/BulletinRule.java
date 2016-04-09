package com.alssc.komicaparser.parser.rule.bulletin;

import com.alssc.komicaparser.util.datamodel.BulletinDataModel;

public abstract class BulletinRule {
	
	
	abstract public BulletinDataModel getBulletin(String source);
	
	public void test() {
		System.out.println("In BulletinRule class.");
	}
}
