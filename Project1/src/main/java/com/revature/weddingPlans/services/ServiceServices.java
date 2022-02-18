package com.revature.weddingPlans.services;

import java.util.List;

import com.revature.weddingPlans.Daos.ServiceDAO;
import com.revature.weddingPlans.models.Service;

public class ServiceServices {

	private final ServiceDAO serviceDAO;
	
	public ServiceServices(ServiceDAO serviceDAO) {
		this.serviceDAO = serviceDAO;
	}
	
	public void insertService(Service service) {
		serviceDAO.insertService(service);
	}
	
	public boolean addService(Service service) {
		
		return serviceDAO.addService(service);
	}
	
	public List<Service> getAllServices(){
		return serviceDAO.getAllServices();
		
	}
	
	public Service getServiceById(int id){
		
		return serviceDAO.getServiceById(id);
	}
	
	
	public void updateServiceWithSessionMethod(Service service) {
		
		serviceDAO.updateServiceWithSessionMethod(service);
	}
	
	// public void updateServiceWithHQL(Service service) {
		
	// 	serviceDAO.updateServiceWithHQL(service);
	// }
	
	
}

