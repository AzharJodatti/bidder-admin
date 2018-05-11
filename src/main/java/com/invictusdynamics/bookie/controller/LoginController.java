package com.invictusdynamics.bookie.controller;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.invictusdynamics.bookie.entity.BookieDetails;
import com.invictusdynamics.bookie.entity.LoginDetails;
import com.invictusdynamics.bookie.global.SessionValue;
import com.invictusdynamics.bookie.service.AgentService;
import com.invictusdynamics.bookie.service.BookieService;
import com.invictusdynamics.bookie.service.LoginService;
import com.invictusdynamics.bookie.utility.Constants;

/**
 * @author Amol K Golhar
 * @since 12 April 2018
 *
 */
@Controller
public class LoginController {

	@Autowired
	@Qualifier("loginServiceImpl")
	private LoginService loginService;

	@Autowired
	@Qualifier("bookieServiceImpl")
	private BookieService bookieService;

	@Autowired
	@Qualifier("agentServiceImpl")
	private AgentService agentService;

	@Autowired
	private SessionValue sessionValue;

	/** Resource bundle for exception message */
	ResourceBundle messageBundle = ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE_NAME);

	final static Logger logger = Logger.getLogger(LoginService.class);

	@RequestMapping(value = "/homePage")
	@ResponseBody
	public ModelAndView homePage() {
		ModelAndView modelAndView = null;
		List<BookieDetails> bookieDetailList = null;
		List<LoginDetails> agentDetailsList = null;
		List<LoginDetails> usersDetailsList = null;
		try {
			modelAndView = new ModelAndView("Home");
			bookieDetailList = bookieService.getBookieDetailList();
			agentDetailsList = agentService.readLoginDetailsByRole(Constants.USER_ROLE_AGENT);
			usersDetailsList = agentService.readLoginDetailsByRole(Constants.USER_ROLE_USER);

			modelAndView.addObject("bookieDetailList", bookieDetailList);
			modelAndView.addObject("agentDetailsList", agentDetailsList);
			modelAndView.addObject("usersDetailsList", usersDetailsList);

			System.out.println(modelAndView);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * Check user credential and login into system.
	 * 
	 * @param request
	 * @param username
	 * @param password
	 * @return {@code ModelAndView}
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView login(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password) {
		Boolean returnValue = Boolean.FALSE;
		ModelAndView modelAndView = null;
		HttpSession httpSession = null;
		List<BookieDetails> bookieDetailList = null;
		List<LoginDetails> agentDetailsList = null;
		List<LoginDetails> usersDetailsList = null;
		try {
			httpSession = request.getSession();
			if (logger.isInfoEnabled())
				logger.info("In LoginController.login() : UserId- " + username + "Password: " + password);

			returnValue = loginService.validateLogin(username, password);

			if (returnValue) {
				modelAndView = new ModelAndView("Home", "message", messageBundle.getString("loginSuccess"));
				bookieDetailList = bookieService.getBookieDetailList();
				modelAndView.addObject("bookieDetailList", bookieDetailList);
				agentDetailsList = agentService.readLoginDetailsByRole(Constants.USER_ROLE_AGENT);
				modelAndView.addObject("agentDetailsList", agentDetailsList);
				usersDetailsList = agentService.readLoginDetailsByRole(Constants.USER_ROLE_AGENT);
				modelAndView.addObject("usersDetailsList", usersDetailsList);
				System.out.println(modelAndView);
				setHttpSessionLoginAttributes(httpSession);
			} else {
				modelAndView = new ModelAndView("../../index", "message", messageBundle.getString("loginFailure"));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		if (logger.isInfoEnabled())
			logger.info("In LoginController.login() : UserId- " + username + "Password: " + password);
		return modelAndView;
	}

	private void setHttpSessionLoginAttributes(HttpSession session) {
		if (session != null) {
			if (sessionValue.getUserId() != null) {
				session.setAttribute("userId", sessionValue.getUserId());
			}
			if (sessionValue.getUserId() != null) {
				session.setAttribute("userName", sessionValue.getUserName());
			}
			if (sessionValue.getUserRole() != null) {
				session.setAttribute("userRole", sessionValue.getUserRole());
			}
		}
	}

	/**
	 * @author amolku570123
	 * @since
	 * @return {@code ModelAndView}
	 */
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView signUp() {
		return new ModelAndView("user/UserRegistration");
	}

	/**
	 * @param username
	 * @param password
	 * @return {@code ModelAndView}
	 */
	@RequestMapping(value = "/userRegistration", method = RequestMethod.POST)
	@ResponseBody
	public String userRegistration(@RequestParam("userName") String username, @RequestParam("password") String password, @RequestParam("name") String name, @RequestParam("emailId") String emailId, @RequestParam("userRole") String userRole, @RequestParam("percentage") Long percentage) {
		Boolean booleanFlag = Boolean.FALSE;
		String returnMessage = messageBundle.getString("saveFailure");
		try {
			booleanFlag = loginService.addUser(username, password, name, emailId, userRole, percentage);
			if (booleanFlag) {
				returnMessage = messageBundle.getString("saveSuccess");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return returnMessage;
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView forgotPassword() {
		return new ModelAndView("index");
	}

	/**
	 * @author amolku570123
	 * @since
	 * @return {@code ModelAndView}
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView logout(HttpServletRequest request, HttpSession session) {
		if (session != null) {
			session.removeAttribute("userId");
			session.removeAttribute("userName");
			/* INVALIDATE SESSION (DESTROY THE SESSION) */
			session.invalidate();
			logger.info("Session invalidate succesfully");
		}

		return new ModelAndView("../../index", "message", messageBundle.getString("logoutMessage"));
	}
}
