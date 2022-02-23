package com.revature.weddingPlans.services;

import java.util.List;

import com.revature.weddingPlans.Daos.EmployeeTypeDAO;
import com.revature.weddingPlans.models.EmployeeType;

public class EmployeeTypeServices {
	
	private final EmployeeTypeDAO employeeTypeDAO;
	
	public EmployeeTypeServices(EmployeeTypeDAO employeeTypeDAO) {
		this.employeeTypeDAO = employeeTypeDAO;
	}
	
	public void insertEmployeeType(EmployeeType employeeType) {
		employeeTypeDAO.insertEmployeeType(employeeType);
	}
	
	public boolean addEmployeeType(EmployeeType employeeType) {
		return employeeTypeDAO.addEmployeeType(employeeType);
	}
	
	public List<EmployeeType> getAllEmployeeTypes(){
		return employeeTypeDAO.getAllEmployeeTypes();
	}
	
	public EmployeeType getEmployeeTypeById(int id) {
		return employeeTypeDAO.getEmployeeTypeById(id);
	}
	
	public void updateEmployeeTypeWithSessionMethod(EmployeeType employeeType) {
		employeeTypeDAO.updateEmployeeTypeWithSessionMethod(employeeType);
	}

}
