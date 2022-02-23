package com.revature.weddingPlans.Daos;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.weddingPlans.models.EmployeeType;
import com.revature.weddingPlans.util.HibernateUtil;

import java.util.List;

public class EmployeeTypeDAO {

	public void insertEmployeeType(EmployeeType employeeType) {

		try {
			Session session = HibernateUtil.getSession();
			session.save(employeeType);
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public boolean addEmployeeType(EmployeeType employeeType) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(employeeType);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public List<EmployeeType> getAllEmployeeTypes(){
		try {
			Session session = HibernateUtil.getSession();
			List<EmployeeType> employeeTypes = session.createQuery("FROM EmployeeType").list();
			return employeeTypes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public EmployeeType getEmployeeTypeById(int id) {
		try {
			Session session = HibernateUtil.getSession();
			EmployeeType employeeType = session.get(EmployeeType.class, id);
			return employeeType;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public void updateEmployeeTypeWithSessionMethod(EmployeeType employeeType) {
		try {
			Session session = HibernateUtil.getSession();
			// Updates and Deletes always start with a transaction and end with a commit
			Transaction transaction = session.beginTransaction();
			session.merge(employeeType);
			transaction.commit();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

}











