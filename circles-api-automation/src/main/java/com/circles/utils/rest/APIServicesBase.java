package com.circles.utils.rest;

import com.circles.utils.data.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * @author VSENAPR
 *
 */
/**
 * @author UPEREJ6
 *
 */
public abstract class APIServicesBase extends RestUtil {

	protected ObjectMapper objectMapper = new ObjectMapper();
	protected static String Environment = null;

	public APIServicesBase() {
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

	}

	/**
	 * @return the environment
	 */
	public static String getEnvironment() {
		return Environment;
	}

	/**
	 * @param environment the environment to set
	 */
	public static void setEnvironment(String environment) {
		Environment = environment;
	}

	/**
	 * **Post Request**
	 * 
	 * @param body
	 * @param headers
	 * @param baseURI
	 * @param relativeURI
	 * @return response
	 */
	public Response postRequest(JSONObject body, Map<String, Object> headers, String baseURI, String relativeURI) {

		setBaseURI(baseURI);
		setBasePath(relativeURI);
		try {

			Response response = given().headers(headers).body(body.toJSONString()).when().post();

			System.out.println(response.asString());

			// Clear Base Path
			resetBaseURI();
			resetBasePath();

			return response;

		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * **Get Request**
	 * 
	 * @param headers
	 * @param baseURI
	 * @param relativeURI
	 * @return
	 */
	public Response getRequest(Map<String, Object> headers, String baseURI, String relativeURI,
			Map<String, Object> params) {

		try {
			setBaseURI(baseURI);
			setBasePath(relativeURI);
			RestAssured.useRelaxedHTTPSValidation();
			Response response = given().params(params).headers(headers).when().get();

			// Clear Base Path
			resetBaseURI();
			resetBasePath();

			return response;

		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * **Get Request**
	 * 
	 * @param headers
	 * @param baseURI
	 * @param relativeURI
	 * @return
	 */
	public Response getRequest(Map<String, Object> headers, String baseURI, String relativeURI) {

		try {
			setBaseURI(baseURI);
			setBasePath(relativeURI);
			RestAssured.useRelaxedHTTPSValidation();
			Response response = given().headers(headers).when().get();

			// Clear Base Path
			resetBaseURI();
			resetBasePath();

			return response;

		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * **Get Request**
	 * 
	 * @param headers
	 * @param baseURI
	 * @param relativeURI
	 * @return
	 */

	public Response getRequestMultiKey(Map<String, Object> headers, String baseURI, String relativeURI,
			Map<String, String> params) {

		try {
			setBaseURI(baseURI);
			setBasePath(relativeURI);
			Response response = given().queryParams(params).queryParam("instructorId", "instructorId2").headers(headers)
					.when().get();

			// Clear Base Path
			resetBaseURI();
			resetBasePath();

			return response;

		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * **Delete Request**
	 * 
	 * @param headers
	 * @param baseURI
	 * @param relativeURI
	 * @return
	 */
	public Response deleteRequest(Map<String, Object> headers, String baseURI, String relativeURI) {

		try {
			setBaseURI(baseURI);
			setBasePath(relativeURI);

			Response response = given().headers(headers).when().delete();

			// Clear Base Path
			resetBaseURI();
			resetBasePath();

			return response;

		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * **Put Request**
	 * 
	 * @param headers
	 * @param baseURI
	 * @param relativeURI
	 * @return
	 */
	public Response putRequest(JSONObject body, Map<String, Object> headers, String baseURI, String relativeURI) {

		try {

			setBaseURI(baseURI);
			setBasePath(relativeURI);

			Response response = given().headers(headers).body(body).when().put();

			// Clear Base Path
			resetBaseURI();
			resetBasePath();

			return response;

		} catch (Exception ex) {
			throw ex;
		}
	}

	public Response postRequest(Map<String, Object> headers, String baseURI, String relativeURI,
			Map<String, String> query) {

		try {
			setBaseURI(baseURI);
			setBasePath(relativeURI);

			Response response = given().queryParams(query).headers(headers).when().post();

			// Clear Base Path
			resetBaseURI();
			resetBasePath();

			return response;

		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * **GET request with query parameter**
	 * 
	 * @param headers
	 * @param baseURI
	 * @param relativeURI
	 * @param query
	 * @return
	 */

}
