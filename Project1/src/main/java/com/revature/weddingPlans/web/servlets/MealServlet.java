//package com.revature.weddingPlans.web.servlets;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.fasterxml.jackson.core.exc.StreamReadException;
//import com.fasterxml.jackson.databind.DatabindException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.revature.weddingPlans.models.MealType;
//import com.revature.weddingPlans.models.User;
//import com.revature.weddingPlans.services.MealServices;
//import com.revature.weddingPlans.services.UserServices;
//
//public class MealServlet extends HttpServlet {
//	
//	private final MealServices mealServices;
//	private final ObjectMapper mapper;
//	private final UserServices userServices;
//	
//	public MealServlet(MealServices mealServices, UserServices userServices, ObjectMapper mapper) {
//		this.mealServices = mealServices;
//		this.userServices = userServices;
//		this.mapper = mapper;
//	}
//	
//	public MealServlet(MealServices mealServices, ObjectMapper mapper) {
//		this.mealServices = mealServices;
//		this.mapper = mapper;
//		this.userServices = null;
//	}
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		PrintWriter writer = resp.getWriter();
//		String path = req.getPathInfo();
//		
//		if(path == null) {
//			path = "";
//		}
//		
//		switch(path) {
//		case "/ID":
//			try {
//				String idParam = req.getParameter("UserId");
//				if(idParam == null) {
//					resp.setStatus(400);
//					writer.write("Please include the query ?userId=# in your url");
//					return;
//				}
//				
//				int userId = Integer.valueOf(idParam);
//				
//				MealType meal = mealServices.getMealById(userId);
//				
//				if(meal == null) {
//					resp.setStatus(500);
//					return;
//				}
//				
//				String payload = mapper.writeValueAsString(meal);
//				writer.write(payload);
//				resp.setStatus(200);
//				
//			} catch (StreamReadException | DatabindException e) {
//				resp.setStatus(400);
//			}
//			break;
//		default:
//			List<MealType> meals = mealServices.getAllMeals();
//			String payload = mapper.writeValueAsString(meals);
//			writer.write(payload);
//			resp.setStatus(200);
//			break;
//		}
//	}
//	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setContentType("application/json");
//		
//		try {
//			String idParam = req.getParameter("userId");
//			
//			if(idParam == null) {
//				resp.setStatus(400);
//				resp.getWriter().write("Please include the query ?userId=# in your url");
//				return;
//			}
//			
//			User user = userServices.getUserById(Integer.valueOf(idParam));
//			MealType newMeal = mapper.readValue(req.getInputStream(), MealType.class);
//			
//			newMeal.setUser(user);
//			mealServices.insertMeal(newMeal);
//			resp.setStatus(201);
//			
//		} catch (StreamReadException | DatabindException e) {
//			resp.setStatus(400);
//			resp.getWriter().write("JSON threw exception");
//			e.printStackTrace();
//		} catch (Exception e) {
//			resp.setStatus(500);
//			resp.getWriter().write("Some other exception didn't persist");
//			e.printStackTrace();
//		}
//	}
//	
//	@Override
//	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		try {
//			MealType updatedMeal = mapper.readValue(req.getInputStream(), MealType.class);
//			mealServices.updateMealTypeWithSessionMethod(updatedMeal);
//			resp.setStatus(204);	
//		} catch (StreamReadException | DatabindException e) {
//			resp.setStatus(400);
//			resp.getWriter().write("JSON threw exception");
//			e.printStackTrace();
//		} catch (Exception e) {
//			resp.setStatus(500);
//			resp.getWriter().write("Some other random exception did not persist");
//			e.printStackTrace();
//		}
//	}
//	
//	@Override
//	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		super.doDelete(req, resp);
//	}
//
//}