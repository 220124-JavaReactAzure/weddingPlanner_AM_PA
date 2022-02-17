package com.revature.weddingPlans.services;

import java.util.List;

import com.revature.weddingPlans.Daos.WeddingDAO;
import com.revature.weddingPlans.models.Wedding;

public class WeddingServices {
private final WeddingDAO weddingDAO;
	
	public WeddingServices(WeddingDAO weddingDAO) {
		this.weddingDAO = weddingDAO;
	}
	
	public boolean addWedding(Wedding wedding) {
		
		return weddingDAO.addWedding(wedding);
	}
	
	public List<Wedding> getAllWeddings(){
		return weddingDAO.getAllWeddings();
		
	}
	
	public Wedding getWeddingById(int id){
		Wedding wedding = weddingDAO.getWeddingById(id);
		return weddingDAO.getWeddingById(id);
	}
	
	
	public void updateWeddingWithSessionMethod(Wedding wedding) {
		
		weddingDAO.updateWeddingWithSessionMethod(wedding);
	}
	
	public void updateWeddingWithHQL(Wedding wedding) {
		
		weddingDAO.updateWeddingWithHQL(wedding);
	}
	
	
}
