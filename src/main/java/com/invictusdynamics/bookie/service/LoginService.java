package com.invictusdynamics.bookie.service;

/**
 * Service for user level functionality
 * 
 * @author Amol K Golhar
 * @since 12 April 2018
 *
 */
public interface LoginService {

	/**
	 * @author Amol K Golhar
	 * 
	 * @since 12 April 2018
	 * @param userId
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public Boolean validateLogin(String userId, String password) throws Exception;

	/**
	 * @author Amol K Golhar
	 * 
	 * @since 12 April 2018
	 * @param userId
	 * @param password
	 * @throws Exception
	 */
	public Boolean addUser(String userId, String password, String name, String emailId, String userRole) throws Exception;

}