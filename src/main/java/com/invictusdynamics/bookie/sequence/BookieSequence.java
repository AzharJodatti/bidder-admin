package com.invictusdynamics.bookie.sequence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookie_sequence")
public class BookieSequence {

	@Id
	private String id;
	private Integer bookieSequence;

	public BookieSequence() {
		super();
	}

	public BookieSequence(String id, Integer bookieSequence) {
		super();
		this.id = id;
		this.bookieSequence = bookieSequence;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getBookieSequence() {
		return bookieSequence;
	}

	public void setBookieSequence(Integer bookieSequence) {
		this.bookieSequence = bookieSequence;
	}

	@Override
	public String toString() {
		return "BookieSequence [id=" + id + ", bookieSequence=" + bookieSequence + "]";
	}

}
