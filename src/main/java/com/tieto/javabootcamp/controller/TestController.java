package com.tieto.javabootcamp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/api/test")
public class TestController {

	// Adresuje soubor v resources/templates
	//  (pouzivame thymeleaf - viz pom.xml)
	@GetMapping("/")
	public String test() {
		return "test.html";
	}
	
	@GetMapping("/rest")
	public ResponseEntity<String> testRest() {
		return new ResponseEntity<String>("IT WORKS!", HttpStatus.OK);
	}
	
	@GetMapping("/betterRest")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String testBetterRest() {
		return "IT WORKS!";
	}

}
