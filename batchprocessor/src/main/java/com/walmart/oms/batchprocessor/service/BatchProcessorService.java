package com.walmart.oms.batchprocessor.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.walmart.oms.batchprocessor.dao.BatchProcessorDAOI;
import com.walmart.oms.batchprocessor.model.OrderRequest;


@Component
public class BatchProcessorService
{

	@Autowired
	RestTemplate rtemp;
	
	@Autowired
	BatchProcessorDAOI bp;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}


	public void getDcNbr(int batchId)
	{
		List<OrderRequest> list2=bp.getOrderRequest(batchId);
		for (int i=0;i<list2.size();i++) 
		{
			OrderRequest orderrequest=list2.get(i);
			final String uri = "http://localhost:8081/getdcnbr/"+orderrequest.getStore()+"/"+orderrequest.getItem();
			int dcnbr=rtemp.getForObject(uri,Integer.class);
			orderrequest.setDcnbr(dcnbr);
			int item=orderrequest.getItem();
			int store=orderrequest.getStore();
			bp.updateDcnbr(dcnbr,item,store,batchId);
		}	
	}
	public void addGroupId(int batchId)
	{
		List<OrderRequest> list2=bp.getRows(batchId);
		for (int i=0;i<list2.size();i++) 
		{
			OrderRequest orderRequest=list2.get(i);
			int dcnbr=orderRequest.getDcnbr();
			Date mabd=orderRequest.getMabd();
			int groupId=bp.addGrpId(dcnbr,mabd);
			bp.addPoNbr(groupId,batchId);
		}
		
	}
	
	
}

