package org.unicen.dmetrics.domain;

import java.util.Date;
import java.util.Objects;

public class DeviceFeature {

	private final String category;

    private final String name;

    private final String value;
    
    
    private final Date createdOn;
    
    private String createdBy;

    
    @SuppressWarnings("unused")
	private DeviceFeature() {
    
    	this.category = null;
    	this.name = null;
    	this.value = null;
    	this.createdOn = null;
    }
    
    public DeviceFeature(String category, String name, String value) {

        Objects.requireNonNull(category, "Category cannot be null");
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(value, "Value cannot be null");
        
        this.createdOn = new Date();
        this.category = category;
        this.name = name;
        this.value = value;
    }

	public String getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeviceFeature other = (DeviceFeature) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DeviceFeature [category=" + category + ", name=" + name + ", value=" + value
				+ ", createdOn=" + createdOn + ", createdBy=" + createdBy + "]";
	}
}
