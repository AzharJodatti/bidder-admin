package com.invictusdynamics.bookie.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.invictusdynamics.bookie.entity.LoginDetails;
import com.invictusdynamics.bookie.entity.Recharges;
import com.invictusdynamics.bookie.entity.Wallet;
import com.invictusdynamics.bookie.global.SessionValue;
import com.invictusdynamics.bookie.sequence.RechargeSequence;
import com.invictusdynamics.bookie.sequence.SequenceCounter;
import com.invictusdynamics.bookie.service.AgentService;
import com.invictusdynamics.bookie.service.SequenceDao;
import com.invictusdynamics.bookie.utility.Constants;
import com.invictusdynamics.bookie.utility.Utility;
import com.mongodb.DBCollection;

/**
 * Services related to Agent
 * 
 * @author amolku570123
 * @since 30 April 2017
 * 
 * 
 */
@Service("agentServiceImpl")
public class AgentServiceImpl implements AgentService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private SequenceDao sequenceDao;

	@Autowired
	private SessionValue sessionValue;

	final static Logger logger = Logger.getLogger(AgentServiceImpl.class);

	@Override
	public Boolean saveAgentDetails(String userId, String password, String name, String emailId, String userRole) throws Exception {
		Boolean returnValue = Boolean.FALSE;
		String userIdInSession = "";
		try {
			if (logger.isInfoEnabled())
				logger.info("In addUser() : UserId- " + userId + "Password: " + password);

			if (sessionValue != null) {
				userIdInSession = sessionValue.getUserId();
			}
			LoginDetails loginDetails = new LoginDetails();

			loginDetails.setId(sequenceDao.getNextSequenceId(Constants.LOGIN_DETAILS_SEQ_KEY, SequenceCounter.class));
			loginDetails.setUserLoginId(userId);
			loginDetails.setPassword(password);
			loginDetails.setUserRole(userRole);
			loginDetails.setName(name);
			loginDetails.setEmailId(emailId);
			loginDetails.setCommissionPercentage(0L);
			loginDetails.setParentId(userIdInSession);
			loginDetails.setCreatedBy(userIdInSession);
			loginDetails.setUpdatedBy(userIdInSession);
			loginDetails.setCreatedAt(new Timestamp(new Date().getTime()));
			loginDetails.setUpdatedAt(new Timestamp(new Date().getTime()));

			mongoTemplate.save(loginDetails);

			if (logger.isInfoEnabled())
				logger.info("In addUser() : dbObject save");
			returnValue = Boolean.TRUE;
		} catch (Exception exception) {
			returnValue = Boolean.FALSE;
			throw exception;
		}
		if (logger.isInfoEnabled())
			logger.info("Out addUser() : returnValue - " + returnValue);
		return returnValue;
	}

	@Override
	public List<LoginDetails> readLoginDetailsByRole(String userRole) throws Exception {
		List<LoginDetails> loginDetailsList = null;
		String startTime = null, endTime = null;
		try {
			if (logger.isDebugEnabled()) {
				startTime = Utility.startTime();
				logger.info("In AgentServiceImpl().readAgentDetails : Start Time : " + Utility.startTime());
				System.out.println("In AgentServiceImpl().readAgentDetails() : Start Time : " + Utility.startTime());
			}

			Query whereQuery = new Query();
			whereQuery.addCriteria(Criteria.where("userRole").in(userRole));

			DBCollection collection = mongoTemplate.getCollection(Constants.USER_SCHEMA);
			List<LoginDetails> cursor = mongoTemplate.find(whereQuery, LoginDetails.class);

			if (cursor != null) {	
				loginDetailsList = new ArrayList<LoginDetails>();
				for (LoginDetails dbObject : cursor) {
					loginDetailsList.add(dbObject);
				}
			}

			if (logger.isDebugEnabled()) {
				endTime = Utility.endTime();
				logger.info("In AgentServiceImpl().readAgentDetails : End Time : " + endTime);
				System.out.println("In AgentServiceImpl().readAgentDetails : End Time : " + endTime);
			}

			logger.info("In AgentServiceImpl().readAgentDetails : Total Time : " + Utility.calculateTime(startTime, endTime));
			System.out.println("In AgentServiceImpl().readAgentDetails : Total Time : " + Utility.calculateTime(startTime, endTime));

		} catch (Exception exception) {
			throw exception;
		}
		return loginDetailsList;
	}

	@Override
	public Boolean saveRechargeDetails(String userId, String rechargeBy, Long coinAmount) throws Exception {
		Boolean returnValue = Boolean.FALSE;
		String userIdInSession = "";
		try {
			if (sessionValue != null) {
				userIdInSession = sessionValue.getUserId();
			}
			if(rechargeBy == "")
				rechargeBy = userIdInSession;
			Wallet wallet = new Wallet();
			wallet.setId(sequenceDao.getNextSequenceId(Constants.RECHARGE_SEQ_KEY, RechargeSequence.class));
			wallet.setUserId(userId);
			wallet.setCoins(coinAmount);
			wallet.setCreatedBy(rechargeBy);
			wallet.setCreatedAt(new Timestamp(new Date().getTime()));
			wallet.setUpdatedAt(new Timestamp(new Date().getTime()));
			wallet.setUpdatedBy(rechargeBy);

			mongoTemplate.save(wallet);
			returnValue = Boolean.TRUE;
		} catch (Exception exception) {
			returnValue = Boolean.TRUE;
			System.out.println(exception);
		}
		return returnValue;
	}
}
