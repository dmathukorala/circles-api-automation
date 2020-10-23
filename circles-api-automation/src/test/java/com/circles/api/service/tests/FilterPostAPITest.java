package com.circles.api.service.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.circles.api.service.responce.dto.PostsDTO;
import com.circles.api.service.services.PostService;
import com.circles.utils.data.HeaderProvider;
import com.cricles.test.base.TestBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FilterPostAPITest extends TestBase {

	private PostService postService;
	private Response response;
	private Integer id;
	private Integer userId;
	private String title;
	private String body1;
	private Map<String, Object> queryParams;
	private Map<String, Object> headers;

	@BeforeClass(alwaysRun = true)
	public void serviceSetUp() throws Exception {

		try {
			postService = new PostService();
			queryParams = new HashMap<String, Object>();

		} catch (Exception e) {
			throw e;
		}
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {

		try {
			headers = HeaderProvider.getHeaders("Header1");
			queryParams.put("userId", 1);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {

		try {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void sendValidFilterPostRequest() {

		try {

			PostsDTO[] resp = postService.filterPost(headers, queryParams);

			for (PostsDTO obj : resp) {

				Assert.assertEquals(obj.getUserId().intValue(), 1);

			}

		} catch (Exception e) {
			logger.error(e);

		}
	}

	@Test
	public void sendFilterPostRequestInvalidUserId() {

		try {

			queryParams.put("userId", RandomStringUtils.randomAlphanumeric(24));

			PostsDTO[] resp = postService.filterPost(headers, queryParams);

			Assert.assertEquals(resp.length, 0);

		} catch (Exception e) {
			logger.error(e);

		}
	}

	@Test
	public void sendFilterPostRequestNullUserId() {

		try {

			queryParams.put("userId", null);

			PostsDTO[] resp = postService.filterPost(headers, queryParams);

			Assert.assertEquals(resp.length, 0);

		} catch (Exception e) {
			logger.error(e);

		}
	}

	@Test
	public void sendFilterPostRequestEmptyUserId() {

		try {

			queryParams.put("userId", null);

			PostsDTO[] resp = postService.filterPost(headers, queryParams);

			Assert.assertEquals(resp.length, 0);

		} catch (Exception e) {
			logger.error(e);

		}
	}

}
