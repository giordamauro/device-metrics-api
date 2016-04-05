package org.unicen.dmetrics.firebase.core;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.unicen.dmetrics.firebase.annotation.Key;
import org.unicen.dmetrics.firebase.annotation.Path;
import org.unicen.dmetrics.firebase.annotation.PathKey;
import org.unicen.dmetrics.firebase.annotation.SetWrapper;

@Component
public class FirebaseAnnotationProcessor {

	public String getPathUrl(Class<?> entityClass) {
		
		String pathValue = Optional.of(entityClass)
			.map(firebaseClass -> AnnotationUtils.findAnnotation(entityClass, Path.class))
			.map(pathAnnotation -> pathAnnotation.value())
		  .orElse("/");
	
		return pathValue;
	}
	
	public Field getKeyField(Class<?> entityClass) {
	
		List<Field> keyFields = Arrays.stream(entityClass.getDeclaredFields())
			.filter(field -> AnnotationUtils.findAnnotation(field, Key.class) != null)
			.limit(2)
			.collect(Collectors.toList());
		
		if(keyFields.size() == 0){
			throw new IllegalStateException(String.format("Class %s must be annotated with @Key annotation", entityClass));
		}
		else if(keyFields.size() > 1){
			throw new IllegalStateException(String.format("Class %s can be annotated with @Key only once", entityClass));
		}

		return keyFields.get(0);
	}
	
	public Map<String, Field> getPathKeyFields(Class<?> entityClass) {
		
		Objects.requireNonNull(entityClass);
		
		Map<String, Field> keyFields = new HashMap<>();
		
		Field[] fields = entityClass.getDeclaredFields();
		Arrays.stream(fields)
			.forEach(field -> {
				
				PathKey keyAnnotation = AnnotationUtils.findAnnotation(field, PathKey.class);
				if(keyAnnotation != null){
					
					String keyName = keyAnnotation.value();
					if(keyName.isEmpty()){
						keyName = field.getName();
					}
					
					keyFields.put(keyName, field);
				}
			});
			
		return keyFields;
	}
	
	public Optional<Field> getSetWrapperField(Class<?> entityClass) {

		Set<Field> setWrapperFields = Arrays.stream(entityClass.getDeclaredFields())
			.filter(field -> AnnotationUtils.findAnnotation(field, SetWrapper.class) != null)
			.collect(Collectors.toSet());
		
		if(setWrapperFields.size() > 1) {
			throw new IllegalStateException(String.format("Only one field can be annotated as @SetWrapper in Class %s", entityClass));
		}

		return setWrapperFields.isEmpty() ? Optional.empty() : Optional.of(setWrapperFields.iterator().next());
	}

}
