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
@DiscriminatorValue("1")
@PrimaryKeyJoinColumn(name = "userId")
public class Employee extends User {
	@Column(name="employee_number", unique = true, nullable = false)
	private String empNum;
	
	private Double price;
	
		
//	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "emptype_id") 
	public EmployeeType employeeType;
	
	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int id, String username, Wedding wedding) {
		super(id, username, wedding);
		// TODO Auto-generated constructor stub
	}

	public Employee(String username, Wedding wedding) {
		super(username, wedding);
		// TODO Auto-generated constructor stub
	}
	public Employee(String empNum) {
		super();
		this.empNum = empNum;
	}

	public String getEmpNum() {
		return empNum;
	}

	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}
}
