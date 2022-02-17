package com.revature.weddingPlans.Daos;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.weddingPlans.models.Wedding;
import com.revature.weddingPlans.util.HibernateUtil;

public class WeddingDAO {
	public boolean addWedding(Wedding wedding) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(wedding);
			return true;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public List<Wedding> getAllWeddings() {
		try {
			Session session = HibernateUtil.getSession();
			List<Wedding> weddings = session.createQuery("FROM Wedding").list();
			return weddings;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public Wedding getWeddingById(int id) {
		try {
			Session session = HibernateUtil.getSession();
			Wedding wedding = session.get(Wedding.class, id);
			return wedding;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public void updateWeddingWithSessionMethod(Wedding wedding) {
		try {
			Session session = HibernateUtil.getSession();
			// Updates and Deletes always start with a transaction and end with a commit
			Transaction transaction = session.beginTransaction();
			session.merge(wedding);
			transaction.commit();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

	}
// Not truly implemented
	public void updateWeddingWithHQL(Wedding wedding) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			
//			Query query = session.createQuery("update Wedding set email='" + wedding.getEmail() + 
//					"', first_name='" + wedding.getFirstName() + 
//					"', last_name='" + wedding.getLastName() + 
//					"', year_born=" + wedding.getYearBorn() +
//					" WHERE wedding_id=" + wedding.getId());
			
			Query query = session.createQuery("update Wedding set email= :email, first_name= :firstName, last_name= :lastName, year_born=:yearBorn where wedding_id = :id");
			//query.setParameter("email", wedding.getEmail());
			query.executeUpdate();
			transaction.commit();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		
	}

	public void deleteWedding(int id) {
		try {
			Session session = HibernateUtil.getSession();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
}

