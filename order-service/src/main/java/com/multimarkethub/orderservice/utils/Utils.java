package com.multimarkethub.orderservice.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class Utils {
	
	
    @Autowired
    private ResourceLoader resourceLoader;
	
	
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
	
	
	public String readHTMLFile() throws IOException {
		String fileName= "order_email_template.html";
        Resource resource = resourceLoader.getResource("classpath:" + fileName);
        InputStream inputStream = resource.getInputStream();
        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String htmlContent = reader.lines().collect(Collectors.joining("\n"));
                return htmlContent;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File not found: " + fileName);
        }
        return null;
    }

}
