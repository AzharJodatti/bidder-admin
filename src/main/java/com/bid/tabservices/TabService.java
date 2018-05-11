package com.bid.tabservices;

import java.util.Map;

import com.invictusdynamics.bookie.entity.TransactionsDetails;

/**
 * Service related to Tab
 * 
 * @author amolku570123
 * @since 10 May 2018
 */
public interface TabService {

	/**
	 * Saves transaction data played by user on Tab
	 * 
	 * @param playedBy
	 * @param category
	 * @param coinType
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public Boolean saveTransactionData(Map<String, String> parameterMap) throws Exception;
}
