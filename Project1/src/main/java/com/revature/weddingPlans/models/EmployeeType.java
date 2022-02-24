package com.revature.weddingPlans.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="employee_types")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class EmployeeType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="emptype_id")
	private int id;
	
	@Column(name="emptype_name")
	private String employeeTypeName;
	
	
//	@JsonManagedReference
	@OneToMany(mappedBy="employeeType", fetch=FetchType.EAGER)
	private List<Employee> employees;

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setGuests(List<Employee> employees) {
		this.employees = employees;
	}
	
	public EmployeeType() {
		super();
	}

	public EmployeeType(String employeeTypeName) {
		super();
		this.employeeTypeName = employeeTypeName;
	}



	public EmployeeType(int id, String employeeTypeName) {
		super();
		this.id = id;
		this.employeeTypeName = employeeTypeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeTypeName() {
		return employeeTypeName;
	}

	public void setEmployeeTypeName(String employeeTypeName) {
		this.employeeTypeName = employeeTypeName;
	}


	
	
	
}
