package com.invictusdynamics.bookie.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Amol K Golhar
 * @since 12 April 2018
 *
 */
@Document(collection = "Users")
public class LoginDetails {

	@Id
	private Long id;
	private String userLoginId;
	private String name;
	private String emailId;
	private String parentId;
	private String password;
	private String userRole;
	private Long commissionPercentage;
	private String createdBy;
	private String updatedBy;
	private Date createdAt;
	private Date updatedAt;

	public LoginDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginDetails(Long id, String userLoginId, String name, String emailId, String parentId, String password, String userRole, Long commissionPercentage, String createdBy, String updatedBy, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.userLoginId = userLoginId;
		this.name = name;
		this.emailId = emailId;
		this.parentId = parentId;
		this.password = password;
		this.userRole = userRole;
		this.commissionPercentage = commissionPercentage;
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

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Long getCommissionPercentage() {
		return commissionPercentage;
	}

	public void setCommissionPercentage(Long commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
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
		return "LoginDetails [id=" + id + ", userLoginId=" + userLoginId + ", name=" + name + ", emailId=" + emailId + ", parentId=" + parentId + ", password=" + password + ", userRole=" + userRole + ", commissionPercentage=" + commissionPercentage + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
