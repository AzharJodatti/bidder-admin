package com.invictusdynamics.bookie.controller;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.invictusdynamics.bookie.entity.TimeDetails;
import com.invictusdynamics.bookie.service.LuckyNumberService;
import com.invictusdynamics.bookie.utility.Constants;

@Controller
public class LuckyNumberController {

	/** Resource bundle for exception message */
	ResourceBundle messageBundle = ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE_NAME);

	@Autowired
	@Qualifier("luckyNumberServiceImpl")
	private LuckyNumberService luckyNumberService;

	@RequestMapping(value = "/luckyNumber", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView luckyNumberPage() {
		return new ModelAndView("luckynumber/LuckyNumber");
	}

	@RequestMapping(value = "/saveLuckyNumber", method = RequestMethod.POST)
	@ResponseBody
	public String saveLuckyNumber(@RequestParam("openValue") Long openValue, @RequestParam("closeValue") long closeValue, @RequestParam("regionValue") String regionValue) {
		String returnMessage = messageBundle.getString("luckyNumberSaveFailureMsg");
		try {
			returnMessage = luckyNumberService.saveLuckyNumber(openValue, closeValue, regionValue);
		} catch (Exception exception) {
			throw exception;
		}
		return returnMessage;
	}

	@RequestMapping(value = "/saveTime", method = RequestMethod.POST)
	@ResponseBody
	public String saveTime(@RequestParam("openTime") String openTime, @RequestParam("closeTime") String closeTime, @RequestParam("region") String region) {
		String returnMessage = messageBundle.getString("luckyNumberSaveFailureMsg");
		try {
			returnMessage = luckyNumberService.saveTime(openTime, closeTime, region);
		} catch (Exception exception) {
			throw exception;
		}
		return returnMessage;
	}

	@RequestMapping(value = "/checkTimeDetails", method = RequestMethod.POST)
	@ResponseBody
	public Boolean checkTimeDetails(@RequestParam("userId") String userId) {
		TimeDetails timeDetails = null;
		Boolean retrunValue = Boolean.FALSE;
		try {
			timeDetails = luckyNumberService.getTimeDetails(userId);
			if (timeDetails != null && timeDetails.getCreatedBy().equals(userId)) {
				retrunValue = Boolean.TRUE;
			}
		} catch (Exception exception) {
			retrunValue = Boolean.FALSE;
			throw exception;
		}
		return retrunValue;
	}
	
	@RequestMapping(value = "/getTimeDetails", method = RequestMethod.POST)
	@ResponseBody
	public TimeDetails getTimeDetails(@RequestParam("userId") String userId) {
		TimeDetails timeDetails = null;
		try {
			timeDetails = luckyNumberService.getTimeDetails(userId);
		} catch (Exception exception) {
			throw exception;
		}
		return timeDetails;
	}
}
