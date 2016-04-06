package org.unicen.dmetrics.firebase.core;

public class FirebasePostResponse {

	private final String name;

	private FirebasePostResponse() {
		this.name = null;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "FirebasePostResponse [name=" + name + "]";
	}
}
