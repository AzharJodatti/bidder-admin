package com.invictusdynamics.bookie.service;

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
	public String saveLuckyNumber(Long openValue, Long closeValue, String region);

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
}
