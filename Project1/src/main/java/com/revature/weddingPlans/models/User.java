package com.revature.weddingPlans.models;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.DiscriminatorType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="product_type", 
  discriminatorType = DiscriminatorType.INTEGER)

@Table(name="users")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int id;
	@Column(name="username", unique = true, nullable = false)
	private String username;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "wedding_id") 
	public Wedding wedding;
	
	public User() {
		super();
	}
	
	public User(int id, String username, Wedding wedding) {
		super();
		this.id = id;
		this.username = username;
		this.wedding = wedding;
	}


	public User(String username, Wedding wedding) {
		super();
		this.username = username;
		this.wedding = wedding;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Wedding getWedding() {
		return wedding;
	}


	public void setWedding(Wedding wedding) {
		this.wedding = wedding;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", wedding=" + wedding + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, username, wedding);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return id == other.id && Objects.equals(username, other.username) && Objects.equals(wedding, other.wedding);
	}


	
}