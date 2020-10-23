package com.cricles.test.base;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class BaseResponseDTO {

    private Response response;
    private int statusCode;
    private String contentType;
    private String body;
	private Headers header;
 

    public void setResponse(Response response) {
        this.response = response;
        this.statusCode = response.statusCode();
        this.contentType = response.contentType();
        this.body = response.toString();
        this.header = response.getHeaders();
    }

    public Response getResponse() {
        return response;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getContentType() {
        return contentType;
    }

    public String getBody() {
        return body;
    }

	public Headers getHeader() {
		return header;
	}
    
    

}
