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
import com.revature.weddingPlans.models.Guest;
import com.revature.weddingPlans.models.Wedding;
import com.revature.weddingPlans.services.GuestServices;
import com.revature.weddingPlans.services.WeddingServices;




public class GuestServlet extends HttpServlet{
	
	private final GuestServices guestServices;
	private final ObjectMapper mapper;
	private final WeddingServices weddingServices;
	
	public GuestServlet(GuestServices guestServices, WeddingServices weddingServices, ObjectMapper mapper) {
		this.guestServices = guestServices;
		this.weddingServices = weddingServices;
		this.mapper = mapper;
	}
	
	// RCUD - order
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Switch statements are back sorry
		PrintWriter writer = resp.getWriter();
		// Obtains everything after the /guests
		String path = req.getPathInfo();
		if(path == null) path = "";
		switch(path) {
		case "/ID":
			try {
				String idParam = req.getParameter("GuestId");
				if(idParam == null) {
					resp.setStatus(400);
					writer.write("Please include the query ?guestId=# in your url");
					return;
				}
				
				int guestId = Integer.valueOf(idParam);
				
			
				Guest guest = guestServices.getGuestById(guestId);
				if(guest == null) {
					resp.setStatus(500);
					return;
				}
				String payload = mapper.writeValueAsString(guest);
				writer.write(payload);
				resp.setStatus(200);
			} catch (StreamReadException | DatabindException e) {
				resp.setStatus(400);
			}
			break;
		default:
			List<Guest> guests = guestServices.getAllGuests();
			String payload = mapper.writeValueAsString(guests);
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
			Guest newGuest = mapper.readValue(req.getInputStream(), Guest.class);
			newGuest.setWedding(wedding);
			guestServices.insertGuest(newGuest);

			wedding.setWeddingBudget(wedding.getWeddingBudget() + newGuest.getPrice());
			weddingServices.updateWeddingWithSessionMethod(wedding);
			
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
			Guest updatedguest = mapper.readValue(req.getInputStream(), Guest.class);
			guestServices.updateGuestWithSessionMethod(updatedguest);
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
