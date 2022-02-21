package com.revature.weddingPlans.services;

import java.util.List;

import com.revature.weddingPlans.Daos.GuestDAO;
import com.revature.weddingPlans.models.Guest;

public class GuestServices {

	private final GuestDAO guestDAO;
	
	public GuestServices(GuestDAO guestDAO) {
		this.guestDAO = guestDAO;
	}
	
	public void insertGuest(Guest guest) {
		guestDAO.insertGuest(guest);
	}
	
	public boolean addGuest(Guest guest) {
		
		return guestDAO.addGuest(guest);
	}
	
	public List<Guest> getAllGuests(){
		return guestDAO.getAllGuests();
		
	}
	
	public Guest getGuestById(int id){
		
		return guestDAO.getGuestById(id);
	}
	
	
	public void updateGuestWithSessionMethod(Guest guest) {
		
		guestDAO.updateGuestWithSessionMethod(guest);
	}
	
	// public void updateGuestWithHQL(Guest guest) {
		
	// 	guestDAO.updateGuestWithHQL(guest);
	// }
	
	
}

