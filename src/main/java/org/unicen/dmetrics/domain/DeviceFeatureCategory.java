package org.unicen.dmetrics.domain;

import java.util.Set;

import org.unicen.dmetrics.firebase.annotation.Key;
import org.unicen.dmetrics.firebase.annotation.SetWrapper;

/**
 * 
 */
public class DeviceFeatureCategory {

	@Key
	private final String name;

	@SetWrapper
	private Set<DeviceFeature> features;

	private DeviceFeatureCategory() {
		this.name = null;
	}

	public Set<DeviceFeature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<DeviceFeature> features) {
		this.features = features;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "TestCategory [name=" + name + ", features=" + features + "]";
	}
}
