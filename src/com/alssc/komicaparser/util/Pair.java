package com.alssc.komicaparser.util;

public class Pair<K, V> {

	public K leftValue;
	public V rightValue;
	

	public Pair(K leftValue, V rightValue) {
		this.leftValue = leftValue;
		this.rightValue = rightValue;
	}
	
	public void put(K leftValue, V rightValue) {
		this.leftValue = leftValue;
		this.rightValue = rightValue;
	}
	
	public K getLeftValue() {
		return this.leftValue;
	}
	
	public V getRightValue() {
		return this.rightValue;
	}
}
