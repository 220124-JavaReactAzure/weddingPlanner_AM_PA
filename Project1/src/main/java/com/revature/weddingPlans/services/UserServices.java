package com.revature.weddingPlans.services;

import java.util.List;

import com.revature.weddingPlans.Daos.UserDAO;
import com.revature.weddingPlans.models.User;

public class UserServices {

private final UserDAO userDAO;
	
	public UserServices(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public boolean addUser(User user) {
		
		return userDAO.addUser(user);
	}
	
	public List<User> getAllUsers(){
		return userDAO.getAllUsers();
		
	}
	
//	public User getUserById(int id){
//		
//		return userDAO.getUserById(id);
//	}
	
	
	public void updateUserWithSessionMethod(User user) {
		
		userDAO.updateUserWithSessionMethod(user);
	}
	
	// public void updateUserWithHQL(User user) {
		
	// 	userDAO.updateUserWithHQL(user);
	// }
	
	
}

