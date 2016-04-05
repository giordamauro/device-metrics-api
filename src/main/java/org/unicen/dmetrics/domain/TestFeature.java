package org.unicen.dmetrics.domain;

import java.util.Date;
import java.util.Objects;

import org.unicen.dmetrics.firebase.annotation.Key;

public class TestFeature {
	
	@Key
	private final String name;
	
	private final String value;

	private final Date createdOn;

	private String createdBy;

	@SuppressWarnings("unused")
	private TestFeature() {

		this.name = null;
		this.value = null;
		this.createdOn = null;
	}

	public TestFeature(String category, String name, String value) {

		Objects.requireNonNull(category, "Category cannot be null");
		Objects.requireNonNull(name, "Name cannot be null");
		Objects.requireNonNull(value, "Value cannot be null");

		this.name = name;
		this.createdOn = new Date();
		this.value = value;
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
	public String toString() {
		return "TestFeature [name=" + name + ", value=" + value + ", createdOn=" + createdOn + ", createdBy=" + createdBy + "]";
	}
}
