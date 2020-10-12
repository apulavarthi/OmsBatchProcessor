package com.walmart.oms.batchprocessor.model;

import org.springframework.stereotype.Component;

@Component
public class PurchaseOrderDistribution {
	private int ponbr;
	private int item;
	private int store;
	private int qty;
	public int getPonbr() {
		return ponbr;
	}
	public void setPonbr(int ponbr) {
		this.ponbr = ponbr;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public int getStore() {
		return store;
	}
	public void setStore(int store) {
		this.store = store;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}

}
