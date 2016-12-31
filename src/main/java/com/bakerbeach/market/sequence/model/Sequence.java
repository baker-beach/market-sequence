package com.bakerbeach.market.sequence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sequence")
public class Sequence {
	
	@Id
	@Column(name = "name")
	public String name;

	@Column(name = "value")
	public Long value;

	public Sequence() {		
		super();
	}
	
	public Sequence(String name, Long value) {
		super();
		this.name = name;
		this.value = value;
	}

}