package org.unicen.dmetrics.domain;

import java.util.Date;
import java.util.Map;
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

    private Map<String, Set<TestFeature>> features;

    private Set<TestFeature> newFeatures;
    
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
	
	public Map<String, Set<TestFeature>> getFeatures() {
		return features;
	}

	public void setFeatures(Map<String, Set<TestFeature>> features) {
		this.features = features;
	}

	public Set<TestFeature> getNewFeatures() {
		return newFeatures;
	}

	public void setNewFeatures(Set<TestFeature> newFeatures) {
		this.newFeatures = newFeatures;
	}

	@Override
	public String toString() {
		return "TestDevice [model=" + model + ", createdOn=" + createdOn + ", createdBy=" + createdBy + ", name=" + name
				+ ", features=" + features + ", newFeatures=" + newFeatures + "]";
	}
}
