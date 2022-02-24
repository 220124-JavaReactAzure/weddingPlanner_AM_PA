package com.revature.weddingPlans.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class GuestTest {

	@Test
	public void test() {
		Guest gst = new Guest();
		assertFalse(gst.equals(null));
	}
	
	@Test
	public void testNameSetter() {
		Guest gst = new Guest();
		gst.setFullname("Tester Testerson");
		assertFalse(gst.getFullname().isEmpty());
	}
}