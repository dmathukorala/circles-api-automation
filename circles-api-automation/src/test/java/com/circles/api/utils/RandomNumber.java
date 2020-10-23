package com.circles.api.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomNumber {
	
	public static String randomIdNumbers() {
		
		return RandomStringUtils.random(11, true, true);
		
	}

}
