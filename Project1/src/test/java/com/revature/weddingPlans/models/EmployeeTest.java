package com.revature.weddingPlans.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmployeeTest {

	@Test
	public void testInitOfEmployee() {
		Employee emp = new Employee();
		assertFalse(emp.equals(null));
	}
	
	@Test
	public void testForName() {
		Employee emp = new Employee();
		emp.setFullname("James Who");
		assertFalse(emp.getFullname().isEmpty());
	}
}