package com.invictusdynamics.bookie.apiimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.invictusdynamics.bookie.api.ApiServices;
import com.invictusdynamics.bookie.entity.BookieDetails;
import com.invictusdynamics.bookie.entity.LoginDetails;
import com.invictusdynamics.bookie.entity.Recharges;

@Service("apiServiceImpl")
public class ApiServiceImpl implements ApiServices {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<LoginDetails> getUsersDetails() throws Exception {
		List<LoginDetails> userDetailsList = null;
		try {
			userDetailsList = mongoTemplate.findAll(LoginDetails.class);
		} catch (Exception exception) {
			System.out.println("Exception in getUsersDetails() : " + exception);
		}
		return userDetailsList;
	}

	@Override
	public LoginDetails getUserDetailsById(String userId) throws Exception {
		if (userId != null && userId != "") {
			try {
				List<LoginDetails> userDetailsList = getUsersDetails();
				if (userDetailsList != null) {
					for (LoginDetails loginDetailsObj : userDetailsList) {
						if (loginDetailsObj.getUserLoginId().equalsIgnoreCase(userId)) {
							return loginDetailsObj;
						}
					}
				}
			} catch (Exception exception) {
				System.out.println("Exception in getUsersDetails() : " + exception);
			}
		}
		return null;
	}

	@Override
	public List<Recharges> getRechargeDetails() throws Exception {
		List<Recharges> rechargesDetailsList = null;
		try {
			rechargesDetailsList = mongoTemplate.findAll(Recharges.class);
		} catch (Exception exception) {
			System.out.println("Exception in getUsersDetails() : " + exception);
		}
		return rechargesDetailsList;
	}

	@Override
	public Recharges getRechargesrDetailsById(String userId) throws Exception {
		if (userId != null && userId != "") {
			try {
				List<Recharges> rechargesDetailsList = mongoTemplate.findAll(Recharges.class);
				if (rechargesDetailsList != null) {
					for (Recharges recharges : rechargesDetailsList) {
						if (recharges.getUserId().equalsIgnoreCase(userId)) {
							return recharges;
						}
					}
				}
			} catch (Exception exception) {
				System.out.println("Exception in getUsersDetails() : " + exception);
			}
		}
		return null;
	}

	@Override
	public List<BookieDetails> getAllBookieDetails() throws Exception {
		List<BookieDetails> bookieDetailsList = null;
		try {
			bookieDetailsList = mongoTemplate.findAll(BookieDetails.class);
		} catch (Exception exception) {
			System.out.println("Exception in getUsersDetails() : " + exception);
		}
		return bookieDetailsList;
	}
}
