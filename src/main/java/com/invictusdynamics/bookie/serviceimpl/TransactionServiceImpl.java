package com.invictusdynamics.bookie.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.invictusdynamics.bookie.entity.TransactionsDetails;
import com.invictusdynamics.bookie.service.TransactionService;

@Service("transactionserviceimpl")
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<TransactionsDetails> getTransactionDetails() throws Exception {
		List<TransactionsDetails> transactionDetailList = null;
		try {
			transactionDetailList = mongoTemplate.findAll(TransactionsDetails.class);
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return transactionDetailList;
	}

}
