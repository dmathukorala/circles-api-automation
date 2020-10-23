package com.circles.api.service.services;

import com.circles.api.constants.RelativeURLs;
import com.circles.api.service.responce.dto.PostsDTO;
import com.circles.utils.data.Config;
import com.circles.utils.rest.APIServicesBase;
import com.circles.utils.rest.RestUtil;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import java.util.Map;

public class PostService extends APIServicesBase {

	private PostsDTO postsDTO;
	private PostsDTO[] postsListDTO;
	private String baseURI;
	private String serviceType;

	public PostService() {
		this.baseURI = Config.getProperty("service.host");
		this.serviceType = Config.getProperty("service.type");
	}

	public PostsDTO createPost(JSONObject body, Map<String, Object> headers) throws Exception {

		try {
			// Make Request
			Response response = postRequest(body, headers, baseURI, RelativeURLs.CREATE_POSTS);

			if (response.statusCode() == 201)
				postsDTO = objectMapper.readValue(response.asString(), PostsDTO.class);
			else
				postsDTO = new PostsDTO();

			postsDTO.setResponse(response);

			// Clear Base Path
			RestUtil.resetBasePath();

		} catch (Exception e) {
			throw e;
		}

		return postsDTO;

	}

	public PostsDTO updatePost(JSONObject body, Map<String, Object> headers, String id) throws Exception {

		try {
			// Make Request
			Response response = putRequest(body, headers, baseURI, RelativeURLs.UPDATE_POSTS.replace("{id}", id));

			if (response.statusCode() == 200)
				postsDTO = objectMapper.readValue(response.asString(), PostsDTO.class);
			else
				postsDTO = new PostsDTO();

			postsDTO.setResponse(response);

			// Clear Base Path
			RestUtil.resetBasePath();

		} catch (Exception e) {
			throw e;
		}

		return postsDTO;

	}

	public PostsDTO[] filterPost(Map<String, Object> headers, Map<String, Object> queryParam) throws Exception {

		Response response = null;

		try {
			// Make Request
			response = getRequest(headers, baseURI, RelativeURLs.FILTER_POSTS, queryParam);

			if (response.statusCode() == 200)
				postsListDTO = objectMapper.readValue(response.asString(), PostsDTO[].class);
			else {
				postsListDTO[0] = new PostsDTO();

				for (int i = 0; i < postsListDTO.length; i++) {
					postsListDTO[i].setResponse(response);
				}
			}
			// Clear Base Path
			RestUtil.resetBasePath();

		} catch (Exception e) {
			throw e;
		}

		return postsListDTO;

	}

	public PostsDTO createPostWithUrl(JSONObject body, Map<String, Object> headers, String url) throws Exception {

		try {
			// Make Request
			Response response = postRequest(body, headers, baseURI, url);

			if (response.statusCode() == 201)
				postsDTO = objectMapper.readValue(response.asString(), PostsDTO.class);
			else
				postsDTO = new PostsDTO();

			postsDTO.setResponse(response);

			// Clear Base Path
			RestUtil.resetBasePath();

		} catch (Exception e) {
			throw e;
		}

		return postsDTO;

	}

	public PostsDTO createPostAsHttp(JSONObject body, Map<String, Object> headers) throws Exception {

		try {
			// Make Request
			Response response = postRequest(body, headers, baseURI.replace("https", "http"), RelativeURLs.CREATE_POSTS);

			if (response.statusCode() == 201)
				postsDTO = objectMapper.readValue(response.asString(), PostsDTO.class);
			else
				postsDTO = new PostsDTO();

			postsDTO.setResponse(response);

			// Clear Base Path
			RestUtil.resetBasePath();

		} catch (Exception e) {
			throw e;
		}

		return postsDTO;

	}

	public PostsDTO createPostWithDifferentHttpMethod(JSONObject body, Map<String, Object> headers, String httpMethod)
			throws Exception {

		try {
			// Make Request

			Response response = null;

			if (httpMethod.equalsIgnoreCase("post"))
				response = postRequest(body, headers, baseURI, RelativeURLs.CREATE_POSTS);
			else if (httpMethod.equalsIgnoreCase("put"))
				response = putRequest(body, headers, baseURI, RelativeURLs.CREATE_POSTS);
			else if (httpMethod.equalsIgnoreCase("get"))
				response = getRequest(headers, baseURI, RelativeURLs.CREATE_POSTS);
			else if (httpMethod.equalsIgnoreCase("delete"))
				response = deleteRequest(headers, baseURI, RelativeURLs.CREATE_POSTS);

			if (response.statusCode() == 201)
				postsDTO = objectMapper.readValue(response.asString(), PostsDTO.class);
			else
				postsDTO = new PostsDTO();

			postsDTO.setResponse(response);

			// Clear Base Path
			RestUtil.resetBasePath();

		} catch (Exception e) {
			throw e;
		}

		return postsDTO;

	}

}