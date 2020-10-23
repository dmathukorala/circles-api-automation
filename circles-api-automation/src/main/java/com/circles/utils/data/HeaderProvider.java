package com.circles.utils.data;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.Map;

public class HeaderProvider {

	private static Map<String, JSONObject> headers;
	private static HeaderProvider instance;
	private static String path;

	private HeaderProvider() {

		try {
			File file = new File(path);
			JSONParser parser = new JSONParser();
			headers = (Map<String, JSONObject>) parser.parse(new FileReader(file));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setHeaderProviderFilePath(String headerProviderFilePath) {
		path = headerProviderFilePath;
		instance = null;
	}

	public static Map<String, Object> getHeaders(String templateName) {
		if (instance == null)
			instance = new HeaderProvider();
		return  (Map<String, Object>) headers.get(templateName);
	}

}
