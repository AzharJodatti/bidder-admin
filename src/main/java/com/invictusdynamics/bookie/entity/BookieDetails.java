package com.invictusdynamics.bookie.entity;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookie_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookieDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long _id;
	private String bookieName;
	private Long coinTransferValue;
	private Long commission;
	private String coinTransferFrom;

	private String createdBy;
	private String updatedBy;
	private Date createdAt;
	private Date updatedAt;

	@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
	private String _class;

	public BookieDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookieDetails(Long _id, String bookieName, Long coinTransferValue, Long commission, String coinTransferFrom, String createdBy, String updatedBy, Date createdAt, Date updatedAt, String _class) {
		super();
		this._id = _id;
		this.bookieName = bookieName;
		this.coinTransferValue = coinTransferValue;
		this.commission = commission;
		this.coinTransferFrom = coinTransferFrom;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this._class = _class;
	}

	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public String getBookieName() {
		return bookieName;
	}

	public void setBookieName(String bookieName) {
		this.bookieName = bookieName;
	}

	public Long getCoinTransferValue() {
		return coinTransferValue;
	}

	public void setCoinTransferValue(Long coinTransferValue) {
		this.coinTransferValue = coinTransferValue;
	}

	public Long getCommission() {
		return commission;
	}

	public void setCommission(Long commission) {
		this.commission = commission;
	}

	public String getCoinTransferFrom() {
		return coinTransferFrom;
	}

	public void setCoinTransferFrom(String coinTransferFrom) {
		this.coinTransferFrom = coinTransferFrom;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BookieDetails [_id=" + _id + ", bookieName=" + bookieName + ", coinTransferValue=" + coinTransferValue + ", commission=" + commission + ", coinTransferFrom=" + coinTransferFrom + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", _class=" + _class + "]";
	}

}
