package com.invictusdynamics.bookie.serviceimpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.invictusdynamics.bookie.entity.LoginDetails;
import com.invictusdynamics.bookie.global.SessionValue;
import com.invictusdynamics.bookie.sequence.SequenceCounter;
import com.invictusdynamics.bookie.service.LoginService;
import com.invictusdynamics.bookie.service.SequenceDao;
import com.invictusdynamics.bookie.utility.Constants;
import com.invictusdynamics.bookie.utility.Utility;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * Service for user level functionality
 * 
 * @author Amol K Golhar
 * @since 12 April 2018
 *
 */
@Service("loginServiceImpl")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private SequenceDao sequenceDao;
	
	@Autowired
	private SessionValue sessionValue;
	
	private static final String LOGIN_DETAILS_SEQ_KEY = "loginSequence";

	final static Logger logger = Logger.getLogger(LoginService.class);

	/** Resource bundle for exception message */
	ResourceBundle messageBundle = ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE_NAME);

	@Override
	public Boolean validateLogin(String userId, String password) throws Exception {
		Boolean returnValue = Boolean.FALSE;
		String startTime = null, endTime = null;
		if (logger.isInfoEnabled())
			logger.info("In validateLogin() : UserId- " + userId + "Password: " + password);
		try {
			LoginDetails loginDetails = null;
			DBObject dbObject = new BasicDBObject();
			dbObject.put("userId", userId);
			Query searchUserQuery = new Query(Criteria.where("userLoginId").is(userId));
			
			if(logger.isDebugEnabled()) {
				startTime = Utility.startTime();
				logger.info("In validateLogin() : Start Time : " + Utility.startTime());
				System.out.println("In validateLogin() : Start Time : " + Utility.startTime());
			}
			
			loginDetails = mongoTemplate.findOne(searchUserQuery, LoginDetails.class);
			
			if(logger.isDebugEnabled()) {
				endTime = Utility.endTime();
				logger.info("In validateLogin() : End Time : " + endTime);
				System.out.println("In validateLogin() : End Time : " + endTime);
			}
			
			logger.info(Utility.calculateTime(startTime, endTime));
			System.out.println(Utility.calculateTime(startTime, endTime));
			
			if (loginDetails != null && loginDetails.getUserLoginId().equals(userId) && loginDetails.getUserLoginId().equals(password)) {
				sessionValue.setUserId(userId);
				sessionValue.setUserName(loginDetails.getName());
				sessionValue.setUserRole(loginDetails.getUserRole());
				logger.info("validate() : User information sucessfully store into the session");
				String sessionUser = sessionValue.getUserId();
				System.out.println("USer Id from Session : " + sessionUser);
				returnValue = Boolean.TRUE;
			} else {
				if (logger.isInfoEnabled())
					logger.info("In validateLogin() : DB Object is null");
				returnValue = Boolean.FALSE;
			}
		} catch (Exception exception) {
			returnValue = Boolean.FALSE;
			throw exception;
		}
		if (logger.isInfoEnabled())
			logger.info("In addUser() : UserId- " + userId + "Password: " + password);
		return returnValue;
	}

	@Override
	public Boolean addUser(String userId, String password, String name, String emailId, String userRole) throws Exception {
		Boolean returnValue = Boolean.FALSE;
		String userNameInSession =  "";
		try {
			if (logger.isInfoEnabled())
				logger.info("In addUser() : UserId- " + userId + "Password: " + password);
			
			if (sessionValue != null) {
				userNameInSession = sessionValue.getUserId();
			}
			
			LoginDetails loginDetails = new LoginDetails();

			loginDetails.setId(sequenceDao.getNextSequenceId(LOGIN_DETAILS_SEQ_KEY, SequenceCounter.class));
			loginDetails.setUserLoginId(userId);
			loginDetails.setPassword(password);
			loginDetails.setUserRole(userRole);
			loginDetails.setName(name);
			loginDetails.setEmailId(emailId);
			loginDetails.setParentId(userNameInSession);
			loginDetails.setCreatedBy(userNameInSession);
			loginDetails.setUpdatedBy(userNameInSession);
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

}
