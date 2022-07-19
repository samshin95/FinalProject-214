package com.shopping.json;

import java.io.Serializable;





public class JsonResult3 implements Serializable {

	private int[] array1;
	private int[] array2;
	
	
	
	public void JsonResult() {
		
	}
	public void JsonResult(int[] array12 ,int[] array22){ 
          //super();
		this.array1 = new int[array12.length];
		this.array2 = new int[array22.length];
		for(int a=0;a<array12.length;a++) {
			array1[a]=array12[a];
		}
		for(int a=0;a<array22.length;a++) {
			array2[a]=array22[a];
		}
	}

	public int[] getArray1() {
		return array1;
	}
	public void setArray1(int[] array) {
		this.array1 = array;
	}
	public int[] getArray2() {
		return array2;
	}
	public void setArray2(int[] array) {
		this.array2 = array;
	}
	
}
