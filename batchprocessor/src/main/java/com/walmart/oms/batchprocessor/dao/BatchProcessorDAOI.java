package com.walmart.oms.batchprocessor.dao;

import java.sql.Date;
import java.util.List;

import com.walmart.oms.batchprocessor.model.OrderRequest;

public interface BatchProcessorDAOI {

	List<OrderRequest> getOrderRequest(int batchId);

	public void updateDcnbr(int dcnbr, int item, int store,int batchid);

	public List<OrderRequest> getRows(int batchId);

	public int addGrpId(int dcnbr, Date mabd);

	public void addPoNbr(int groupId,int batchId);

}
