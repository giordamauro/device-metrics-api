package org.unicen.dmetrics.domain;

import java.util.Date;
import java.util.Set;

import org.unicen.dmetrics.firebase.annotation.Key;
import org.unicen.dmetrics.firebase.annotation.Path;
import org.unicen.dmetrics.firebase.annotation.PathKey;

/**
 * 
 */
@Path("/devices/{brand}")
public class DeviceModel {

	@PathKey
    private final String brand;
	
	@Key
    private final String model;

	private Date createdOn;
    
    private String createdBy;

    private String name;

    private Set<DeviceFeatureCategory> features;
    
    @SuppressWarnings("unused")
	private DeviceModel(){
    	this.brand = null;
    	this.model = null;
    }
    
	public DeviceModel(String brand, String model, Date createdOn, String createdBy, String name) {
		this.brand = brand;
		this.model = model;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public Set<DeviceFeatureCategory> getFeatures() {
		return features;
	}

	public void setFeatures(Set<DeviceFeatureCategory> features) {
		this.features = features;
	}

	@Override
	public String toString() {
		return "DeviceModel [brand=" + brand + ", model=" + model + ", createdOn=" + createdOn + ", createdBy="
				+ createdBy + ", name=" + name + ", features=" + features + "]";
	}
}
