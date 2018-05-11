package com.invictusdynamics.bookie.global;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;

public class SessionValue implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(SessionValue.class);
	private String userId;
	private String userName;
	private String userRole;
	private Integer userSessionId;

	public SessionValue() {
		super();
	}

	public SessionValue(String userId, String userName, String userRole, Integer userSessionId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userRole = userRole;
		this.userSessionId = userSessionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		log.info("Setting user id : " + userId);
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Integer getUserSessionId() {
		return userSessionId;
	}

	public void setUserSessionId(Integer userSessionId) {
		this.userSessionId = userSessionId;
	}

	@PostConstruct
	public void init() {
		System.out.println("SessionValue initialization...");
		log.info("SessionValue initialization...");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("MdmSessionValue destroyed...");
		log.info("MdmSessionValue destroyed...");
	}
}