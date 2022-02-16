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

}
