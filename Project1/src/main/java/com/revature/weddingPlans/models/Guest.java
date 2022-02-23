package com.revature.weddingPlans.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@DiscriminatorValue("2")
@PrimaryKeyJoinColumn(name = "userId")
public class Guest extends User {
	@Column(name="plus_one", nullable = false)
	private boolean plusOne;
	
	
//	@Column(name="betrothed", nullable = false)
//	private boolean betrothed;
//	
//	public boolean isBetrothed() {
//		return betrothed;
//	}
//
//	public void setBetrothed(boolean betrothed) {
//		this.betrothed = betrothed;
//	}

	private Double price;
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "meal_id") 
	public MealType mealType;
	
	public MealType getMealType() {
		return mealType;
	}

	public void setMealType(MealType mealType) {
		this.mealType = mealType;
	}

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
