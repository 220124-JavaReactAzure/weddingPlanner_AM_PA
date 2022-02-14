package com.revature.weddingPlans;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MainDriver extends HttpServlet {
	
	private final ObjectMapper mapper;

	public MainDriver(ObjectMapper mapper) {
//		this.userService = userService;
		this.mapper = mapper;
	}

	@Override
	public void init() throws ServletException {
		System.out.println("Init has been called for TestServlet");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		resp.getWriter().write("<h1>" + firstName + " " + lastName + "</h1>");
	}

	@Override
	public void destroy() {
		System.out.println("TestServlet Destroyer");
	}

}
