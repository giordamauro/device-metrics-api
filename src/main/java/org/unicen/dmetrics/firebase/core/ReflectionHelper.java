package org.unicen.dmetrics.firebase.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import sun.reflect.ReflectionFactory;

@Component
@SuppressWarnings("restriction")
public class ReflectionHelper {

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

}
