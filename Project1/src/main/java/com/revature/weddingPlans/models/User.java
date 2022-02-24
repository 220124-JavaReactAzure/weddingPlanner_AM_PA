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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity

@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="user_type", 
  discriminatorType = DiscriminatorType.INTEGER)
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
	
	private String fullname;
	private String address;
	private String phone;
	private String email;
	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
//	@JsonBackReference
//	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "wedding_id") 
//	public Wedding wedding;
//	public Wedding getWedding() {
//		return wedding;
//	}
//	public void setWedding(Wedding wedding) {
//		this.wedding = wedding;
//	}
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
    private List<Wedding> weddings = new ArrayList<>();
	
	public List<Wedding> getWeddings() {
		return weddings;
	}
	public void setWeddings(List<Wedding> weddings) {
		this.weddings = weddings;
	}
	public void addWedding(Wedding wedding) {
		weddings.add(wedding);
		wedding.getUsers().add(this);
	}
	public void removeUser(Wedding wedding) {
		weddings.remove(wedding);
		wedding.getUsers().remove(this);
	}

	public User() {
		super();
	}
	

	public User(String username, String fullname, String address, String phone, String email, List<Wedding> weddings) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.weddings = weddings;
	}

	public User(int id, String username, String fullname, String address, String phone, String email,
			List<Wedding> weddings) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.weddings = weddings;
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

	
}