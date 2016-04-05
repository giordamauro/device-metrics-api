package org.unicen.dmetrics.firebase.json;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

import org.unicen.dmetrics.firebase.core.FirebaseAnnotationProcessor;
import org.unicen.dmetrics.firebase.core.ReflectionHelper;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

public class SetAdapterFactory implements TypeAdapterFactory {

	private final FirebaseAnnotationProcessor annotationProcessor;
	private final ReflectionHelper reflectionHelper;
	
	public SetAdapterFactory(FirebaseAnnotationProcessor annotationProcessor, ReflectionHelper reflectionHelper) {
		this.annotationProcessor = annotationProcessor;
		this.reflectionHelper = reflectionHelper;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

		Class<? super T> typeClass = type.getRawType();
		
		if (Set.class.isAssignableFrom(typeClass)) {
    		Type elementType = ((ParameterizedType) type.getType()).getActualTypeArguments()[0];
    		TypeAdapter<?> elementAdapter = gson.getAdapter(TypeToken.get(elementType));
    
    		return (TypeAdapter<T>) new SetTypeAdapter(elementAdapter, annotationProcessor, reflectionHelper);
		}

		return annotationProcessor.getSetWrapperField(typeClass)
			.map(field -> {
				TypeAdapter<?> elementAdapter = gson.getAdapter(field.getType());
				
				return (TypeAdapter<T>) new SetWrapperTypeAdapter<>(field, elementAdapter, reflectionHelper);
			})
			.orElse(null);
	}
	
	
}