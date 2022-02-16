package com.revature.weddingPlans.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.weddingPlans.models.User;
import com.revature.weddingPlans.services.UserServices;

public class UserServlet extends HttpServlet {
	
	private final UserServices userServices;
	private final ObjectMapper mapper;
	
	public UserServlet(UserServices userServices, ObjectMapper mapper) {
		this.userServices = userServices;
		this.mapper = mapper;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		String path = req.getPathInfo();
		if(path == null) path = "";
		switch(path) {
		case "/ID":
			try {
				String idParam = req.getParameter("userId");
				if(idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the ?userId=# in your url");
					return;
				}
				
				int userId = Integer.valueOf(idParam);
				
				User user = userServices.getUserById(userId);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
