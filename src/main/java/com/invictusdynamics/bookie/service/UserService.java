package com.invictusdynamics.bookie.service;

import java.util.List;

import com.invictusdynamics.bookie.entity.LoginDetails;

public interface UserService {
	
	/**
	 * @return
	 * @throws Exception
	 */
	public List<LoginDetails> readUserDetails() throws Exception;
}
