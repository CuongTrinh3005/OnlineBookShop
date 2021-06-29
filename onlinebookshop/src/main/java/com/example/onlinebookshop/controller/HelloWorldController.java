package com.example.onlinebookshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {
	@GetMapping("hello")
	public String sayHello(){
		return "Hello Worlds!";
	}
	
	
}