package com.cricles.test.base;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.BeforeSuite;
import org.testng.util.Strings;

import com.circles.utils.data.Config;
import com.circles.utils.data.HeaderProvider;

import java.io.File;
import java.io.FileReader;
import java.util.Map;

public abstract class TestBase {

	private static String templateBodyPath;
	private static String templateHeaderPath;
	private static String testDataPath;
	private static String templateErrorMessagePath;
	private static JSONParser parser;
	private static File file;

	protected static Logger logger;
	protected JSONObject data;
	protected static Map<String, JSONObject> value;
	protected static String associationId;
	protected static String errorMessage;
	public static String baseURI;

	@BeforeSuite(alwaysRun = true)
	public void startUp() throws Exception {

		try {
			Config.setConfigFilePath("/Config/configuration.properties");
			templateBodyPath = Config.getProperty("template.body.path");
			templateHeaderPath = Config.getProperty("template.header.path");
			testDataPath = Config.getProperty("template.data.path");
			templateErrorMessagePath = Config.getProperty("template.errorMessage.path");
			HeaderProvider.setHeaderProviderFilePath(templateHeaderPath);
			parser = new JSONParser();
			associationId = Config.getProperty("socket.associationId");
			parser = new JSONParser();
			baseURI = Config.getProperty("socket.host");

		} catch (Exception e) {
			throw e;
		}
	}

	public abstract void serviceSetUp() throws Exception;

	public JSONObject getJSONBodyTemplate(String filename) throws Exception {

		try {
			file = new File(templateBodyPath, filename);
			return (JSONObject) parser.parse(new FileReader(file));

		} catch (Exception e) {
			throw e;
		}
	}

	public JSONObject getJSONTestData(String filename, String DataName) throws Exception {

		try {
			file = new File(testDataPath, filename);
			value = (Map<String, JSONObject>) parser.parse(new FileReader(file));
			return (JSONObject) value.get(DataName);

		} catch (Exception e) {
			throw e;
		}
	}

	public JSONObject getJSONBodyTemplate(String filename, String DataName) throws Exception {

		try {
			file = new File(templateBodyPath, filename);
			value = (Map<String, JSONObject>) parser.parse(new FileReader(file));
			return (JSONObject) value.get(DataName);

		} catch (Exception e) {
			throw e;
		}
	}

	public String getErrorMessage(String dataName) throws Exception {

		String fileName = "ErrorMessageTemplates";
		try {
			file = new File(templateErrorMessagePath, fileName);
			value = (Map<String, JSONObject>) parser.parse(new FileReader(file));
			return value.get(dataName).get("Message").toString();
		} catch (Exception e) {
			throw e;
		}

	}

}
