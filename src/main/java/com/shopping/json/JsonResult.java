package com.shopping.json;

import java.io.Serializable;

public class JsonResult implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private int n;

private String s;

private String[] sarr;

public String[] getSarr() {
	return sarr;
}
public void setSarr(String[] sarr) {
	this.sarr = sarr;
}
public JsonResult() {
	
}
public int getN() {
	return n;
}
public void setN(int n) {
	this.n = n;
}
public String getS() {
	return s;
}
public void setS(String s) {
	this.s = s;
}
public JsonResult(int n, String s) {
	super();
	this.n = n;
	this.s = s;
}

public JsonResult(int n, String s,String[] sarr) {
	super();
	this.n = n;
	this.s = s;
	this.sarr=sarr;
}
public JsonResult(String s) {
	super();
	n = 0;
	this.s = s;
	sarr=null;
	
}
public JsonResult(int n) {
	super();
	this.n = 0;
	s = "";
	sarr=null;
}
}
