package org.unicen.dmetrics.firebase.json;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.unicen.dmetrics.firebase.core.FirebaseAnnotationProcessor;
import org.unicen.dmetrics.firebase.core.ReflectionHelper;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class SetTypeAdapter extends TypeAdapter<Set<?>> {

	private final TypeAdapter<?> elementAdapter;
	private final FirebaseAnnotationProcessor annotationProcessor;
	private final ReflectionHelper reflectionHelper;
	
	public SetTypeAdapter(TypeAdapter<?> elementAdapter, FirebaseAnnotationProcessor annotationProcessor, ReflectionHelper reflectionHelper) {
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
		
		Entry<String, Field> keyField = annotationProcessor.getKeyField(element.getClass());
		reflectionHelper.setPrivateField(element, keyField.getValue(), property);
	}
}