package com.casestudy.tg;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/***
 * This class models responses produced by the
 * endpoint getProductInfo in ProductInfoController
 * @author AJ Sanchez (a.sanchez.824@gmail.com)
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInfo {
	
	private long pid;
	private String name;
	private CurrentPrice currentPrice;
	
	public ProductInfo(long pid, String name, CurrentPrice currentPrice) {
		super();
		this.pid = pid;
		this.name = name;
		this.currentPrice = currentPrice;
	}
	
	public ProductInfo() {
	}

	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CurrentPrice getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(CurrentPrice currentPrice) {
		this.currentPrice = currentPrice;
	}	
}
