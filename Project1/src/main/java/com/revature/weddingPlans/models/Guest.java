package com.revature.weddingPlans.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("2")
@PrimaryKeyJoinColumn(name = "userId")
public class Guest extends User {
	@Column(name="plus_one", unique = true, nullable = false)
	private boolean plusOne;
	

	public Guest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Guest(int id, String username, Wedding wedding) {
		super(id, username, wedding);
		// TODO Auto-generated constructor stub
	}

	public Guest(String username, Wedding wedding) {
		super(username, wedding);
		// TODO Auto-generated constructor stub
	}
		
	public Guest(Boolean plusOne) {
		super();
		this.plusOne = plusOne;
	}

	public boolean isPlusOne() {
		return plusOne;
	}

	public void setPlusOne(boolean plusOne) {
		this.plusOne = plusOne;
	}


}
