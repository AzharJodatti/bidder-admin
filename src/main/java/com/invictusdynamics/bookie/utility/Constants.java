package com.invictusdynamics.bookie.utility;

/**
 * This class contains constants used across the application
 * 
 * @author Amol K Golhar
 * @since 12 April 2018
 * 
 */
public class Constants {

	public static final String RESOURCE_BUNDLE_NAME = "resource";
	public static final String MESSAGE_BUNDLE_NAME = "messages";

	public static final int REGISTER_FLAG = 1;

	public static final String YES_FLAG = "Y";
	public static final String NO_FLAG = "N";

	public static final String YES_VALUE = "Yes";
	public static final String NO_VALUE = "No";

	public static final String EMPTY_VALUE = "";

	public static final String SUCCESS = "Success";
	public static final String FAILURE = "Failure";
	public static final String NAN = "NaN";
	public static final String ACTIVE = "active";
	public static final String EXPIRE= "expire";
	/** Sequence constants */
	public static final String COIN_SEQ_KEY = "coinSequence";
	public static final String BOOKIE_SEQ_KEY = "bookieSequence";
	public static final String LUCKY_NUMBER_SEQ_KEY = "luckyNumberSequence";
	public static final String TIME_SEQ_KEY = "timeSequence";
	public static final String LOGIN_DETAILS_SEQ_KEY = "loginSequence";
	public static final String RECHARGE_SEQ_KEY = "rechargeSequence";
	public static final String TRANSACTION_SEQ_KEY = "transactionSequence";
	
	
	public static final String GENERATED = "GENERATED";
	public static final String TRANSFERED = "TRANSFERED";
	public static final String USER_ROLE_AGENT = "agent";
	public static final String USER_ROLE_USER = "user";
	public static final String USER_ROLE_BOOKIE = "bookie";
	public static final String USER_ROLE_ADMIN = "admin";
	
	public static final String OPEN = "open";
	public static final String close = "close";
	public static final String JODI = "jodi";
	
	/*Database schemas*/
	public static final String USER_SCHEMA ="Users";
	public static final String BOOKIE_DETAILS ="bookie_details";
}
