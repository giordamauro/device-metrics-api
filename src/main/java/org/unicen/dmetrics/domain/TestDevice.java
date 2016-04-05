package org.unicen.dmetrics.domain;

import java.util.Date;
import java.util.Set;

import org.unicen.dmetrics.firebase.annotation.Key;
import org.unicen.dmetrics.firebase.annotation.Path;

/**
 * 
 */
@Path("/devices/samsung")
public class TestDevice {

	@Key
    private final String model;

	private Date createdOn;
    
    private String createdBy;

    private String name;

    private Set<TestCategory> features;
    
    @SuppressWarnings("unused")
	private TestDevice(){
    	this.model = null;
    }
    
	public TestDevice(String model, Date createdOn, String createdBy, String name) {
		this.model = model;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.name = name;
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

	public Set<TestCategory> getFeatures() {
		return features;
	}

	public void setFeatures(Set<TestCategory> features) {
		this.features = features;
	}

	@Override
	public String toString() {
		return "TestDevice [model=" + model + ", createdOn=" + createdOn + ", createdBy=" + createdBy + ", name=" + name
				+ ", features=" + features + "]";
	}
}
