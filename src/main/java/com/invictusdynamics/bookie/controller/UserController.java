package com.invictusdynamics.bookie.controller;

import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.invictusdynamics.bookie.utility.Constants;

/**
 * @author amolku570123
 * @since 12 April 2018
 */
@Controller
public class UserController {

	/** Resource bundle for exception message */
	ResourceBundle messageBundle = ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE_NAME);

	/**
	 * @author amolku570123
	 * @since 12 April 2018
	 * @return {@code ModelAndView}
	 */
	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView addUser() {
		return new ModelAndView("user/User");
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView signUp() {
		return new ModelAndView("user/UserRegistration");
	}
}
