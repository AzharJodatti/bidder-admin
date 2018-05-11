package com.invictusdynamics.bookie.service;

import java.util.List;

import com.invictusdynamics.bookie.entity.TransactionsDetails;

/**
 * @author amol k Golhar
 *
 */
public interface TransactionService {
	public List<TransactionsDetails> getTransactionDetails() throws Exception;
}
