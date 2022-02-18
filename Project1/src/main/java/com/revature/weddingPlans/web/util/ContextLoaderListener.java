package com.revature.weddingPlans.web.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.revature.weddingPlans.Daos.ServiceDAO;
import com.revature.weddingPlans.Daos.UserDAO;
import com.revature.weddingPlans.Daos.WeddingDAO;
import com.revature.weddingPlans.services.ServiceServices;
import com.revature.weddingPlans.services.UserServices;
import com.revature.weddingPlans.services.WeddingServices;
import com.revature.weddingPlans.web.servlets.ServiceServlet;
import com.revature.weddingPlans.web.servlets.UserServlet;
import com.revature.weddingPlans.web.servlets.WeddingServlet;

@WebListener
public class ContextLoaderListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate5Module());

		ServletContext context = sce.getServletContext();
		
		WeddingDAO weddingDAO = new WeddingDAO();
		WeddingServices weddingServices = new WeddingServices(weddingDAO);
		WeddingServlet weddingServlet = new WeddingServlet(weddingServices, mapper);

		context.addServlet("WeddingServlet", weddingServlet).addMapping("/weddings/*");
		
		
		UserDAO userDAO = new UserDAO();
		UserServices userServices = new UserServices(userDAO);
		UserServlet userServlet = new UserServlet(userServices, weddingServices,  mapper);
		
		context.addServlet("UserServlet", userServlet).addMapping("/users/*");		
		
		ServiceDAO serviceDAO = new ServiceDAO();
		ServiceServices serviceServices = new ServiceServices(serviceDAO);
		ServiceServlet serviceServlet = new ServiceServlet(serviceServices, mapper);
		
		context.addServlet("ServiceServlet", serviceServlet).addMapping("/services/*");
		

		

}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}
	
	
}
