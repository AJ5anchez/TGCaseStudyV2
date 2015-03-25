package com.casestudy.tg;


class CurrentPrice {
	
	private float value;
	private String currencyCode;
	
	public CurrentPrice(float value, String currencyCode) {
		super();
		this.value = value;
		this.currencyCode = currencyCode;
	}
	public CurrentPrice(){
		
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
	@Override
	public String toString(){
		return "{ " + 
	           "value: " + 
				String.valueOf(value) + 
				", currency_code: " 
				+ currencyCode +
				" }";
	}

}
