package com.revature.weddingPlans.models;

import java.util.List;
import java.util.Objects;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "weddings")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Wedding {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wedding_id")
	private int id;
	
	@Column(name = "wedding_name")
	private String weddingName;
	
	@Column(name = "wedding_date")
	private String weddingDate;
	
	public String getWeddingDate() {
		return weddingDate;
	}

	public void setWeddingDate(String weddingDate) {
		this.weddingDate = weddingDate;
	}

	//	@JsonManagedReference
	@OneToMany(mappedBy="wedding", fetch=FetchType.EAGER)
	private List<User> users;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "service_id") 
	public Service service;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getWeddingName() {
		return weddingName;
	}

	public void setWeddingName(String weddingName) {
		this.weddingName = weddingName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	
	
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}






	

}
