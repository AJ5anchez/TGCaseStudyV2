package com.casestudy.tg;

public class ExpectedProductInfo {
	private String name;
	private float value;
	private String currencyCode;
	public ExpectedProductInfo(String name, float value, String currencyCode) {
		super();
		this.name = name;
		this.value = value;
		this.currencyCode = currencyCode;
	}
	public ExpectedProductInfo() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
}
