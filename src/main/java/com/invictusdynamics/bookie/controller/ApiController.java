package com.invictusdynamics.bookie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.invictusdynamics.bookie.api.ApiServices;
import com.invictusdynamics.bookie.entity.BookieDetails;
import com.invictusdynamics.bookie.entity.LoginDetails;
import com.invictusdynamics.bookie.entity.LuckyNumberDetails;
import com.invictusdynamics.bookie.entity.Recharges;
import com.invictusdynamics.bookie.service.LuckyNumberService;

@Controller
public class ApiController {

	@Autowired
	@Qualifier("apiServiceImpl")
	private ApiServices apiService;
	
	@Autowired
	@Qualifier("luckyNumberServiceImpl")
	private LuckyNumberService luckyNumberService;
	
	@RequestMapping(value = "/getAllUserData", method = RequestMethod.GET)
	@ResponseBody
	public List<LoginDetails> userDetails() {
		List<LoginDetails> userDetailsList = null;
		try {
			userDetailsList = apiService.getUsersDetails();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return userDetailsList;
	}

	@RequestMapping(value = "/getUserData/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public LoginDetails userDetailsById(@PathVariable("userId") String userId) {
		LoginDetails userDetail = null;
		try {
			userDetail = apiService.getUserDetailsById(userId);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return userDetail;
	}
	
	@RequestMapping(value = "/getAllBookieDetails", method = RequestMethod.GET)
	@ResponseBody
	public List<BookieDetails> getAllBookieBetails() {
		List<BookieDetails> bookieDetailsList = null;
		try {
			bookieDetailsList = apiService.getAllBookieDetails();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return bookieDetailsList;
	}
	
	@RequestMapping(value = "/getAllLuckyNumberDetails", method = RequestMethod.GET)
	@ResponseBody
	public List<LuckyNumberDetails> getAllLuckyNumberDetails() {
		List<LuckyNumberDetails> luckyNumberDetailsList = null;
		try {
			luckyNumberDetailsList = luckyNumberService.getLuckyNumberDetails();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return luckyNumberDetailsList;
	}
	
	/**
	 * Date format should be 
	 * 
	 * @param todaysDate "yyyy-MM-dd"
	 * @return
	 */
/*	@RequestMapping(value = "/getTodaysLuckyNumber/{todaysDate}", method = RequestMethod.GET)
	@ResponseBody
	public LuckyNumberDetails getTodaysLuckyNumber(@PathVariable("todaysDate") String todaysDate) {
		LuckyNumberDetails luckyNumberDetails = null;
		try {
			luckyNumberDetails= luckyNumberService.getTodaysLuckyNumber(todaysDate);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return luckyNumberDetails;
	}*/
	
	@RequestMapping(value = "/getAllRechargeDetails", method = RequestMethod.GET)
	@ResponseBody
	public List<Recharges> getAllRechargeDetails() {
		List<Recharges> rechargeDetailsList = null;
		try {
			rechargeDetailsList = apiService.getRechargeDetails();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return rechargeDetailsList;
	}
	
	@RequestMapping(value = "/getRechargeDetailsById/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Recharges getRechargeDetailsById(@PathVariable("userId") String userId) {
		Recharges recharge = null;
		try {
			recharge = apiService.getRechargesrDetailsById(userId);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return recharge;
	}
}
