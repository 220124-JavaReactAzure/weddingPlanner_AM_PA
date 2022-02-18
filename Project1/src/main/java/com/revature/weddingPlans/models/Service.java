package com.revature.weddingPlans.models;

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

	@Override
	public String toString() {
		return "Service [id=" + id + ", serviceName=" + serviceName + ", serviceCost=" + serviceCost + ", serviceType="
				+ serviceType + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, serviceCost, serviceName, serviceType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Service other = (Service) obj;
		return id == other.id && Double.doubleToLongBits(serviceCost) == Double.doubleToLongBits(other.serviceCost)
				&& Objects.equals(serviceName, other.serviceName) && serviceType == other.serviceType;
	}








	
}
