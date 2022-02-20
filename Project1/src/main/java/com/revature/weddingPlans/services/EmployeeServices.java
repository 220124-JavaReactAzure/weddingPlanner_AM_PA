package com.revature.weddingPlans.services;

import java.util.List;

import com.revature.weddingPlans.Daos.EmployeeDAO;
import com.revature.weddingPlans.models.Employee;

public class EmployeeServices {

	private final EmployeeDAO employeeDAO;
	
	public EmployeeServices(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	public void insertEmployee(Employee employee) {
		employeeDAO.insertEmployee(employee);
	}
	
	public boolean addEmployee(Employee employee) {
		
		return employeeDAO.addEmployee(employee);
	}
	
	public List<Employee> getAllEmployees(){
		return employeeDAO.getAllEmployees();
		
	}
	
	public Employee getEmployeeById(int id){
		
		return employeeDAO.getEmployeeById(id);
	}
	
	
	public void updateEmployeeWithSessionMethod(Employee employee) {
		
		employeeDAO.updateEmployeeWithSessionMethod(employee);
	}
	
	// public void updateEmployeeWithHQL(Employee employee) {
		
	// 	employeeDAO.updateEmployeeWithHQL(employee);
	// }
	
	
}

