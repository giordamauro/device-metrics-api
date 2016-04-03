package org.unicen.dmetrics.firebase.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class FirebasePlaceholdersProcessor {

	private static final Pattern MATCHER_PATTERN = Pattern.compile("\\{([^}]+)\\}");
	
	public List<String> getUrlPlaceholders(String url){
		
		List<String> placeholders = new ArrayList<>();

		Matcher matcher = MATCHER_PATTERN.matcher(url);
		while (matcher.find()) {
			placeholders.add(matcher.group(1));
		}

		return placeholders;
	}
}
