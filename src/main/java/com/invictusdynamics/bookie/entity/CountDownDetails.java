package com.invictusdynamics.bookie.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="countdown_details")
public class CountDownDetails {

	@Id
	private Long id;
	private String userId;
	private String hours;
	private String minutes;
	private Date createdDate;
}
