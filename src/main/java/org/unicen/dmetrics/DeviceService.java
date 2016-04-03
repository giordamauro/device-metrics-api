package org.unicen.dmetrics;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unicen.dmetrics.domain.TestDevice;
import org.unicen.dmetrics.firebase.core.FirebaseTemplate;

@Service
public class DeviceService {

	@Autowired
	private FirebaseTemplate firebaseTemplate;

	@PostConstruct
	public void init() {

		Optional<TestDevice> device = firebaseTemplate.findByKey(TestDevice.class, "galaxy-pop");
		System.out.println(device);
	}
}