package org.unicen.dmetrics.firebase.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FirebaseTemplate {
	
	@Autowired
	private FirebaseAnnotationProcessor annotationProcessor;

	@Autowired
	private ReflectionHelper reflectionHelper;

	@Autowired
	private FirebasePlaceholdersProcessor placeholdersProcessor;

	private final FirebaseConfiguration config;
	private final RestTemplate restTemplate;
	
	@Autowired
	public FirebaseTemplate(FirebaseConfiguration config) {
		this.config = config;
		this.restTemplate = new RestTemplate();
		
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = converter.getObjectMapper();
		objectMapper.setPropertyNamingStrategy(new CamelCaseNamingStrategy());
	    
		restTemplate.setMessageConverters(Collections.singletonList(converter));
	}

	public <T> Optional<T> findByKey(Class<T> domainClass, String key){

		String entityPath = annotationProcessor.getPathUrl(domainClass);
		
		List<String> urlPlaceholders = placeholdersProcessor.getUrlPlaceholders(entityPath);
		if(!urlPlaceholders.isEmpty()){
			throw new IllegalStateException(String.format("Find by Key in class %s contains Url placeholders", domainClass));
		}
		
		Field keyField = annotationProcessor.getKeyField(domainClass);

		String url = config.getHost() + entityPath + "/{key}.json";
		T entity = restTemplate.getForObject(url, domainClass, key);
		
		Optional<T> result = Optional.ofNullable(entity);
		result.ifPresent(instance -> reflectionHelper.setPrivateField(instance, keyField, key));

		return result;
	}

	public <T> Iterable<T> findAll(Class<T> domainClass){

		List<T> list = new ArrayList<>();

		String entityPath = annotationProcessor.getPathUrl(domainClass);
		List<String> urlPlaceholders = placeholdersProcessor.getUrlPlaceholders(entityPath);
		if(!urlPlaceholders.isEmpty()){
			throw new IllegalStateException(String.format("Find by Key in class %s contains Url placeholders", domainClass));
		}
		
		String url = config.getHost() + entityPath + ".json";
		
//		Map<String, T> entity = restTemplate.getForObject(url, domainClass);
		
		return list;
	}
}
