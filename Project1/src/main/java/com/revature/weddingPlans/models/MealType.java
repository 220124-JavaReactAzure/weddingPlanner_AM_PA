package com.revature.weddingPlans.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="meal_types")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class MealType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="meal_id")
	private int id;
	
	@Column(name="meal_type_name")
	private String mealTypeName;
	
	@JsonManagedReference
	@OneToMany(mappedBy="mealType", fetch=FetchType.EAGER)
	private List<Guest> guests;

	public List<Guest> getGuests() {
		return guests;
	}

	public void setGuests(List<Guest> guests) {
		this.guests = guests;
	}

	public MealType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MealType(String mealTypeName) {
		super();
		this.mealTypeName = mealTypeName;
	}

	public MealType(int id, String mealTypeName) {
		super();
		this.id = id;
		this.mealTypeName = mealTypeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMealTypeName() {
		return mealTypeName;
	}

	public void setMealTypeName(String mealTypeName) {
		this.mealTypeName = mealTypeName;
	}




	
}
