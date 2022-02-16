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
			List<User> users = session.createQuery("FROM User").list();
			return users;
		} catch (Exception e) {
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
}
