package com.invictusdynamics.bookie.sequence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lucky_number_sequence")
public class LuckyNumberSequence {

	@Id
	private String id;
	private Integer luckyNumSeq;

	public LuckyNumberSequence() {
		super();
	}

	public LuckyNumberSequence(String id, Integer luckyNumSeq) {
		super();
		this.id = id;
		this.luckyNumSeq = luckyNumSeq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getLuckyNumSeq() {
		return luckyNumSeq;
	}

	public void setLuckyNumSeq(Integer luckyNumSeq) {
		this.luckyNumSeq = luckyNumSeq;
	}

	@Override
	public String toString() {
		return "LuckyNumberSequence [id=" + id + ", luckyNumSeq=" + luckyNumSeq + "]";
	}

}
