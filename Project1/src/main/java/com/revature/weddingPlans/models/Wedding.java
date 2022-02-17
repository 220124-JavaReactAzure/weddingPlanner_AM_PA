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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
	
	private String description;
	
	@OneToMany(mappedBy="wedding", fetch=FetchType.EAGER)
	private List<User> users;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Wedding [id=" + id + ", description=" + description + ", users=" + users + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, users);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wedding other = (Wedding) obj;
		return Objects.equals(description, other.description) && id == other.id && Objects.equals(users, other.users);
	}
	

}
