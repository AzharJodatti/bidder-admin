package com.invictusdynamics.bookie.service;

import java.util.List;

import com.invictusdynamics.bookie.entity.LuckyNumberDetails;
import com.invictusdynamics.bookie.entity.TimeDetails;

/**
 * @author amol K Golhar
 *
 */
public interface LuckyNumberService {

	/**
	 * @param openValue
	 * @param closeValue
	 * @param region
	 * @return
	 */
	public String saveLuckyNumber(String openValue, String closeValue, String region);

	/**
	 * @param openValue
	 * @param closeValue
	 * @param region
	 * @return
	 */
	public String saveTime(String openTime, String closeTime, String region);

	/**
	 * @param userId
	 * @return
	 */
	public TimeDetails getTimeDetails(String userId);
	
	/**
	 * @return
	 */
	public List<LuckyNumberDetails> getLuckyNumberDetails();

	public LuckyNumberDetails getTodaysLuckyNumber();
}
