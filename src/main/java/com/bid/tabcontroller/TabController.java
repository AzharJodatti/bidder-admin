package com.bid.tabcontroller;

import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bid.tabservices.TabService;
import com.invictusdynamics.bookie.controller.AgentController;
import com.invictusdynamics.bookie.utility.Constants;

@Controller
public class TabController {

	/** Resource bundle for exception message */
	ResourceBundle messageBundle = ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE_NAME);

	final static Logger logger = Logger.getLogger(AgentController.class);

	@Autowired
	@Qualifier("tabserviceimpl")
	private TabService tabService;

	//@RequestMapping(value = "/saveTransactionData/{playedBy}/{category}/{coinType}/{amount}/{userSelectedNumber}", method = RequestMethod.GET)
	@RequestMapping(value = "/saveTransactionData", method = RequestMethod.POST)
	@ResponseBody
	public Boolean saveTransactionData(@RequestParam Map<String, String> parameterMap) {
		Boolean returnFlag = Boolean.FALSE;
		try {
			System.out.println("TabController.saveTransactionData() - Parameter Data - " + parameterMap);
			returnFlag = tabService.saveTransactionData(parameterMap);
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return returnFlag;
	}

}
