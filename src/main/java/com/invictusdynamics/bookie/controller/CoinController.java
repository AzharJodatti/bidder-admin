package com.invictusdynamics.bookie.controller;

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

import com.invictusdynamics.bookie.service.CoinService;
import com.invictusdynamics.bookie.utility.Constants;

/**
 * @author Amol K Golhar
 * @since 19 April 2018
 *
 */
@Controller
public class CoinController {

	/** Resource bundle for exception message */
	ResourceBundle messageBundle = ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE_NAME);

	final static Logger logger = Logger.getLogger(CoinController.class);

	@Autowired
	@Qualifier("coinServiceImpl")
	private CoinService coinService;

	/** Resource bundle for exception message */
	@RequestMapping(value = "/coins", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView coinsPage() {
		return new ModelAndView("coin/Coin");
	}

	@RequestMapping(value = "/saveCoin", method = RequestMethod.POST)
	@ResponseBody
	public String saveCoin(@RequestParam("coinValue") long coinValue) {
		String returnMessage = messageBundle.getString("coinSaveFailureMsg");
		try {
			returnMessage = coinService.saveCoins(coinValue);
		} catch (Exception exception) {
			throw exception;
		}
		return returnMessage;
	}
}
