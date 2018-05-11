package com.invictusdynamics.bookie.controller;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.invictusdynamics.bookie.entity.LoginDetails;
import com.invictusdynamics.bookie.service.AgentService;
import com.invictusdynamics.bookie.utility.Constants;
import com.invictusdynamics.bookie.utility.Utility;

@Controller
public class AgentController {

	/** Resource bundle for exception message */
	ResourceBundle messageBundle = ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE_NAME);

	final static Logger logger = Logger.getLogger(AgentController.class);

	@Autowired
	@Qualifier("agentServiceImpl")
	private AgentService agentService;

	/** Resource bundle for exception message */
	@RequestMapping(value = "/createAgent", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView coinsPage() {
		return new ModelAndView("agent/Agent");
	}

	@RequestMapping(value = "/saveAgent", method = RequestMethod.POST)
	@ResponseBody
	public String saveAgent(@RequestParam("userName") String username, @RequestParam("password") String password, @RequestParam("name") String name, @RequestParam("emailId") String emailId, @RequestParam("userRole") String userRole) {
		String returnMessage = messageBundle.getString("registrationFailure");
		Boolean booleanFlag = Boolean.FALSE;
		String startTime = null, endTime = null;
		try {

			if (logger.isDebugEnabled()) {
				startTime = Utility.startTime();
				logger.info("In saveAgent() : Start Time : " + Utility.startTime());
				System.out.println("In saveAgent() : Start Time : " + Utility.startTime());
			}

			booleanFlag = agentService.saveAgentDetails(username, password, name, emailId, userRole);

			if (logger.isDebugEnabled()) {
				endTime = Utility.endTime();
				logger.info("In saveAgent() : End Time : " + endTime);
				System.out.println("In saveAgent() : End Time : " + endTime);
			}

			logger.info("In saveAgent() : End Time : " + Utility.calculateTime(startTime, endTime));
			System.out.println("In saveAgent() : End Time : " + Utility.calculateTime(startTime, endTime));

			if (booleanFlag)
				returnMessage = messageBundle.getString("registrationSuccess");
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return returnMessage;
	}

	@RequestMapping(value = "/readAgentDetails", method = RequestMethod.GET)
	@ResponseBody
	public List<LoginDetails> readBookieDetails() {
		List<LoginDetails> loginDetailList = null;
		try {
			loginDetailList = agentService.readLoginDetailsByRole(Constants.USER_ROLE_AGENT);
		} catch (Exception exception) {
			System.out.println("In AgentServiceImpl().readAgentDetails() Exception block : " + exception);
			exception.printStackTrace();
		}
		return loginDetailList;
	}

	@RequestMapping(value = "/agentRecharge", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView agentRecharge() {
		List<LoginDetails> usersDetailsList = null;
		ModelAndView modelAndView = new ModelAndView("agent/AgentRecharge");
		try {
			usersDetailsList = agentService.readLoginDetailsByRole(Constants.USER_ROLE_USER);
			modelAndView.addObject("usersDetailsList", usersDetailsList);
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/saveRechargeDetails", method = RequestMethod.POST)
	@ResponseBody
	public String saveRechargeDetails(@RequestParam("userId") String userId, @RequestParam("rechargeBy") String rechargeBy, @RequestParam("coinAmount") Long coinAmount) {
		String returnValue = messageBundle.getString("saveFailure");
		Boolean saveFlag = Boolean.FALSE;
		try {
			saveFlag = agentService.saveRechargeDetails(userId, rechargeBy, coinAmount);
			if(saveFlag)
				returnValue = messageBundle.getString("saveSuccess");
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return returnValue;
	}
}
