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
import com.revature.weddingPlans.models.Wedding;
import com.revature.weddingPlans.services.UserServices;
import com.revature.weddingPlans.services.WeddingServices;


public class UserServlet extends HttpServlet{
	
	private final UserServices userServices;
	private final ObjectMapper mapper;
	private final WeddingServices weddingServices;
	
	public UserServlet(UserServices userServices, WeddingServices weddingServices, ObjectMapper mapper) {
		this.userServices = userServices;
		this.weddingServices = weddingServices;
		this.mapper = mapper;
	}
	
	// RCUD - order
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Switch statements are back sorry
		PrintWriter writer = resp.getWriter();
		// Obtains everything after the /users
		String path = req.getPathInfo();
		if(path == null) path = "";
		switch(path) {
		case "/ID":
			try {
				String idParam = req.getParameter("UserId");
				if(idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the query ?userId=# in your url");
					return;
				}
				
				int userId = Integer.valueOf(idParam);				
			
				User user = userServices.getUserById(userId);
				if(user == null) {
					resp.setStatus(500);
					return;
				}
				String payload = mapper.writeValueAsString(user);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}
			break;
		default:
			List<User> users = userServices.getAllUsers();
			String payload = mapper.writeValueAsString(users);
			writer.write(payload);
			resp.setStatus(200);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		try {
			String idParam = req.getParameter("weddingId");
			if(idParam == null) {
				resp.setStatus(400);
				resp.getWriter().write("Please include the query ?weddingId=# in your url");
				return;
			}
			Wedding wedding = weddingServices.getWeddingById(Integer.valueOf(idParam));
			User newUser = mapper.readValue(req.getInputStream(), User.class);
			newUser.setWedding(wedding);
			userServices.insertUser(newUser);
			resp.setStatus(201);
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
			User updateduser = mapper.readValue(req.getInputStream(), User.class);
			userServices.updateUserWithSessionMethod(updateduser);
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
