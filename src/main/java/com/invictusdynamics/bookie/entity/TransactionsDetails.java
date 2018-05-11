package com.invictusdynamics.bookie.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactions_details")
public class TransactionsDetails {

	@Id
	private Long id;
	private Date date;

	/** "MUMBAI | KALYAN", */
	private String category;

	/** :"OPEN|CLOSE|JODI", */
	private String type;
	private String number;
	private Long coins;
	private String userId;
	private String playedBy;

	private String createdBy;
	private String updatedBy;
	private Date createdAt;
	private Date updatedAt;

	public TransactionsDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionsDetails(Long id, Date date, String category, String type, String number, Long coins, String userId, String playedBy, String createdBy, String updatedBy, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.date = date;
		this.category = category;
		this.type = type;
		this.number = number;
		this.coins = coins;
		this.userId = userId;
		this.playedBy = playedBy;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getCoins() {
		return coins;
	}

	public void setCoins(Long coins) {
		this.coins = coins;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPlayedBy() {
		return playedBy;
	}

	public void setPlayedBy(String playedBy) {
		this.playedBy = playedBy;
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

	@Override
	public String toString() {
		return "TransactionsDetails [id=" + id + ", date=" + date + ", category=" + category + ", type=" + type + ", number=" + number + ", coins=" + coins + ", userId=" + userId + ", playedBy=" + playedBy + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
