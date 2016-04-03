package org.unicen.dmetrics.domain;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import org.unicen.dmetrics.firebase.annotation.Key;
import org.unicen.dmetrics.firebase.annotation.Path;

/**
 * 
 */
@Path("/devices/{brand}")
public class DeviceModel {

	@Key
    private final String brand;

	@Key
    private final String model;

    private final Date createdOn;
    
    private final String createdBy;
    

    private String name;

//    private Set<DeviceFeature> features = new HashSet<>();    
  
        
    private DeviceModel(Builder builder) {

        Objects.requireNonNull(builder.brand, "Brand cannot be null");
        Objects.requireNonNull(builder.model, "Model cannot be null");
        Objects.requireNonNull(builder.createdBy, "CreatedBy cannot be null");
        
        this.createdOn = new Date();
        
        this.brand = builder.brand;
        this.model = builder.model;
        this.createdBy = builder.createdBy;
        this.name = builder.name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void addFeatures(Set<DeviceFeature> features) {
//    	
//    	features.forEach(feature -> feature.setModel(this));
//
//    	this.features.addAll(features);
//    	this.featuresCount = features.size();
//	}
//    	
//	public Set<DeviceFeature> getFeatures() {
//		return features;
//	}

	public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }
    
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }
        
	public Date getCreatedOn() {
		return createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		DeviceModel other = (DeviceModel) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DeviceModel [brand=" + brand + ", model=" + model + ", createdOn=" + createdOn
				+ ", createdBy=" + createdBy + ", name=" + name + "]";//", features=" + features + "]";
	}

	public static class Builder {
    	
    	private String brand;
        private String model;
        private String createdBy;
        private String name;

		public Builder setBrand(String brand) {
			
			Objects.requireNonNull(brand);
			this.brand = brand;

			return this;
		}

		public Builder setModel(String model) {
			
			Objects.requireNonNull(model);
			this.model = model;

			return this;
		}

		public Builder setCreatedBy(String createdBy) {
			
			Objects.requireNonNull(createdBy);
			this.createdBy = createdBy;
			
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			
			return this;
		}
    	
		public DeviceModel build() {
			return new DeviceModel(this);
		}
    }
}
