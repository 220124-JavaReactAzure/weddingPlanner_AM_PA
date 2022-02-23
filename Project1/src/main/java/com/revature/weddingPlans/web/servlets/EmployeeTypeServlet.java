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
import com.revature.weddingPlans.models.EmployeeType;
import com.revature.weddingPlans.models.Service;
import com.revature.weddingPlans.models.User;
import com.revature.weddingPlans.services.EmployeeTypeServices;
import com.revature.weddingPlans.services.UserServices;

public class EmployeeTypeServlet extends HttpServlet {
	
	private final EmployeeTypeServices employeeTypeServices;
	private final ObjectMapper mapper;
//	private final UserServices userServices;
	


	public EmployeeTypeServlet(EmployeeTypeServices employeeTypeServices, ObjectMapper mapper) {
		super();
		this.employeeTypeServices = employeeTypeServices;
		this.mapper = mapper;
	}

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		String path = req.getPathInfo();
		
		if(path == null) {
			path = "";
		}
		
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
				
				EmployeeType employee = employeeTypeServices.getEmployeeTypeById(userId);
				
				if(employee == null) {
					resp.setStatus(500);
					return;
				}
				
				String payload = mapper.writeValueAsString(employee);
				writer.write(payload);
				resp.setStatus(200);
				
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}
			break;
		default:
			List<EmployeeType> employees = employeeTypeServices.getAllEmployeeTypes();
			String payload = mapper.writeValueAsString(employees);
			writer.write(payload);
			resp.setStatus(200);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		try {
			EmployeeType newEmployeeType = mapper.readValue(req.getInputStream(), EmployeeType.class);
			boolean wasReg = employeeTypeServices.addEmployeeType(newEmployeeType);
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
			EmployeeType updatedEmployeeType = mapper.readValue(req.getInputStream(), EmployeeType.class);
			employeeTypeServices.updateEmployeeTypeWithSessionMethod(updatedEmployeeType);
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