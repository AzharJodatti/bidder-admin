package com.invictusdynamics.bookie.service;

public interface SequenceDao {
	@SuppressWarnings("rawtypes")
	long getNextSequenceId(String key, Class className);
}
