package com.invictusdynamics.bookie.sequence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recharge_sequence")
public class RechargeSequence {
	@Id
	private String id;
	private Integer rechargeSeq;

	public RechargeSequence() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RechargeSequence(String id, Integer rechargeSeq) {
		super();
		this.id = id;
		this.rechargeSeq = rechargeSeq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRechargeSeq() {
		return rechargeSeq;
	}

	public void setRechargeSeq(Integer rechargeSeq) {
		this.rechargeSeq = rechargeSeq;
	}

	@Override
	public String toString() {
		return "RechargeSequence [id=" + id + ", rechargeSeq=" + rechargeSeq + "]";
	}

}
