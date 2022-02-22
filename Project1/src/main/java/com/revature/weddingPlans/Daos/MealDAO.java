package com.revature.weddingPlans.Daos;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.weddingPlans.models.MealType;
import com.revature.weddingPlans.util.HibernateUtil;

import java.util.List;

public class MealDAO {

	public void insertMealType(MealType meal) {

		try {
			Session session = HibernateUtil.getSession();
			session.save(meal);
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public boolean addMealType(MealType meal) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(meal);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public List<MealType> getAllMeals(){
		try {
			Session session = HibernateUtil.getSession();
			List<MealType> meals = session.createQuery("FROM MealType").list();
			return meals;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public MealType getMealById(int id) {
		try {
			Session session = HibernateUtil.getSession();
			MealType meal = session.get(MealType.class, id);
			return meal;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public void updateMealTypeWithSessionMethod(MealType meal) {
		try {
			Session session = HibernateUtil.getSession();
			// Updates and Deletes always start with a transaction and end with a commit
			Transaction transaction = session.beginTransaction();
			session.merge(meal);
			transaction.commit();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

}











