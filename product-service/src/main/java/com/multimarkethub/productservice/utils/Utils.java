package com.multimarkethub.productservice.utils;

import java.util.Arrays;
import java.util.stream.Collectors;


public class Utils {
	
	
	public static String capitalizeEachWord(String input) {
		return Arrays.stream(input.split("\\s+"))
                .filter(word -> !word.isEmpty())
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
	}

}
