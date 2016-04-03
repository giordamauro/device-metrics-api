package org.unicen.dmetrics.firebase.core;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public class CamelCaseNamingStrategy extends PropertyNamingStrategy {
	
	private static final long serialVersionUID = 8114566216794335344L;

	@Override
	public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
		return translate(defaultName);
	}

	@Override
	public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
		return translate(defaultName);
	}

	@Override
	public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
		return translate(defaultName);
	}

	private String translate(String defaultName) {
		char[] nameChars = defaultName.toCharArray();
		StringBuilder nameTranslated = new StringBuilder(nameChars.length * 2);
		for (char c : nameChars) {
			if (Character.isUpperCase(c)) {
				nameTranslated.append("-");
				c = Character.toLowerCase(c);
			}
			nameTranslated.append(c);
		}
		return nameTranslated.toString();
	}
}