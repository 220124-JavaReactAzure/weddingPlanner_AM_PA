package com.revature.weddingPlans.models;

<<<<<<< HEAD

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Employee extends User {
	private int empNum;
=======
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
	
>>>>>>> 28dd4dcc875bf62ac832a2f9e4bb6d25f4a453ca

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
<<<<<<< HEAD

	public int getEmpNum() {
		return empNum;
	}

	public void setEmpNum(int empNum) {
		this.empNum = empNum;
	}
	
	
=======
		
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
>>>>>>> 28dd4dcc875bf62ac832a2f9e4bb6d25f4a453ca
}
