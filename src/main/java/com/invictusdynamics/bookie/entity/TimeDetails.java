package com.invictusdynamics.bookie.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "time")
public class TimeDetails {

	@Id
	private Long id;
	private String openTime;
	private String closeTime;
	private String region;
	/** Expired or Active */
	private String status;
	/*
	 * private String hours; private String minutes;
	 */
	private String createdBy;
	private String updatedBy;
	private String createdDate;
	private Date updatedDate;

	public TimeDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TimeDetails(Long id, String openTime, String closeTime, String region, String status, String createdBy, String updatedBy, String createdDate, Date updatedDate) {
		super();
		this.id = id;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.region = region;
		this.status = status;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "TimeDetails [id=" + id + ", openTime=" + openTime + ", closeTime=" + closeTime + ", region=" + region + ", status=" + status + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

}
