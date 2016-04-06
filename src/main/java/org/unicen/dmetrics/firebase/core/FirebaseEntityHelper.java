package org.unicen.dmetrics.firebase.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FirebaseEntityHelper {

	@Autowired
	private FirebaseAnnotationProcessor annotationProcessor;
	
	private static final Pattern MATCHER_PATTERN = Pattern.compile("\\{([^}]+)\\}");
	
	public String getEntityUrl(Class<?> domainClass) {
		
		String entityPath = annotationProcessor.getPathUrl(domainClass);
		
		List<String> urlPlaceholders = getUrlPlaceholders(entityPath);
		if(!urlPlaceholders.isEmpty()){
			throw new IllegalStateException(String.format("Find by Key in class %s contains Url placeholders", domainClass));
		}

		return entityPath;
	}
	
	public List<String> getUrlPlaceholders(String url){
		
		List<String> placeholders = new ArrayList<>();

		Matcher matcher = MATCHER_PATTERN.matcher(url);
		while (matcher.find()) {
			placeholders.add(matcher.group(1));
		}

		return placeholders;
	}
}
