package org.unicen.dmetrics.firebase.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import sun.reflect.ReflectionFactory;

@Component
@SuppressWarnings("restriction")
public class ReflectionHelper {

	public Set<Field> findFieldForType(Class<?> classType, Class<?> fieldType) {

		return Arrays.stream(classType.getDeclaredFields())
			.filter(field -> field.getType().equals(fieldType))
			.collect(Collectors.toSet());
	}
	
	public <T> T setPrivateField(T target, Field field, Object value) {

		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		ReflectionUtils.setField(field, target, value);
		field.setAccessible(accessible);
		
		return target;
	}
	
	public <T> T createInstanceSilently(Class<T> classToCreate) {
		return createInstanceSilently(classToCreate, Object.class);
	}
	
	public <T> T createInstanceSilently(Class<T> classToCreate, Class<? super T> parent) {
		try {
			ReflectionFactory rf = ReflectionFactory.getReflectionFactory();
			Constructor<?> objDef = parent.getDeclaredConstructor();
			Constructor<?> intConstr = rf.newConstructorForSerialization(classToCreate, objDef);
			return classToCreate.cast(intConstr.newInstance());
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalStateException("Cannot create object", e);
		}
	}
	
	public <T> T createInstanceByDefaultConstructor(Class<T> classToCreate) {
		
		try {
			Constructor<T> constructor = classToCreate.getDeclaredConstructor();
			boolean accessible = constructor.isAccessible();
			constructor.setAccessible(true);
			
			T newInstance = constructor.newInstance();
			constructor.setAccessible(accessible);
			
			return newInstance;
		}
		catch(Exception e){
			throw new IllegalStateException(e);
		}
	}

}
