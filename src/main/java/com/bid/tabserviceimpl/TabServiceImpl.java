package com.bid.tabserviceimpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.bid.tabservices.TabService;
import com.invictusdynamics.bookie.entity.TransactionsDetails;
import com.invictusdynamics.bookie.global.SessionValue;
import com.invictusdynamics.bookie.sequence.TransactionSequence;
import com.invictusdynamics.bookie.service.SequenceDao;
import com.invictusdynamics.bookie.utility.Constants;

@Service("tabserviceimpl")
public class TabServiceImpl implements TabService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private SequenceDao sequenceDao;

	@Autowired
	private SessionValue sessionValue;

	@Override
	public Boolean saveTransactionData(Map<String, String> parameterMap) throws Exception {
		Boolean returnFlag = Boolean.FALSE;
		String playedBy = "", category = "", coinType = "", amountInString = "", userSelectedNumber = "", userIdInSession = "";
		Long amount = null;
		try {
			
			if (parameterMap != null) {
				if (sessionValue != null) {
					userIdInSession = sessionValue.getUserId();
				}

				playedBy = parameterMap.get("playedBy");
				category = parameterMap.get("category");
				coinType = parameterMap.get("coinType");
				userSelectedNumber = parameterMap.get("userSelectedNumber");
				amountInString = parameterMap.get("amount");
				if (amountInString!=null && amountInString != "") {
					amount = Long.parseLong(amountInString);
				}

				TransactionsDetails transactionsDetails = new TransactionsDetails();
				transactionsDetails.setId(sequenceDao.getNextSequenceId(Constants.TRANSACTION_SEQ_KEY, TransactionSequence.class));
				transactionsDetails.setDate(new Timestamp(new Date().getTime()));
				transactionsDetails.setCategory(category);
				transactionsDetails.setType(coinType);
				transactionsDetails.setType(coinType);
				transactionsDetails.setNumber(userSelectedNumber);
				transactionsDetails.setCoins(amount);
				transactionsDetails.setPlayedBy(playedBy);
				transactionsDetails.setUserId(userIdInSession);
				transactionsDetails.setCreatedBy(playedBy);
				transactionsDetails.setUpdatedBy(playedBy);
				transactionsDetails.setCreatedAt(new Timestamp(new Date().getTime()));
				transactionsDetails.setUpdatedAt(new Timestamp(new Date().getTime()));

				mongoTemplate.save(transactionsDetails);
				System.out.println("In TabServiceImpl.saveTransactionData() : Transaction details saved");
				returnFlag = Boolean.TRUE;
			}
		} catch (Exception exception) {
			returnFlag = Boolean.FALSE;
			System.out.println(exception);
			throw exception;
		}

		return returnFlag;
	}

}
