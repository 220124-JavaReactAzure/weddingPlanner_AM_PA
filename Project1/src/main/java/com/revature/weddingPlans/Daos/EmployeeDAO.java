package com.revature.weddingPlans.Daos;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.weddingPlans.models.Employee;
import com.revature.weddingPlans.util.HibernateUtil;

public class EmployeeDAO {
	public void insertEmployee(Employee employee) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(employee);
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	public boolean addEmployee(Employee employee) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(employee);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}finally {
			HibernateUtil.closeSession();
		}
	}
	
	public List<Employee> getAllEmployees() {
		try {
			Session session = HibernateUtil.getSession();
			List<Employee> employees = session.createQuery("FROM Employee").list();
			return employees;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}


	public Employee getEmployeeById(int id) {
		try {
			Session session = HibernateUtil.getSession();
			Employee employee = session.get(Employee.class, id);
			return employee;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public void updateEmployeeWithSessionMethod(Employee employee) {
		try {
			Session session = HibernateUtil.getSession();
			// Updates and Deletes always start with a transaction and end with a commit
			Transaction transaction = session.beginTransaction();
			session.merge(employee);
			transaction.commit();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

	}
// Not truly implemented
//	public void updateEmployeeWithHQL(Employee employee) {
//		try {
//			Session session = HibernateUtil.getSession();
//			Transaction transaction = session.beginTransaction();
//			
////			Query query = session.createQuery("update Employee set email='" + Employee.getEmail() + 
////					"', first_name='" + Employee.getFirstName() + 
////					"', last_name='" + Employee.getLastName() + 
////					"', year_born=" + Employee.getYearBorn() +
////					" WHERE Employee_id=" + Employee.getId());
//			
//			Query query = session.createQuery("update Employee set email= :email, first_name= :firstName, last_name= :lastName, year_born=:yearBorn where Employee_id = :id");
//			query.setParameter("email", employee.getEmail());
//			query.executeUpdate();
//			transaction.commit();
//		} catch (HibernateException | IOException e) {
//			e.printStackTrace();
//		} finally {
//			HibernateUtil.closeSession();
//		}
//		
//	}

	public void deleteEmployee(int id) {
		try {
			Session session = HibernateUtil.getSession();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

}
