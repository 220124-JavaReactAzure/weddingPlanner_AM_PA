package com.revature.weddingPlans.Daos;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.weddingPlans.models.Service;
import com.revature.weddingPlans.util.HibernateUtil;

public class ServiceDAO {
	public void insertService(Service service) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(service);
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	public boolean addService(Service service) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(service);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}finally {
			HibernateUtil.closeSession();
		}
	}
	
	public List<Service> getAllServices() {
		try {
			Session session = HibernateUtil.getSession();
			List<Service> services = session.createQuery("FROM Service").list();
			return services;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}


	public Service getServiceById(int id) {
		try {
			Session session = HibernateUtil.getSession();
			Service service = session.get(Service.class, id);
			return service;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public void updateServiceWithSessionMethod(Service service) {
		try {
			Session session = HibernateUtil.getSession();
			// Updates and Deletes always start with a transaction and end with a commit
			Transaction transaction = session.beginTransaction();
			session.merge(service);
			transaction.commit();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

	}
// Not truly implemented
//	public void updateServiceWithHQL(Service service) {
//		try {
//			Session session = HibernateUtil.getSession();
//			Transaction transaction = session.beginTransaction();
//			
////			Query query = session.createQuery("update Service set email='" + Service.getEmail() + 
////					"', first_name='" + Service.getFirstName() + 
////					"', last_name='" + Service.getLastName() + 
////					"', year_born=" + Service.getYearBorn() +
////					" WHERE Service_id=" + Service.getId());
//			
//			Query query = session.createQuery("update Service set email= :email, first_name= :firstName, last_name= :lastName, year_born=:yearBorn where Service_id = :id");
//			query.setParameter("email", service.getEmail());
//			query.executeUpdate();
//			transaction.commit();
//		} catch (HibernateException | IOException e) {
//			e.printStackTrace();
//		} finally {
//			HibernateUtil.closeSession();
//		}
//		
//	}

	public void deleteService(int id) {
		try {
			Session session = HibernateUtil.getSession();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

}
