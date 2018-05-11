package com.invictusdynamics.bookie.sequence;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "sequence")
public class SequenceCounter {

	@Id
	private String id;
	private Integer seq;

	public SequenceCounter() {
		super();
	}

	public SequenceCounter(String sequenceId, Integer sequence) {
		super();
		this.id = sequenceId;
		this.seq = sequence;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	@Override
	public String toString() {
		return "SequenceCounter [id=" + id + ", seq=" + seq + "]";
	}

}
