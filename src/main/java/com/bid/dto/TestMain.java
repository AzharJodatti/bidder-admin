package com.bid.dto;

import java.math.BigDecimal;

public class TestMain {

	public static void main(String[] args) {

		Long a = 10L;
		Long b = 20L;

		BigDecimal aa = BigDecimal.valueOf(a);
		BigDecimal bb = BigDecimal.valueOf(b);
		BigDecimal hundred = new BigDecimal(100L);
		Long c = a / 100L * b;
		BigDecimal cc = ((aa.divide(hundred)).multiply(bb));
		System.out.println(cc);
	}

}
