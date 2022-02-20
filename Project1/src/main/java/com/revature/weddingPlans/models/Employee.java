package com.revature.weddingPlans.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("1")
@PrimaryKeyJoinColumn(name = "userId")
public class Employee extends User {
	@Column(name="employee_number", unique = true, nullable = false)
	private String empNum;
	

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
