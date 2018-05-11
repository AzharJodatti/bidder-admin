package com.invictusdynamics.bookie.sequence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "coin_sequence")
public class CoinSequence {

	@Id
	private String id;
	private Integer coinSeq;

	public CoinSequence() {
		super();
	}

	public CoinSequence(String id, Integer coinSeq) {
		super();
		this.id = id;
		this.coinSeq = coinSeq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCoinSeq() {
		return coinSeq;
	}

	public void setCoinSeq(Integer coinSeq) {
		this.coinSeq = coinSeq;
	}

	@Override
	public String toString() {
		return "CoinSequence [id=" + id + ", coinSeq=" + coinSeq + "]";
	}

}
