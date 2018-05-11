package com.bid.dto;

import java.io.Serializable;
import java.util.Map;

public class TransactionsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, Long> calculatedAmountMap;

	public Map<String, Long> getCalculatedAmountMap() {
		return calculatedAmountMap;
	}

	public void setCalculatedAmountMap(Map<String, Long> calculatedAmountMap) {
		this.calculatedAmountMap = calculatedAmountMap;
	}

	@Override
	public String toString() {
		return "TransactionsDTO [calculatedAmountMap=" + calculatedAmountMap + "]";
	}

}
