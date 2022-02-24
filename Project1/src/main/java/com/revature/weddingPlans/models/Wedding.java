package com.revature.weddingPlans.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "weddings")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Wedding {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wedding_id")
	private int id;

	@Column(name = "wedding_name")
	private String weddingName;

	@Column(name = "wedding_date")
	private String weddingDate;

	@Column(name = "wedding_budget")
	private Double weddingBudget;

	public String getWeddingDate() {
		return weddingDate;
	}

	public void setWeddingDate(String weddingDate) {
		this.weddingDate = weddingDate;
	}

	// @JsonManagedReference
//	@OneToMany(mappedBy = "wedding", fetch = FetchType.EAGER)
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "wedding_user", 
        joinColumns = { @JoinColumn(name = "wedding_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "user_id") })
	
	private List<User> users = new ArrayList<>();
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void addUser(User user) {
		users.add(user);
//		user.getWeddings().add(this);
	}

	public void removeUser(User user) {
		users.remove(user);
//		user.getWeddings().remove(this);
	}

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

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Double getWeddingBudget() {
		return weddingBudget;
	}

	public void setWeddingBudget(Double weddingBudget) {
		this.weddingBudget = weddingBudget;
	}

	public Wedding() {
		super();
	}

	public Wedding(int id, String weddingName, String weddingDate, Double weddingBudget, List<User> users,
			Service service) {
		super();
		this.id = id;
		this.weddingName = weddingName;
		this.weddingDate = weddingDate;
		this.weddingBudget = weddingBudget;
		this.users = users;
		this.service = service;
	}

	public Wedding(String weddingName, String weddingDate, Double weddingBudget, List<User> users, Service service) {
		super();
		this.weddingName = weddingName;
		this.weddingDate = weddingDate;
		this.weddingBudget = weddingBudget;
		this.users = users;
		this.service = service;
	}
	
	
	

}
