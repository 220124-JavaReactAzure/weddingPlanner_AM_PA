package com.revature.weddingPlans.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.weddingPlans.servlets.AuthServlet;

@WebListener
public class ContextLoaderListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ObjectMapper mapper = new ObjectMapper();
		
		AuthServlet authServlet = new AuthServlet(mapper);
		
		ServletContext context = sce.getServletContext();
		context.addServlet("AuthServlet", authServlet).addMapping("/auth");
}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}
	
	
}
