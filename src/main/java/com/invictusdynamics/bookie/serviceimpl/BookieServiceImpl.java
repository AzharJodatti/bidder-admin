package com.invictusdynamics.bookie.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.invictusdynamics.bookie.entity.BookieDetails;
import com.invictusdynamics.bookie.entity.LoginDetails;
import com.invictusdynamics.bookie.entity.Recharges;
import com.invictusdynamics.bookie.entity.Wallet;
import com.invictusdynamics.bookie.global.SessionValue;
import com.invictusdynamics.bookie.sequence.BookieSequence;
import com.invictusdynamics.bookie.sequence.RechargeSequence;
import com.invictusdynamics.bookie.service.BookieService;
import com.invictusdynamics.bookie.service.SequenceDao;
import com.invictusdynamics.bookie.utility.Constants;
import com.invictusdynamics.bookie.utility.Utility;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Service("bookieServiceImpl")
public class BookieServiceImpl implements BookieService {

	/** Resource bundle for message */
	ResourceBundle messageBundle = ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE_NAME);
	final static Logger logger = Logger.getLogger(BookieServiceImpl.class);
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private SequenceDao sequenceDao;

	@Autowired
	private SessionValue sessionValue;

	@Override
	public String saveBookieDetails(String bookieName, Long coinTransferValue, Long commission) {
		String returnValue = messageBundle.getString("saveBookieFailure");
		String userNameInSession = "";
		if (bookieName != null && bookieName != "") {
			try {
				BookieDetails bookieDetails = new BookieDetails();

				if (sessionValue != null) {
					userNameInSession = sessionValue.getUserId();
				}

				bookieDetails.set_id(sequenceDao.getNextSequenceId(Constants.BOOKIE_SEQ_KEY, BookieSequence.class));
				bookieDetails.setBookieName(bookieName);
				bookieDetails.setCoinTransferValue(coinTransferValue);
				bookieDetails.setCommission(commission);

				bookieDetails.setCoinTransferFrom(userNameInSession);
				bookieDetails.setCreatedBy(userNameInSession);
				bookieDetails.setUpdatedBy(userNameInSession);
				bookieDetails.setCreatedAt(new Timestamp(new Date().getTime()));
				bookieDetails.setUpdatedAt(new Timestamp(new Date().getTime()));
				mongoTemplate.save(bookieDetails);
				returnValue = messageBundle.getString("saveBookieSuccess");
			} catch (Exception exception) {
				returnValue = messageBundle.getString("saveBookieFailure");
				exception.printStackTrace();
				throw exception;
			}
		}
		return returnValue;
	}

	@Override
	public List<BookieDetails> getBookieDetailList() throws Exception {
		List<BookieDetails> bookieDetailList = null;
		BookieDetails bookieDetails = null;
		String startTime = null, endTime = null;
		try {

			if (logger.isDebugEnabled()) {
				startTime = Utility.startTime();
				logger.info("In getBookieDetailList() : Start Time : " + Utility.startTime());
				System.out.println("In getBookieDetailList() : Start Time : " + Utility.startTime());
			}

			DBCollection collection = mongoTemplate.getCollection(Constants.BOOKIE_DETAILS);
			DBCursor cursor = collection.find();

			if (cursor != null) {
				bookieDetailList = new ArrayList<BookieDetails>();
				for (DBObject dbObject : cursor) {
					String jsonString = Utility.ObjectToString(dbObject);
					bookieDetails = (BookieDetails) Utility.StringToObject(jsonString, BookieDetails.class.getCanonicalName());
					bookieDetailList.add(bookieDetails);
				}
			}

			if (logger.isDebugEnabled()) {
				endTime = Utility.endTime();
				logger.info("In getBookieDetailList() : End Time : " + endTime);
				System.out.println("In getBookieDetailList() : End Time : " + endTime);
			}

			logger.info("In getBookieDetailList() : Total Time : " + Utility.calculateTime(startTime, endTime));
			System.out.println("In getBookieDetailList() : Total Time : " + Utility.calculateTime(startTime, endTime));

		} catch (Exception exception) {
			System.out.println(exception);
			exception.printStackTrace();
		}
		return bookieDetailList;
	}

	@Override
	public String saveUserCoinTransferDetails(String userId, Long coinTransferValue) throws Exception {
		String returnValue = messageBundle.getString("saveFailure");
		String userNameInSession = "";
		try {
			if (sessionValue != null) {
				userNameInSession = sessionValue.getUserId();
			}

			Wallet wallet = new Wallet();
			wallet.setId(sequenceDao.getNextSequenceId(Constants.RECHARGE_SEQ_KEY, RechargeSequence.class));
			wallet.setUserId(userId);
			wallet.setCoins(coinTransferValue);

			wallet.setCreatedBy(userNameInSession);
			wallet.setUpdatedBy(userNameInSession);
			wallet.setCreatedAt(new Timestamp(new Date().getTime()));
			wallet.setUpdatedAt(new Timestamp(new Date().getTime()));
			mongoTemplate.save(wallet);
			returnValue = messageBundle.getString("saveSuccess");
		} catch (Exception exception) {
			System.out.println(exception);
			exception.printStackTrace();
		}
		return returnValue;
	}

	@Override
	public String transferAmountToUser(String userId, Long coinTransferValue) {
		String retrunValue = messageBundle.getString("saveFailure"); 
		try {
			if(userId != null) {
				Recharges recharges = null;
				Query searchUserQuery = new Query(Criteria.where("userId").is(userId));
				DBCollection rechargeCollection = mongoTemplate.getCollection("recharges");
				recharges = mongoTemplate.findOne(searchUserQuery, Recharges.class);
				Long existingCoins = 0L;
				if (recharges != null) {
					existingCoins = recharges.getCoins();

					existingCoins = existingCoins + coinTransferValue;
					recharges.setCoins(existingCoins);
					Integer intupdateCoins = existingCoins.intValue();

					DBObject query = new BasicDBObject("userId", userId);
					DBObject update = new BasicDBObject();
					update.put("$set", new BasicDBObject("coins", intupdateCoins));
					retrunValue = messageBundle.getString("saveSuccess");
					/*DBCursor cursor = rechargeCollection.find();
					try {
						while (cursor.hasNext()) {
							System.out.println(cursor.next());
						}
					} finally {
						cursor.close();
					}*/
					rechargeCollection.findAndModify(query, update);
				}
				
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println(exception);
		}
		return retrunValue;
	}
}