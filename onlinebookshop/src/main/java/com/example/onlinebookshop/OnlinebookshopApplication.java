package com.example.onlinebookshop;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
public class OnlinebookshopApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("n17dccn017@student.ptithcm.edu.vn");
	    mailSender.setPassword("trinhquoccuong30051999");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
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
	
	public static Boolean verifyPhoneNumber(String phoneNumber){
		char[] chars = phoneNumber.toCharArray();
	      for(char c : chars){
	         if(!Character.isDigit(c)){
	            return false;
	         }
	      }
	      return true;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(OnlinebookshopApplication.class, args);
		System.out.println("This is my assignment to build e-commerce webapp");
	}

}
