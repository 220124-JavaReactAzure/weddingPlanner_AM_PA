package com.revature.weddingPlans.Daos;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.weddingPlans.models.Guest;
import com.revature.weddingPlans.util.HibernateUtil;

public class GuestDAO {
	public void insertGuest(Guest guest) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(guest);
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	public boolean addGuest(Guest guest) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(guest);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}finally {
			HibernateUtil.closeSession();
		}
	}
	
	public List<Guest> getAllGuests() {
		try {
			Session session = HibernateUtil.getSession();
			List<Guest> guests = session.createQuery("FROM Guest").list();
			return guests;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}


	public Guest getGuestById(int id) {
		try {
			Session session = HibernateUtil.getSession();
			Guest guest = session.get(Guest.class, id);
			return guest;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public void updateGuestWithSessionMethod(Guest guest) {
		try {
			Session session = HibernateUtil.getSession();
			// Updates and Deletes always start with a transaction and end with a commit
			Transaction transaction = session.beginTransaction();
			session.merge(guest);
			transaction.commit();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

	}
// Not truly implemented
//	public void updateGuestWithHQL(Guest guest) {
//		try {
//			Session session = HibernateUtil.getSession();
//			Transaction transaction = session.beginTransaction();
//			
////			Query query = session.createQuery("update Guest set email='" + Guest.getEmail() + 
////					"', first_name='" + Guest.getFirstName() + 
////					"', last_name='" + Guest.getLastName() + 
////					"', year_born=" + Guest.getYearBorn() +
////					" WHERE Guest_id=" + Guest.getId());
//			
//			Query query = session.createQuery("update Guest set email= :email, first_name= :firstName, last_name= :lastName, year_born=:yearBorn where Guest_id = :id");
//			query.setParameter("email", guest.getEmail());
//			query.executeUpdate();
//			transaction.commit();
//		} catch (HibernateException | IOException e) {
//			e.printStackTrace();
//		} finally {
//			HibernateUtil.closeSession();
//		}
//		
//	}

	public void deleteGuest(int id) {
		try {
			Session session = HibernateUtil.getSession();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

}
