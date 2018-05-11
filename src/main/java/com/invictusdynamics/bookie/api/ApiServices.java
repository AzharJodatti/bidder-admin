package com.invictusdynamics.bookie.api;

import java.util.List;

import com.invictusdynamics.bookie.entity.BookieDetails;
import com.invictusdynamics.bookie.entity.LoginDetails;
import com.invictusdynamics.bookie.entity.LuckyNumberDetails;
import com.invictusdynamics.bookie.entity.Recharges;

public interface ApiServices {

	public List<LoginDetails> getUsersDetails() throws Exception;

	public LoginDetails getUserDetailsById(String userId) throws Exception;

	public List<Recharges> getRechargeDetails() throws Exception;

	public Recharges getRechargesrDetailsById(String userId) throws Exception;
	
	public List<BookieDetails> getAllBookieDetails() throws Exception;


}
