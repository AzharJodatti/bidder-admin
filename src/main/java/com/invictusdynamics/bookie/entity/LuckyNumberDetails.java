package com.invictusdynamics.bookie.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lucky_numbers")
public class LuckyNumberDetails {

	@Id
	private Long id;
	private Long open;
	private Long close;
	private String jodi;
	private Long pan;
	private String region;
	private String createdBy;
	private String updatedBy;
	private Date createdDate;
	private Date updatedDate;

	public LuckyNumberDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LuckyNumberDetails(Long id, Long open, Long close, String jodi, Long pan, String region, String createdBy, String updatedBy, Date createdDate, Date updatedDate) {
		super();
		this.id = id;
		this.open = open;
		this.close = close;
		this.jodi = jodi;
		this.pan = pan;
		this.region = region;
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

	public Long getOpen() {
		return open;
	}

	public void setOpen(Long open) {
		this.open = open;
	}

	public Long getClose() {
		return close;
	}

	public void setClose(Long close) {
		this.close = close;
	}

	public String getJodi() {
		return jodi;
	}

	public void setJodi(String jodi) {
		this.jodi = jodi;
	}

	public Long getPan() {
		return pan;
	}

	public void setPan(Long pan) {
		this.pan = pan;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
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
		return "LuckyNumberDetails [id=" + id + ", open=" + open + ", close=" + close + ", jodi=" + jodi + ", pan=" + pan + ", region=" + region + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

}
