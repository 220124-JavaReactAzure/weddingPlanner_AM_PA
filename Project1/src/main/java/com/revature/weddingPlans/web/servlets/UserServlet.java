package com.revature.weddingPlans.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
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
//		PrintWriter writer = resp.getWriter();
//		String path = req.getPathInfo();
		System.out.println("doGet");
		resp.getWriter().write("<h1>Test Servlet is working for Monster Lab</h1>");
		
		// Switch statements are back sorry
		PrintWriter writer = resp.getWriter();
		// Obtains everything after the /Users
		String path = req.getPathInfo();
		if(path == null) path = "";
		switch(path) {
		case "/ID":
			try {
				String idParam = req.getParameter("UserId");
				if(idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the query ?UserId=# in your url");
					return;
				}
				
				int UserId = Integer.valueOf(idParam);
				
			
				User User = userServices.getUserById(UserId);
				if(User == null) {
					resp.setStatus(500);
					return;
				}
				String payload = mapper.writeValueAsString(User);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}
			break;
		default:
			List<User> Users = userServices.getAllUsers();
			String payload = mapper.writeValueAsString(Users);
			writer.write(payload);
			resp.setStatus(200);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		try {
			User newUser = mapper.readValue(req.getInputStream(), User.class);
			System.out.println(newUser);
			boolean wasReg = userServices.addUser(newUser);
			if(wasReg) {
				resp.setStatus(201);
			} else {
				resp.setStatus(500);
				resp.getWriter().write("Database did not persist");
			}
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception did not persist");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			User updatedUser = mapper.readValue(req.getInputStream(), User.class);
			//UserServices.updateUserWithHQL(updatedUser);
			userServices.updateUserWithSessionMethod(updatedUser);
			resp.setStatus(204);	
		} catch (StreamReadException | DatabindException e) {
			resp.setStatus(400);
			resp.getWriter().write("JSON threw exception");
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("Some other random exception did not persist");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}
	
}
