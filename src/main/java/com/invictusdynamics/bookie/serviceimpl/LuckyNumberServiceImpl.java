package com.invictusdynamics.bookie.serviceimpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.bid.dto.TransactionsDTO;
import com.invictusdynamics.bookie.api.ApiServices;
import com.invictusdynamics.bookie.entity.LoginDetails;
import com.invictusdynamics.bookie.entity.LuckyNumberDetails;
import com.invictusdynamics.bookie.entity.TimeDetails;
import com.invictusdynamics.bookie.entity.TransactionsDetails;
import com.invictusdynamics.bookie.global.SessionValue;
import com.invictusdynamics.bookie.sequence.LuckyNumberSequence;
import com.invictusdynamics.bookie.sequence.TimeSequence;
import com.invictusdynamics.bookie.service.LuckyNumberService;
import com.invictusdynamics.bookie.service.SequenceDao;
import com.invictusdynamics.bookie.service.TransactionService;
import com.invictusdynamics.bookie.utility.Constants;

@Service("luckyNumberServiceImpl")
public class LuckyNumberServiceImpl implements LuckyNumberService {

	/** Resource bundle for exception message */
	ResourceBundle messageBundle = ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE_NAME);

	@Autowired
	private SequenceDao sequenceDao;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private SessionValue sessionValue;

	@Autowired
	@Qualifier("transactionserviceimpl")
	private TransactionService transactionService;
	
	@Autowired
	@Qualifier("luckyNumberServiceImpl")
	private LuckyNumberService luckyNumberService;
	
	@Autowired
	@Qualifier("apiServiceImpl")
	private ApiServices apiService;
	
	
	
	@Override
	public String saveLuckyNumber(String openValue, String closeValue, String region) {
		String returnMessage = "", jodiNumber = "", userNameInSession = "";
		Long openValueLong = null;
		Long closeValueLong = null;
		if (sessionValue != null) {
			userNameInSession = sessionValue.getUserId();
		}
		if (!region.isEmpty()) {
			LuckyNumberDetails luckyNumberDetails = new LuckyNumberDetails();
			luckyNumberDetails.setId(sequenceDao.getNextSequenceId(Constants.LUCKY_NUMBER_SEQ_KEY, LuckyNumberSequence.class));
			if (openValue != null && openValue != "")
				openValueLong = Long.parseLong(openValue);
			luckyNumberDetails.setOpen(openValueLong);
			if (closeValue != null && closeValue != "")
				closeValueLong = Long.parseLong(closeValue);
			luckyNumberDetails.setClose(closeValueLong);
			luckyNumberDetails.setRegion(region);
			if (openValue != null && closeValue != null) {
				jodiNumber = String.valueOf(openValue) + String.valueOf(closeValue);
			}
			luckyNumberDetails.setJodi(jodiNumber);
			luckyNumberDetails.setPan(0L);
			luckyNumberDetails.setCreatedBy(userNameInSession);
			luckyNumberDetails.setCreatedDate(new Timestamp(new Date().getTime()));
			luckyNumberDetails.setUpdatedBy(userNameInSession);
			luckyNumberDetails.setUpdatedDate(new Timestamp(new Date().getTime()));
			mongoTemplate.save(luckyNumberDetails);
			returnMessage = messageBundle.getString("luckyNumberSaveSuccessMsg");

		} else {

		}
		return returnMessage;
	}

	@Override
	public String saveTime(String openTime, String closeTime, String region) {
		String returnMessage = "", userIdInSession = "";
		returnMessage = messageBundle.getString("saveFailure");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (sessionValue != null) {
				userIdInSession = sessionValue.getUserId();
			}
			TimeDetails timeDetails = new TimeDetails();
			timeDetails.setId(sequenceDao.getNextSequenceId(Constants.TIME_SEQ_KEY, TimeSequence.class));
			timeDetails.setRegion(region);
			timeDetails.setOpenTime(openTime);
			timeDetails.setCloseTime(closeTime);
			timeDetails.setStatus(Constants.ACTIVE);
			timeDetails.setCreatedBy(userIdInSession);
			timeDetails.setUpdatedBy(userIdInSession);
			timeDetails.setCreatedDate(simpleDateFormat.format(new Date()));
			timeDetails.setUpdatedDate(new Date());
			mongoTemplate.save(timeDetails);
			returnMessage = messageBundle.getString("saveSuccess");
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return returnMessage;
	}

	/**
	 * Check the Time details in Database against user who is in session and current date. (non-Javadoc)
	 * 
	 * @see LuckyNumberService#checkTimeDetails(String)
	 */
	@Override
	public TimeDetails getTimeDetails(String userId) {
		TimeDetails timeDetails = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Query searchUserQuery = new Query();
		if (userId != "")
			searchUserQuery.addCriteria(Criteria.where("createdBy").is(userId));
		searchUserQuery.addCriteria(Criteria.where("createdDate").is(simpleDateFormat.format(new Date())));
		timeDetails = mongoTemplate.findOne(searchUserQuery, TimeDetails.class);
		return timeDetails;
	}

	@Override
	public List<LuckyNumberDetails> getLuckyNumberDetails() {
		List<LuckyNumberDetails> luckyNumberDetailList = null;
		try {
			luckyNumberDetailList = mongoTemplate.findAll(LuckyNumberDetails.class);
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return luckyNumberDetailList;
	}

	@Override
	public LuckyNumberDetails getTodaysLuckyNumber() {
		LuckyNumberDetails luckyNumberDetails = null;
		List<LuckyNumberDetails> luckyNumberDetailsList = null;
		DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Query searchUserQuery = new Query(Criteria.where("createdDate").is(new Date()));
			luckyNumberDetails = mongoTemplate.findOne(searchUserQuery, LuckyNumberDetails.class);
			luckyNumberDetailsList = getLuckyNumberDetails();
			if (luckyNumberDetailsList != null) {
				for (LuckyNumberDetails luckyNumberObj : luckyNumberDetailsList) {
					Date dateFromDb = luckyNumberObj.getCreatedDate();
					String dbFormattedDate = simpleDateFormat.format(dateFromDb);
					Date currDate = new Date();
					String currentFormaatedDate = simpleDateFormat.format(currDate);
					if (dbFormattedDate.equals(currentFormaatedDate))
						luckyNumberDetails = luckyNumberObj;
				}
			}
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return luckyNumberDetails;
	}

	@Override
	public TransactionsDTO getCalculatedTransactionDetails() {
		TransactionsDTO transactionsDTO = null;
		List<TransactionsDetails> transactionsDetailsList = null;
		Map<String, Long> calculatedAmountMap = null;
		LuckyNumberDetails luckyNumberDetails = null;
		Long todaysOpenLuckyNumber = 0L;
		BigDecimal totalCalculatedAmountBd;
		Long totalCalculatedAmountLg = 0L;
		Long totalOpenCount = 0L;
		Long userPercentage = 0L;
		try {
			transactionsDetailsList = transactionService.getTransactionDetails();
			luckyNumberDetails = luckyNumberService.getTodaysLuckyNumber();
			
			if(luckyNumberDetails!=null)
				todaysOpenLuckyNumber  = luckyNumberDetails.getOpen();
			
			for (TransactionsDetails transDetailsObj : transactionsDetailsList) {
				if (transDetailsObj.getType().equalsIgnoreCase(Constants.OPEN)) {
					totalOpenCount++;
				}
			}
			System.out.println("Total Open Count" + totalOpenCount);
			
			calculatedAmountMap = new HashMap<String, Long>();
			transactionsDTO = new TransactionsDTO();

			for (TransactionsDetails transactionsDetailsObj : transactionsDetailsList) {
				if (transactionsDetailsObj!=null && transactionsDetailsObj.getType().equalsIgnoreCase(Constants.OPEN)) {
					Long userSelectedNumber = Long.parseLong(transactionsDetailsObj.getNumber());
					if (luckyNumberDetails != null && todaysOpenLuckyNumber.equals(userSelectedNumber)) {
						if (!totalOpenCount.equals(0L)) {
							LoginDetails userData = apiService.getUserDetailsById(transactionsDetailsObj.getPlayedBy());
							if (userData != null) {
								userPercentage = userData.getCommissionPercentage();
								System.out.println("userPercentage" + userPercentage);
							}
							
							BigDecimal userPercentageBd = BigDecimal.valueOf(userPercentage);
							BigDecimal userSelectedAmount = BigDecimal.valueOf(transactionsDetailsObj.getCoins());
							BigDecimal deviserHundred = new BigDecimal(100L);
							
							totalCalculatedAmountBd = (userPercentageBd.divide(deviserHundred).multiply(userSelectedAmount));
							//totalCalculatedAmount = (((userPercentage / 100) * totalOpenCount) * transactionsDetailsObj.getCoins());
							System.out.println("BigDecimal val : "+ totalCalculatedAmountBd);
							totalCalculatedAmountLg = totalCalculatedAmountBd.longValue();
							System.out.println("Long val : " + totalCalculatedAmountBd);
							calculatedAmountMap.put(transactionsDetailsObj.getPlayedBy(), totalCalculatedAmountLg);
						}
					}
				}
			}
			transactionsDTO.setCalculatedAmountMap(calculatedAmountMap);

		} catch (Exception exception) {
			System.out.println(exception);
		}
		return transactionsDTO;
	}

}
