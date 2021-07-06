package com.example.onlinebookshop;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlinebookshopApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	public static List<Integer> convertStringArrToIntArr(String[] strArr){
		List<Integer> listInt = new ArrayList<Integer>();
		if(strArr.length>0){
			for(int index=0; index<strArr.length; index++){
				listInt.add(Integer.valueOf(strArr[index]));
			}
		}
		
		return listInt;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(OnlinebookshopApplication.class, args);
		System.out.println("This is my assignment to build e-commerce webapp");
	}

}
