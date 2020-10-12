package com.walmart.oms.batchprocessor.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class OrderRequest {
	
	private int item;
	private int qty;
	private int store;
	private Date mabd;
	private int vendornbr;
	private int dcnbr;
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getStore() {
		return store;
	}
	public void setStore(int store) {
		this.store = store;
	}
	
	public Date getMabd() {
		return mabd;
	}
	public void setMabd(Date mabd) {
		this.mabd = mabd;
	}
	public int getVendornbr() {
		return vendornbr;
	}
	public void setVendornbr(int vendornbr) {
		this.vendornbr = vendornbr;
	}
	public int getDcnbr() {
		return dcnbr;
	}
	public void setDcnbr(int dcnbr) {
		this.dcnbr = dcnbr;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public int getPonbr() {
		return ponbr;
	}
	public void setPonbr(int ponbr) {
		this.ponbr = ponbr;
	}
	public long getBatchid() {
		return batchid;
	}
	public void setBatchid(long batchid) {
		this.batchid = batchid;
	}
	private int groupid;
	private int ponbr;
	private long batchid;
	

}
