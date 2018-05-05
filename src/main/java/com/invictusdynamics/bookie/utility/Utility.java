package com.invictusdynamics.bookie.utility;

import org.codehaus.jackson.map.ObjectMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.map.JsonMappingException;

/**
 * Utilities to help
 * 
 * @author Amol K Golhar.
 * @since 30 April 2018
 * 
 *
 */
public class Utility {

	/**
	 * This is a general method that serializes object
	 * 
	 * @author Amol K Golhar.
	 * @since 30 April 2018
	 * 
	 * @param object
	 * @return
	 * @throws DCFException
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String ObjectToString(Object object) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(object);
		} catch (Exception exception) {
			throw exception;
		}
		return jsonInString;
	}

	/**
	 * This is a method that deserializes objects
	 * 
	 * @author Amol K Golhar.
	 * @since 30 April 2018
	 * @param objectStr
	 * @param qualifiedObjName
	 * @return Object
	 * @throws DCFSystemException
	 * @Modify Exception Handling implementation - amolku570123
	 */
	public static Object StringToObject(String objectStr, String qualifiedObjName) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Object object = null;
		try {
			object = mapper.readValue(objectStr, Class.forName(qualifiedObjName));
		} catch (Exception exception) {
			throw exception;
		}
		return object;
	}
	
	/**
	 * 
	 * @return Time
	 */
	public static String startTime() {
		return (new SimpleDateFormat("dd/MM/YYYY HH:mm:ss:SSSS")).format(new Date(System.currentTimeMillis()));
	}

	/**
	 * @return Time
	 */
	public static String endTime() {
		return (new SimpleDateFormat("dd/MM/YYYY HH:mm:ss:SSSS")).format(new Date(System.currentTimeMillis()));
	}

	/**
	 * Calculate the execution time.
	 * 
	 * @param startTime
	 * @param endTime
	 * @return {@code Time in}
	 */
	public static String calculateTime(String startTime, String endTime) {
		int diffSeconds = 0, diffMinutes = 0, diffHours = 0;
		long timeDifference = 0;
		Date startDate = null;
		Date endDate = null;
		String returnValue = "";
		if (startTime != null && !startTime.isEmpty() && endTime != null && !endTime.isEmpty()) {
			try {
				/** HH converts hour in 24 hours format (0-23), day calculation */
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss:SSSS");

				startDate = dateFormat.parse(startTime);
				endDate = dateFormat.parse(endTime);

				timeDifference = endDate.getTime() - startDate.getTime();

				diffSeconds = (int) (timeDifference / 1000) % 60;
				diffMinutes = (int) (timeDifference / (60 * 1000)) % 60;
				diffHours = (int) (timeDifference / (60 * 60 * 1000)) % 24;
				returnValue = "Total execution time :: " + diffHours + " hours. :: " + diffMinutes + " minutes. ::" + diffSeconds + " seconds. :: " + timeDifference + " milliseconds";
			} catch (ParseException parseException) {
				parseException.printStackTrace();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		return returnValue;
	}
	
}
