package org.unicen.dmetrics.domain;

import java.util.Map;

import org.unicen.dmetrics.firebase.annotation.Key;

/**
 * 
 */
public class TestCategory {

	@Key
	private final String name;

	private Map<String, TestFeature> features;

	@SuppressWarnings("unused")
	private TestCategory() {
		this.name = null;
	}

	public TestCategory(String name, Map<String, TestFeature> features) {
		this.name = name;
		this.features = features;
	}

	public Map<String, TestFeature> getFeatures() {
		return features;
	}

	public void setFeatures(Map<String, TestFeature> features) {
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
