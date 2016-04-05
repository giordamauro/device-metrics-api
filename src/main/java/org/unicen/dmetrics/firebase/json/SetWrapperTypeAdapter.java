package org.unicen.dmetrics.firebase.json;

import java.io.IOException;
import java.lang.reflect.Field;

import org.unicen.dmetrics.firebase.core.ReflectionHelper;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class SetWrapperTypeAdapter<T> extends TypeAdapter<T> {

	private final Field field;
	private final TypeAdapter<?> elementAdapter;
	private final ReflectionHelper reflectionHelper;
	
	public SetWrapperTypeAdapter(Field field, TypeAdapter<?> elementAdapter, ReflectionHelper reflectionHelper) {
		this.field = field;
		this.elementAdapter = elementAdapter;
		this.reflectionHelper = reflectionHelper;
	}

	@Override
	public void write(JsonWriter out, T value) {

	}

	@Override
	public T read(JsonReader in) throws IOException {

		@SuppressWarnings("unchecked")
		T result = (T) reflectionHelper.createInstanceSilently(field.getDeclaringClass());
		
		Object fieldValue = elementAdapter.read(in);
		reflectionHelper.setPrivateField(result, field, fieldValue);
		
		return result;

	}
}