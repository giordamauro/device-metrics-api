package org.unicen.dmetrics.domain;

import java.util.Set;

import org.unicen.dmetrics.firebase.annotation.Key;
import org.unicen.dmetrics.firebase.annotation.SetWrapper;

/**
 * 
 */
@SetWrapper
public class TestCategory {

	@Key
	private final String name;

	private Set<TestFeature> features;

	private TestCategory() {
		this.name = null;
	}

	public Set<TestFeature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<TestFeature> features) {
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
