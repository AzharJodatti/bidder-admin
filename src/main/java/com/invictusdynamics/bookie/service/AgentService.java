package com.invictusdynamics.bookie.service;

import java.util.List;

import com.invictusdynamics.bookie.entity.LoginDetails;

/**
 * Services related to Agent
 * 
 * @author amolku570123
 * @since 30 April 2017
 * 
 * 
 */
public interface AgentService {
	/**
	 * @return
	 * @throws Exception
	 */
	public Boolean saveAgentDetails(String userId, String password, String name, String emailId, String userRole) throws Exception;

	/**
	 * @return
	 * @throws Exception
	 */
	public List<LoginDetails> readLoginDetailsByRole(String userRole) throws Exception;
	
	/**
	 * @param userId
	 * @param rechargeBy
	 * @param coinAmount
	 * @return
	 * @throws Exception
	 */
	public Boolean saveRechargeDetails(String userId, String rechargeBy, Long coinAmount) throws Exception;
}
