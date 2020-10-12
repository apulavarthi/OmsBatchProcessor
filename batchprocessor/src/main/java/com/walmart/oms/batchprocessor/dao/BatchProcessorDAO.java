package com.walmart.oms.batchprocessor.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.walmart.oms.batchprocessor.model.OrderRequest;
import com.walmart.oms.batchprocessor.model.PurchaseOrder;
import com.walmart.oms.batchprocessor.model.PurchaseOrderDistribution;
import com.walmart.oms.batchprocessor.model.PurchaseOrderItem;

@Repository
public class BatchProcessorDAO implements  BatchProcessorDAOI{
	
	@Autowired
	JdbcTemplate jtemp;
	
	@Override
	public List<OrderRequest> getOrderRequest(int batchId)
	{
	
		
		String sql="select * from order_request where batchid=?";
		List<Map<String, Object>> results = jtemp.queryForList(sql,batchId);
		List<OrderRequest> list1=new ArrayList<OrderRequest>();
		for (int i=0; i<results.size(); i++) 
		{
			Map<String, Object> row = results.get(i);
			OrderRequest orderRequest = new OrderRequest();
			
			orderRequest.setItem((Integer)row.get("item"));
			orderRequest.setQty((Integer)row.get("qty"));
			orderRequest.setStore((Integer)row.get("store"));
			orderRequest.setMabd((Date)row.get("mabd"));
			list1.add(orderRequest);
			
		}
		return list1;
		
		
	}

	@Override
	public void updateDcnbr(int dcnbr,int item,int store,int batchid)
	{
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setItem(item);
		orderRequest.setStore(store);
		orderRequest.setBatchid(batchid);
		String sql="update order_request set dcnbr=? where item=? and store=? and batchid=?";
		jtemp.update(sql,dcnbr,orderRequest.getItem(),orderRequest.getStore(),orderRequest.getBatchid());
	}

	@Override
	public List<OrderRequest> getRows(int batchId) 
	{
		String sql="select distinct dcnbr,mabd from oms.order_request where batchid=?";
		List<Map<String, Object>> results=jtemp.queryForList(sql,batchId);
		List<OrderRequest> list1=new ArrayList<OrderRequest>();
		for (int i=0; i<results.size(); i++) 
		{
			Map<String, Object> row = results.get(i);
			OrderRequest orderRequest = new OrderRequest();
			orderRequest.setDcnbr((Integer)row.get("dcnbr"));
			orderRequest.setMabd((Date)row.get("mabd"));
			list1.add(orderRequest);			
		}
		return list1;
	}

	@Override
	public int addGrpId(int dcnbr, Date mabd)
	{
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setDcnbr(dcnbr);
		orderRequest.setMabd(mabd);
		Random r = new Random();
		int groupId=r.nextInt(50);
		orderRequest.setGroupid(groupId);
		String sql="update order_request set groupid=? where dcnbr=? and mabd=?";
		jtemp.update(sql,orderRequest.getGroupid(),orderRequest.getDcnbr(),orderRequest.getMabd());	
		return groupId;
	}

	@Override
	public void addPoNbr(int groupId,int batchId)
	{
		String sql="select * from order_request where groupid=? and batchid=?";
		List<Map<String, Object>> results = jtemp.queryForList(sql,groupId,batchId);
		Random r = new Random();
		int ponbr=r.nextInt(50);
		Set<PurchaseOrder> set1=new HashSet<PurchaseOrder>();
		Set<PurchaseOrderItem> set2=new HashSet<PurchaseOrderItem>();
		Set<PurchaseOrderDistribution> set3=new HashSet<PurchaseOrderDistribution>();
		for (int i=0; i<results.size(); i++) 
		{
			OrderRequest orderRequest=new OrderRequest();
			Map<String, Object> row = results.get(i);
			orderRequest.setStore((Integer)row.get("store"));
			orderRequest.setItem((Integer)row.get("dcnbr"));
			orderRequest.setDcnbr((Integer)row.get("dcnbr"));
			orderRequest.setMabd((Date)row.get("mabd"));
			orderRequest.setQty((Integer)row.get("qty"));
			PurchaseOrder purchaseOrder= new PurchaseOrder();
			purchaseOrder.setPonbr(ponbr);
			purchaseOrder.setDcnbr(orderRequest.getDcnbr());
			purchaseOrder.setMabd(orderRequest.getMabd());
			set1.add(purchaseOrder);
			PurchaseOrderItem purchaseOrderItem= new PurchaseOrderItem();
			purchaseOrderItem.setItem(orderRequest.getItem());
			purchaseOrderItem.setPonbr(ponbr);
			set2.add(purchaseOrderItem);
			PurchaseOrderDistribution purchaseOrderDistribution= new PurchaseOrderDistribution();
			purchaseOrderDistribution.setItem(orderRequest.getItem());
			purchaseOrderDistribution.setPonbr(ponbr);
			purchaseOrderDistribution.setStore(orderRequest.getStore());
			purchaseOrderDistribution.setQty(orderRequest.getQty());
			set3.add(purchaseOrderDistribution);				
		}
			Iterator<PurchaseOrder> iterator=set1.iterator();
			while(iterator.hasNext())
			{
				PurchaseOrder purchaseOrder=new PurchaseOrder();
				purchaseOrder=iterator.next();
				String sql1="insert into purchase_order(ponbr,dcnbr,mabd) values(?,?,?)";
				jtemp.update(sql1,purchaseOrder.getPonbr(),purchaseOrder.getDcnbr(),purchaseOrder.getMabd());
			}
			Iterator<PurchaseOrderItem> iterator1=set2.iterator();
			while(iterator1.hasNext())
			{
				PurchaseOrderItem purchaseOrderItem=new PurchaseOrderItem();
				purchaseOrderItem=iterator1.next();
				String sql1="insert into purchase_order_item(ponbr,item) values(?,?)";
				jtemp.update(sql1,purchaseOrderItem.getPonbr(),purchaseOrderItem.getItem());
			}
			Iterator<PurchaseOrderDistribution> iterator2=set3.iterator();
			while(iterator2.hasNext())
			{
				PurchaseOrderDistribution purchaseOrderDistribution=new PurchaseOrderDistribution();
				purchaseOrderDistribution=iterator2.next();
				String sql1="insert into purchase_order_distribution(ponbr,item,store,qty) values(?,?,?,?)";
				jtemp.update(sql1,purchaseOrderDistribution.getPonbr(),purchaseOrderDistribution.getItem(),purchaseOrderDistribution.getStore(),purchaseOrderDistribution.getQty());
			}
			
	}	

}
