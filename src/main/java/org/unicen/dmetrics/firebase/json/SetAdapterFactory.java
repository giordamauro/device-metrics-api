package org.unicen.dmetrics.firebase.json;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import org.unicen.dmetrics.firebase.core.FirebaseAnnotationProcessor;
import org.unicen.dmetrics.firebase.core.ReflectionHelper;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

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

		if (!Set.class.isAssignableFrom(type.getRawType())) {
			return null;
		}

		Type elementType = ((ParameterizedType) type.getType()).getActualTypeArguments()[0];
		TypeAdapter<?> elementAdapter = gson.getAdapter(TypeToken.get(elementType));

		return (TypeAdapter<T>) new SetAdapter(elementAdapter, annotationProcessor, reflectionHelper);
	}

	private static class SetAdapter extends TypeAdapter<Set<?>> {

		private final TypeAdapter<?> elementAdapter;
		private final FirebaseAnnotationProcessor annotationProcessor;
		private final ReflectionHelper reflectionHelper;
		
		
		public SetAdapter(TypeAdapter<?> elementAdapter, FirebaseAnnotationProcessor annotationProcessor, ReflectionHelper reflectionHelper) {
			this.elementAdapter = elementAdapter;
			this.annotationProcessor = annotationProcessor;
			this.reflectionHelper = reflectionHelper;
		}

		@Override
		public void write(JsonWriter out, Set<?> value) {

		}

		@Override
		public Set<?> read(JsonReader in) throws IOException {

			if (in.peek() == JsonToken.NULL) {
				in.nextNull();
				return null;
			}

			Set<Object> result = new HashSet<>();
			in.beginObject();
			while (in.hasNext()) {
				
				String property = in.nextName();
				Object element = elementAdapter.read(in);
				
				setElementKeyField(element, property);
				
				result.add(element);
			}
			in.endObject();
			return result;

		}

		private void setElementKeyField(Object element, String property) {
			
			Field keyField = annotationProcessor.getKeyField(element.getClass());
			reflectionHelper.setPrivateField(element, keyField, property);
		}
	}
}