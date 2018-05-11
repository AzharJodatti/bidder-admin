package com.invictusdynamics.bookie.sequence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transaction_sequence")
public class TransactionSequence {
	@Id
	private String id;
	private Integer transactSeq;

	public TransactionSequence() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionSequence(String id, Integer transactSeq) {
		super();
		this.id = id;
		this.transactSeq = transactSeq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getTransactSeq() {
		return transactSeq;
	}

	public void setTransactSeq(Integer transactSeq) {
		this.transactSeq = transactSeq;
	}

	@Override
	public String toString() {
		return "TransactionSequence [id=" + id + ", transactSeq=" + transactSeq + "]";
	}

}
