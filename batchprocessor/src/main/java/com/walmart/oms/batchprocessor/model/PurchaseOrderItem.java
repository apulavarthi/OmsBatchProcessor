package com.walmart.oms.batchprocessor.model;

import org.springframework.stereotype.Component;

@Component
public class PurchaseOrderItem {
	private int ponbr;
	private int item;
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

}
