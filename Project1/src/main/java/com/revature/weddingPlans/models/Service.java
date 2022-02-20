package com.revature.weddingPlans.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="services")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Service {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="service_id")
	private int id;
	

	@Column(name="service_name")
	private String serviceName;
	

	@Column(name="service_cost")
	private double serviceCost;
	
//	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Column(name="service_type_id")
	private int serviceType;
	
	@OneToMany(mappedBy="service", fetch=FetchType.EAGER)
	private List<Wedding> weddings;

	public List<Wedding> getWeddings() {
		return weddings;
	}

	public void setWeddings(List<Wedding> weddings) {
		this.weddings = weddings;
	}

	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Service(String serviceName, double serviceCost, int serviceType) {
		super();
		this.serviceName = serviceName;
		this.serviceCost = serviceCost;
		this.serviceType = serviceType;
	}

	public Service(int id, String serviceName, double serviceCost, int serviceType) {
		super();
		this.id = id;
		this.serviceName = serviceName;
		this.serviceCost = serviceCost;
		this.serviceType = serviceType;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public double getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(double serviceCost) {
		this.serviceCost = serviceCost;
	}

	public int getServiceType() {
		return serviceType;
	}

	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}








	
}
