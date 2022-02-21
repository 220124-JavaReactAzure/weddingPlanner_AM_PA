//package com.revature.weddingPlans.services;
//
//import java.util.List;
//
//import com.revature.weddingPlans.Daos.MealDAO;
//import com.revature.weddingPlans.models.MealType;
//
//public class MealServices {
//	
//	private final MealDAO mealDAO;
//	
//	public MealServices(MealDAO mealDAO) {
//		this.mealDAO = mealDAO;
//	}
//	
//	public void insertMeal(MealType meal) {
//		mealDAO.insertMealType(meal);
//	}
//	
//	public boolean addMeal(MealType meal) {
//		return mealDAO.addMealType(meal);
//	}
//	
//	public List<MealType> getAllMeals(){
//		return mealDAO.getAllMeals();
//	}
//	
//	public MealType getMealById(int id) {
//		return mealDAO.getMealById(id);
//	}
//	
//	public void updateMealTypeWithSessionMethod(MealType meal) {
//		mealDAO.updateMealTypeWithSessionMethod(meal);
//	}
//
//}
