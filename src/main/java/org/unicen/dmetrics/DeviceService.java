package org.unicen.dmetrics;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unicen.dmetrics.domain.DeviceModel;
import org.unicen.dmetrics.firebase.core.FirebaseTemplate;

@Service
public class DeviceService {

	@Autowired
	private FirebaseTemplate firebaseTemplate;

	@PostConstruct
	public void init() {

		Map<String, String> keys = new HashMap<>();
		keys.put("brand", "samsung");
		keys.put("model", "galaxy-pop");
		
		Optional<DeviceModel> device = firebaseTemplate.findByKeys(DeviceModel.class, keys);
		System.out.println(device);
	}
}