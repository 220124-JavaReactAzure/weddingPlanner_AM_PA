package com.revature.weddingPlans.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthServlet extends HttpServlet {

//	private final UserService userService;
	private final ObjectMapper mapper;

	public AuthServlet(ObjectMapper mapper) {
//		this.userService = userService;
		this.mapper = mapper;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		resp.getWriter().write("AuthServlet <h1>" + firstName + " " + lastName + "</h1>");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		LoginCredentials loginCreds = mapper.readValue(req.getInputStream(), LoginCredentials.class);
		resp.getWriter().write("<h1>AuthServlet doPost </h1>");

	}
}

