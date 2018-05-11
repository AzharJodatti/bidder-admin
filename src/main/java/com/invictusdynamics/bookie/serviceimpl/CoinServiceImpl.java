package com.invictusdynamics.bookie.serviceimpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.invictusdynamics.bookie.entity.Recharges;
import com.invictusdynamics.bookie.global.SessionValue;
import com.invictusdynamics.bookie.sequence.CoinSequence;
import com.invictusdynamics.bookie.service.CoinService;
import com.invictusdynamics.bookie.service.SequenceDao;
import com.invictusdynamics.bookie.utility.Constants;

@Service("coinServiceImpl")
public class CoinServiceImpl implements CoinService {

	@Autowired
	private SequenceDao sequenceDao;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private SessionValue sessionValue;

	/** Resource bundle for exception message */
	ResourceBundle messageBundle = ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE_NAME);

	@Override
	public String saveCoins(Long coinsValue) {

		String returnMessage = "";
		String userNameInSession = "";
		Recharges recharges = new Recharges();
		if (sessionValue != null) {
			userNameInSession = sessionValue.getUserId();
		}
		if (coinsValue != null) {
			recharges.setId(sequenceDao.getNextSequenceId(Constants.COIN_SEQ_KEY, CoinSequence.class));
			recharges.setCoins(coinsValue);
			recharges.setRecharedBy(userNameInSession);
			recharges.setRechargeDate(new Timestamp(new Date().getTime()));

			/** "GENERATED | TRANSFERED" */
			recharges.setType(Constants.GENERATED);

			/** TODO : Read from session */
			recharges.setCreatedBy(userNameInSession);
			recharges.setUpdatedBy(userNameInSession);
			recharges.setCreatedAt(new Timestamp(new Date().getTime()));
			recharges.setUpdatedAt(new Timestamp(new Date().getTime()));

			mongoTemplate.save(recharges);

			returnMessage = messageBundle.getString("coinSaveSuccessMsg");
		} else {
			System.out.println("In CoinServiceImpl.saveCoins() : Coin value is null");
		}
		return returnMessage;
	}

}
