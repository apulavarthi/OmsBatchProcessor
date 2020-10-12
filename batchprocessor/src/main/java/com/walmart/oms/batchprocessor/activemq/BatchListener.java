package com.walmart.oms.batchprocessor.activemq;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.walmart.oms.batchprocessor.service.BatchProcessorService;

@Component
public class BatchListener
{
	
	@Autowired
	BatchProcessorService bs;
	
	int batchId;
	@JmsListener(destination = "test", concurrency = "5-10")
	public void receiveMessage(final Message jsonMessage) throws JMSException
	{
		String messageData = null;
		
		System.out.println("Received message " + jsonMessage);
		//String response = null;
		if(jsonMessage instanceof TextMessage)
		{
			TextMessage textMessage = (TextMessage)jsonMessage;
			messageData = textMessage.getText();
			batchId=Integer.parseInt(messageData);
			System.out.println("its a text message " + batchId);
			bs.getDcNbr(batchId);
			bs.addGroupId(batchId);
		}
	}
	

}
