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
import com.revature.weddingPlans.models.Service;
import com.revature.weddingPlans.models.User;
import com.revature.weddingPlans.models.Wedding;
import com.revature.weddingPlans.services.ServiceServices;
import com.revature.weddingPlans.services.WeddingServices;

public class WeddingServlet extends HttpServlet{

	private final WeddingServices weddingServices;
	private final ServiceServices serviceServices;
	
	private final ObjectMapper mapper;
	
	public WeddingServlet(WeddingServices weddingServices, ServiceServices serviceServices, ObjectMapper mapper) {
		this.weddingServices = weddingServices;
		this.serviceServices = serviceServices;
		this.mapper = mapper;
	}
	
	// RCUD - order
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Switch statements are back sorry
		PrintWriter writer = resp.getWriter();
		// Obtains everything after the /weddings
		String path = req.getPathInfo();
		if(path == null) path = "";
		switch(path) {
		case "/ID":
			try {
				String idParam = req.getParameter("weddingId");
				if(idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the query ?weddingId=# in your url");
					return;
				}
				
				int weddingId = Integer.valueOf(idParam);
				
			
				Wedding wedding = weddingServices.getWeddingById(weddingId);
				if(wedding == null) {
					resp.setStatus(500);
					return;
				}
				String payload = mapper.writeValueAsString(wedding);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}
			break;
		default:
			List<Wedding> weddings = weddingServices.getAllWeddings();
			String payload = mapper.writeValueAsString(weddings);
			writer.write(payload);
			resp.setStatus(200);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		try {
			Wedding newWedding = mapper.readValue(req.getInputStream(), Wedding.class);
			
			int service_id = newWedding.getService().getId();
			Service service = serviceServices.getServiceById(Integer.valueOf(service_id));
			System.out.println(service);
			newWedding.setService(service);
			
			boolean wasReg = weddingServices.addWedding(newWedding);
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
			Wedding updatedWedding = mapper.readValue(req.getInputStream(), Wedding.class);
			//weddingServices.updateWeddingWithHQL(updatedWedding);

			int service_id = updatedWedding.getService().getId();
			Service service = serviceServices.getServiceById(Integer.valueOf(service_id));
			System.out.println(service);
			updatedWedding.setService(service);
			
			weddingServices.updateWeddingWithSessionMethod(updatedWedding);
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
