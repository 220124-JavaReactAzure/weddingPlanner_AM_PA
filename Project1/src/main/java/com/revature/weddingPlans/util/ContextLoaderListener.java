package com.revature.weddingPlans.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.weddingPlans.MainDriver;
import com.revature.weddingPlans.Daos.UserDAO;
import com.revature.weddingPlans.services.UserServices;
import com.revature.weddingPlans.servlets.AuthServlet;
import com.revature.weddingPlans.web.servlets.UserServlet;

@WebListener
public class ContextLoaderListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		ObjectMapper mapper = new ObjectMapper();

		ServletContext context = sce.getServletContext();
		
		UserDAO userDAO = new UserDAO();
		UserServices userServices = new UserServices(userDAO);
		UserServlet userServlet = new UserServlet(UserServices, mapper);
		
		context.addServlet("UserServlet", userServlet).addMapping("/users/*");
		
	
}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}
	
	
}
