package com.invictusdynamics.bookie.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bid.dto.TransactionsDTO;
import com.invictusdynamics.bookie.entity.BookieDetails;
import com.invictusdynamics.bookie.entity.LoginDetails;
import com.invictusdynamics.bookie.entity.LuckyNumberDetails;
import com.invictusdynamics.bookie.entity.TransactionsDetails;
import com.invictusdynamics.bookie.service.AgentService;
import com.invictusdynamics.bookie.service.BookieService;
import com.invictusdynamics.bookie.service.LuckyNumberService;
import com.invictusdynamics.bookie.service.TransactionService;
import com.invictusdynamics.bookie.utility.Constants;

@Controller
public class BookieController {

	/** Resource bundle for exception message */
	ResourceBundle messageBundle = ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE_NAME);

	@Autowired
	@Qualifier("bookieServiceImpl")
	private BookieService bookieService;
	
	@Autowired
	@Qualifier("agentServiceImpl")
	private AgentService agentService;
	
	@Autowired
	@Qualifier("transactionserviceimpl")
	private TransactionService transactionService;
	
	@Autowired
	@Qualifier("luckyNumberServiceImpl")
	private LuckyNumberService luckyNumberService;
	
	
	
	@RequestMapping(value = "/createBookie", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView bookieHome() {
		return new ModelAndView("bookie/Bookie");
	}

	@RequestMapping(value = "/saveBookie", method = RequestMethod.POST)
	@ResponseBody
	public String saveBookie(@RequestParam("bookieName") String bookieName, @RequestParam("coinTransferValue") long coinTransferValue, @RequestParam("commission") long commission) {
		String returnValue = "";
		try {
			returnValue = bookieService.saveBookieDetails(bookieName, coinTransferValue, commission);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return returnValue;
	}

	@RequestMapping(value = "/readBookieDetails", method = RequestMethod.GET)
	@ResponseBody
	public List<BookieDetails> readBookieDetails() {
		List<BookieDetails> bookieDetailList = null;
		try {
			bookieDetailList = bookieService.getBookieDetailList();
		} catch (Exception exception) {
			System.out.println("In AgentServiceImpl().readAgentDetails() Exception block : " + exception);
			exception.printStackTrace();
		}
		return bookieDetailList;
	}

	@RequestMapping(value = "/transferToUser", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView transferToUser() {
		List<LoginDetails> usersDetailsList = null;
		ModelAndView modelAndView = new ModelAndView("bookie/BookieTransfer");
		try {
			usersDetailsList = agentService.readLoginDetailsByRole(Constants.USER_ROLE_USER);
			modelAndView.addObject("usersDetailsList", usersDetailsList);
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/saveUserCoinTransferDetails", method = RequestMethod.POST)
	@ResponseBody
	public String saveUserCoinTransferDetails(@RequestParam("userId") String userId, @RequestParam("coinTransferValue") Long coinTransferValue) {
		String returnValue = messageBundle.getString("saveFailure");
		try {
			returnValue = bookieService.saveUserCoinTransferDetails(userId, coinTransferValue);
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/readTransactionDetails", method = RequestMethod.POST)
	@ResponseBody
	public List<TransactionsDetails> readTranactionDetails() {
		List<TransactionsDetails> transactionsDetailsList = null;
		try {
			transactionsDetailsList = transactionService.getTransactionDetails();
		} catch (Exception exception) {
			System.out.println("In AgentServiceImpl().readAgentDetails() Exception block : " + exception);
			exception.printStackTrace();
		}
		return transactionsDetailsList;
	}	
	
	@RequestMapping(value = "/setellment", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView setellmentHome() {
		List<LoginDetails> usersDetailsList = null;
		List<TransactionsDetails> transactionsDetailsList = null;
		LuckyNumberDetails luckyNumberDetails = null;
		TransactionsDTO transactionsDTO = null; 
		ModelAndView modelAndView = new ModelAndView("bookie/BookieSetellment");
		try {
			usersDetailsList = agentService.readLoginDetailsByRole(Constants.USER_ROLE_USER);
			transactionsDetailsList = transactionService.getTransactionDetails();
			luckyNumberDetails = luckyNumberService.getTodaysLuckyNumber();
			transactionsDTO = luckyNumberService.getCalculatedTransactionDetails();
			
			modelAndView.addObject("usersDetailsList", usersDetailsList);
			modelAndView.addObject("transactionsDetailsList", transactionsDetailsList);
			modelAndView.addObject("luckyNumberDetails", luckyNumberDetails);
			modelAndView.addObject("transactionsDTO", transactionsDTO);
		} catch (Exception exception) {
			System.out.println("In AgentServiceImpl().readAgentDetails() Exception block : " + exception);
			exception.printStackTrace();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/transferAmountToUser", method = RequestMethod.POST)
	@ResponseBody
	public String transferAmountToUser(@RequestParam("userId") String userId, @RequestParam("coinTransferValue") Long coinTransferValue) {
		String returnValue = messageBundle.getString("saveFailure");
		try {
			returnValue = bookieService.transferAmountToUser(userId, coinTransferValue);
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return returnValue;
	}
}
