package com.invictusdynamics.bookie.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recharges")
public class Recharges {

	@Id
	private Long id;
	private String userId;
	private Long coins;
	private String recharedBy;
	private Date rechargeDate;
	/** "GENERATED | TRANSFERED" */
	private String type;
	private String createdBy;
	private String updatedBy;
	private Date createdAt;
	private Date updatedAt;

	public Recharges() {
		super();
	}

	public Recharges(long id, String userId, long coins, String recharedBy, Date rechargeDate, String type, String createdBy, String updatedBy, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.coins = coins;
		this.recharedBy = recharedBy;
		this.rechargeDate = rechargeDate;
		this.type = type;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getCoins() {
		return coins;
	}

	public void setCoins(long coins) {
		this.coins = coins;
	}

	public String getRecharedBy() {
		return recharedBy;
	}

	public void setRecharedBy(String recharedBy) {
		this.recharedBy = recharedBy;
	}

	public Date getRechargeDate() {
		return rechargeDate;
	}

	public void setRechargeDate(Date rechargeDate) {
		this.rechargeDate = rechargeDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
		return "Recharges [id=" + id + ", userId=" + userId + ", coins=" + coins + ", recharedBy=" + recharedBy + ", rechargeDate=" + rechargeDate + ", type=" + type + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
