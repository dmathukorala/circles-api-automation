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

public class UpdatePostAPITest extends TestBase {

	private PostService postService;
	private PostsDTO postDTO;
	private Integer id;
	private Integer userId;
	private String title;
	private String body1;
	private JSONObject body;
	private Map<String, Object> headers;

	@BeforeClass(alwaysRun = true)
	public void serviceSetUp() throws Exception {

		try {
			postService = new PostService();

		} catch (Exception e) {
			throw e;
		}
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {

		try {

			headers = HeaderProvider.getHeaders("Header1");

			body = getJSONBodyTemplate("CreatePost");

			userId = RandomUtils.nextInt(1, 100);

			title = RandomStringUtils.randomAlphabetic(24);

			body1 = RandomStringUtils.randomAlphabetic(24);

			body.put("userId", userId);

			body.put("title", title);

			body.put("body", body1);

			postDTO = postService.createPost(body, headers);

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
	public void sendValidUpdatePostRequest() {

		try {

			title = RandomStringUtils.randomAlphabetic(24);
			body1 = RandomStringUtils.randomAlphabetic(24);

			body.put("id", 1);
			body.put("userId", userId);
			body.put("title", title);
			body.put("body", body1);

			postDTO = postService.updatePost(body, headers, "1");

			Assert.assertEquals(postDTO.getStatusCode(), 200);

			Assert.assertEquals(postDTO.getUserId(), userId);

			Assert.assertEquals(postDTO.getId().intValue(), 1);

			Assert.assertEquals(postDTO.getTitle(), title);

			Assert.assertEquals(postDTO.getBody(), body1);

		} catch (Exception e) {
			logger.error(e);

		}
	}

	@Test
	public void sendValidUpdatePostRequestWithNullUserId() {

		try {

			title = RandomStringUtils.randomAlphabetic(24);
			body1 = RandomStringUtils.randomAlphabetic(24);

			body.put("id", 1);
			body.put("userId", null);
			body.put("title", title);
			body.put("body", body1);

			postDTO = postService.updatePost(body, headers, "1");

			Assert.assertEquals(postDTO.getStatusCode(), 200);

			Assert.assertNull(postDTO.getUserId(), null);

			Assert.assertEquals(postDTO.getId().intValue(), 1);

			Assert.assertEquals(postDTO.getTitle(), title);

			Assert.assertEquals(postDTO.getBody(), body1);

		} catch (Exception e) {
			logger.error(e);

		}
	}

}
