package com.walmart.oms.batchprocessor.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class PurchaseOrder {
	private int ponbr;
	private int vendornbr;
	private int dcnbr;
	private Date mabd;
	public int getPonbr() {
		return ponbr;
	}
	public void setPonbr(int ponbr) {
		this.ponbr = ponbr;
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
	public Date getMabd() {
		return mabd;
	}
	public void setMabd(Date mabd) {
		this.mabd = mabd;
	}
	

}
