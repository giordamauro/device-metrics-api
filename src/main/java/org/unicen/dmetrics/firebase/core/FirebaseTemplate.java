package org.unicen.dmetrics.firebase.core;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.unicen.dmetrics.firebase.json.DateDeserializer;
import org.unicen.dmetrics.firebase.json.SetAdapterFactory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class FirebaseTemplate {
	
	@Autowired
	private FirebaseAnnotationProcessor annotationProcessor;

	@Autowired
	private FirebaseEntityHelper entityHelper;

	@Autowired
	private ReflectionHelper reflectionHelper;
	
	private final FirebaseConfiguration config;
	private final RestTemplate restTemplate;
	private Gson gson;
		
	@Autowired
	public FirebaseTemplate(FirebaseConfiguration config) {
		this.config = config;
		this.restTemplate = new RestTemplate();
	}
	
	@PostConstruct
	public void init() {

		GsonBuilder builder = new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
				.setDateFormat(DateFormat.LONG)
				.registerTypeAdapter(Date.class, new DateDeserializer())
				.registerTypeAdapterFactory(new SetAdapterFactory(annotationProcessor, reflectionHelper));
		
	    this.gson = builder.create();
	}

	public <T> Optional<T> findByKey(Class<T> domainClass, String key){

		String entityPath = entityHelper.getEntityUrl(domainClass);
		
		Entry<String, Field> keyField = annotationProcessor.getKeyField(domainClass);

		String url = config.getHost() + entityPath + "/{key}.json";
		String json = restTemplate.getForObject(url, String.class, key);

		Optional<T> result = Optional.ofNullable(json)
				.map(jsonString -> gson.fromJson(jsonString, domainClass));
		result.ifPresent(instance -> reflectionHelper.setPrivateField(instance, keyField.getValue(), key));

		return result;
	}
	
	public <T> Optional<T> findByKeys(Class<T> domainClass, Map<String, String> keys){

		String entityPath = annotationProcessor.getPathUrl(domainClass);
		
		List<String> urlPlaceholders = entityHelper.getUrlPlaceholders(entityPath);
		for(String placeholder : urlPlaceholders){

			String value = keys.get(placeholder);
			if(value == null) {
				throw new IllegalStateException(String.format("Placeholder for name %s not found - class %s", placeholder, domainClass));
			}
			entityPath = entityPath.replaceAll("\\{" + placeholder + "\\}", value);
		}
		
		Entry<String, Field> keyField = annotationProcessor.getKeyField(domainClass);
		String keyValue = keys.get(keyField.getKey());
		if(keyValue == null) {
			throw new IllegalStateException(String.format("Key value missing for name %s - class %s", keyField.getKey(), domainClass));
		}
		
		String url = config.getHost() + entityPath + "/{key}.json";
		String json = restTemplate.getForObject(url, String.class, keyValue);

		Optional<T> result = Optional.ofNullable(json)
				.map(jsonString -> gson.fromJson(jsonString, domainClass));
		result.ifPresent(instance -> {

			Map<String, Field> pathKeyFields = annotationProcessor.getPathKeyFields(domainClass);
			
			for(Entry<String, Field> placeholderField : pathKeyFields.entrySet()){
				String placeholderValue = keys.get(placeholderField.getKey());				
				reflectionHelper.setPrivateField(instance, placeholderField.getValue(), placeholderValue);
			}
			reflectionHelper.setPrivateField(instance, keyField.getValue(), keyValue);
		});

		return result;
	}
	
	public String saveByIndexing(Object domainInstance) {
		
		Class<?> domainClass = domainInstance.getClass();
		String entityPath = entityHelper.getEntityUrl(domainClass);
		
		String url = config.getHost() + entityPath + ".json";
		FirebasePostResponse response = restTemplate.postForObject(url, domainInstance, FirebasePostResponse.class);
		String id = response.getName();
		
		// TODO: @Key should be optional 		
		Entry<String, Field> keyField = annotationProcessor.getKeyField(domainClass);
		reflectionHelper.setPrivateField(domainInstance, keyField.getValue(), id);
	
		return id;
	}

	public <T> Iterable<T> findAll(Class<T> domainClass){

		List<T> list = new ArrayList<>();

		String entityPath = annotationProcessor.getPathUrl(domainClass);
		List<String> urlPlaceholders = entityHelper.getUrlPlaceholders(entityPath);
		if(!urlPlaceholders.isEmpty()){
			throw new IllegalStateException(String.format("Find by Key in class %s contains Url placeholders", domainClass));
		}
		
//		String url = config.getHost() + entityPath + ".json";
//		Map<String, T> entity = restTemplate.getForObject(url, domainClass);
		
		return list;
	}
}
