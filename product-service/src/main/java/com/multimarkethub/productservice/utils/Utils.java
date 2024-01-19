package com.multimarkethub.productservice.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;


public class Utils {
	
	
	public static String capitalizeEachWord(String input) {
		return Arrays.stream(input.split("\\s+"))
                .filter(word -> !word.isEmpty())
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
	}
	
	
	public static boolean isImage(MultipartFile file) {
	    
	    String fileName = file.getOriginalFilename();
	    return fileName != null && (fileName.endsWith(".jpg") || fileName.endsWith(".JPG") || fileName.endsWith(".jpeg") || fileName.endsWith(".JPEG") || fileName.endsWith(".png") || fileName.endsWith(".PNG") || fileName.endsWith(".SVG") || fileName.endsWith(".svg"));
	}

}
