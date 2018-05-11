package com.invictusdynamics.bookie.sequence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "time_sequence")
public class TimeSequence {

	@Id
	private String id;
	private Integer timeSeq;

	public TimeSequence() {
		super();
	}

	public TimeSequence(String id, Integer timeSeq) {
		super();
		this.id = id;
		this.timeSeq = timeSeq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getTimeSeq() {
		return timeSeq;
	}

	public void setTimeSeq(Integer timeSeq) {
		this.timeSeq = timeSeq;
	}

	@Override
	public String toString() {
		return "TimeSequence [id=" + id + ", timeSeq=" + timeSeq + "]";
	}

}
