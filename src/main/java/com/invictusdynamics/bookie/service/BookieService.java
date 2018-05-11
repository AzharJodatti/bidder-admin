package com.invictusdynamics.bookie.service;

import java.util.List;

import com.invictusdynamics.bookie.entity.BookieDetails;

/**
 * Services for Bookie
 * 
 * @author amol k Golhar
 * @since April 26, 2018
 *
 */
public interface BookieService {

	/**
	 * Save bookie details in Database
	 * 
	 * @author amol k Golhar
	 * @since April 26, 2018
	 * @param bookieName
	 * @param coinTransferValue
	 * @param commission
	 * @return {@code String}
	 * @throws Exception
	 */
	public String saveBookieDetails(String bookieName, Long coinTransferValue, Long commission) throws Exception;

	/**
	 * Returns all bookie data available in Database.
	 * 
	 * @author amol k Golhar
	 * @since April 26, 2018
	 * @return {@code List<BookieDetails>}
	 * @throws Exception
	 */
	public List<BookieDetails> getBookieDetailList() throws Exception;
	
	/**
	 * @param userId
	 * @param coinTransferValue
	 * @param commission
	 * @return
	 * @throws Exception
	 */
	public String saveUserCoinTransferDetails(String userId, Long coinTransferValue) throws Exception;

}
