package com.revature.weddingPlans.Daos;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.weddingPlans.models.User;
import com.revature.weddingPlans.util.HibernateUtil;

public class UserDAO {
	
	public boolean addUser(User user) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}finally {
			HibernateUtil.closeSession();
		}
	}

	public List<User> getAllUsers() {
		try {
			Session session = HibernateUtil.getSession();
			List<User> users = session.createQuery("FROM Users").list();
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public User getUserById(int id) {
		try {
			Session session = HibernateUtil.getSession();
			User user = session.get(User.class, id);
			return user;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public void updateUserWithSessionMethod(User user) {
		try {
			Session session = HibernateUtil.getSession();
			// Updates and Deletes always start with a transaction and end with a commit
			Transaction transaction = session.beginTransaction();
			session.merge(user);
			transaction.commit();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

	}
// Not truly implemented
	public void updateUserWithHQL(User user) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			
//			Query query = session.createQuery("update User set email='" + User.getEmail() + 
//					"', first_name='" + User.getFirstName() + 
//					"', last_name='" + User.getLastName() + 
//					"', year_born=" + User.getYearBorn() +
//					" WHERE User_id=" + User.getId());
			
			Query query = session.createQuery("update User set email= :email, first_name= :firstName, last_name= :lastName, year_born=:yearBorn where User_id = :id");
			query.setParameter("email", user.getEmail());
			query.executeUpdate();
			transaction.commit();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		
	}

	public void deleteUser(int id) {
		try {
			Session session = HibernateUtil.getSession();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

}
