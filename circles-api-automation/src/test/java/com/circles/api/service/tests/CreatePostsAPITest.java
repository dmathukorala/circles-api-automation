package com.circles.api.service.tests;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.circles.api.service.responce.dto.PostsDTO;
import com.circles.api.service.services.PostService;
import com.circles.utils.data.HeaderProvider;
import com.cricles.test.base.TestBase;

public class CreatePostsAPITest extends TestBase {

	private PostService postService;
	private PostsDTO postDTO;
	private Integer id;
	private Integer userId;
	private String title;
	private String body1;
	private Map<String, Object> headers;
	private JSONObject body;

	@BeforeClass(alwaysRun = true)
	public void serviceSetUp() throws Exception {

		try {
			postService = new PostService();
			body = getJSONBodyTemplate("CreatePost");

		} catch (Exception e) {
			throw e;
		}
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {

		try {
			headers = HeaderProvider.getHeaders("Header1");
			userId = RandomUtils.nextInt(1, 10);
			title = RandomStringUtils.randomAlphabetic(24);
			body1 = RandomStringUtils.randomAlphabetic(24);

			body.put("userId", userId);
			body.put("title", title);
			body.put("body", body1);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {

		try {

			body.clear();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void sendValidPostRequest() {

		try {

			postDTO = postService.createPost(body, headers);

			Assert.assertEquals(postDTO.getStatusCode(), 201);

			Assert.assertEquals(postDTO.getUserId(), userId);

			Assert.assertNotNull(postDTO.getId());

			Assert.assertEquals(postDTO.getTitle(), title);

			Assert.assertEquals(postDTO.getBody(), body1);

		} catch (Exception e) {
			logger.error(e);

		}
	}

	@Test
	public void sendPostRequestWithoutUserId() {

		try {

			body.remove("userId");

			postDTO = postService.createPost(body, headers);

			Assert.assertEquals(postDTO.getStatusCode(), 201);

			Assert.assertNull(postDTO.getUserId());

			Assert.assertNotNull(postDTO.getId());

			Assert.assertEquals(postDTO.getTitle(), title);

			Assert.assertEquals(postDTO.getBody(), body1);

		} catch (Exception e) {
			logger.error(e);

		}
	}

	@Test
	public void sendPostRequestWitNullUserId() {

		try {

			body.put("userId", null);

			postDTO = postService.createPost(body, headers);

			Assert.assertEquals(postDTO.getStatusCode(), 201);

			Assert.assertNull(postDTO.getUserId());

			Assert.assertNotNull(postDTO.getId());

			Assert.assertEquals(postDTO.getTitle(), title);

			Assert.assertEquals(postDTO.getBody(), body1);

		} catch (Exception e) {
			logger.error(e);

		}
	}

	@Test
	public void sendPostRequestWitEmptyUserId() {

		try {

			body.put("userId", "");

			postDTO = postService.createPost(body, headers);

			Assert.assertEquals(postDTO.getStatusCode(), 201);

			Assert.assertNull(postDTO.getUserId());

			Assert.assertNotNull(postDTO.getId());

			Assert.assertEquals(postDTO.getTitle(), title);

			Assert.assertEquals(postDTO.getBody(), body1);

		} catch (Exception e) {
			logger.error(e);

		}
	}

	@Test
	public void sendPostRequestWitInvlidHttpMethod() {

		try {

			postDTO = postService.createPostWithDifferentHttpMethod(body, headers, "put");

			Assert.assertEquals(postDTO.getStatusCode(), 404);

			postDTO = postService.createPostWithDifferentHttpMethod(body, headers, "delete");

			Assert.assertEquals(postDTO.getStatusCode(), 404);

		} catch (Exception e) {
			logger.error(e);

		}
	}

	@Test
	public void sendPostRequestAsHttp() {

		try {

			postDTO = postService.createPostAsHttp(body, headers);

			Assert.assertEquals(postDTO.getStatusCode(), 201);

			Assert.assertEquals(postDTO.getUserId(), userId);

			Assert.assertNotNull(postDTO.getId());

			Assert.assertEquals(postDTO.getTitle(), title);

			Assert.assertEquals(postDTO.getBody(), body1);

		} catch (Exception e) {
			logger.error(e);

		}
	}

	@Test
	public void sendPostRequestWithoutTitle() {

		try {

			body.remove("title");

			postDTO = postService.createPost(body, headers);

			Assert.assertEquals(postDTO.getStatusCode(), 201);

			Assert.assertEquals(postDTO.getUserId(), userId);

			Assert.assertNotNull(postDTO.getId());

			Assert.assertNull(postDTO.getTitle(), title);

			Assert.assertEquals(postDTO.getBody(), body1);

		} catch (Exception e) {
			logger.error(e);

		}
	}

	@Test
	public void sendPostRequestWithoutBodyField() {

		try {

			body.remove("body");

			postDTO = postService.createPost(body, headers);

			Assert.assertEquals(postDTO.getStatusCode(), 201);

			Assert.assertEquals(postDTO.getUserId(), userId);

			Assert.assertNotNull(postDTO.getId());

			Assert.assertEquals(postDTO.getTitle(), title);

			Assert.assertNull(postDTO.getBody(), body1);

		} catch (Exception e) {
			logger.error(e);

		}
	}

}
