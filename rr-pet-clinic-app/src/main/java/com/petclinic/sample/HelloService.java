package com.petclinic.sample;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
	
	public String hello() {
		return "hello";
	}
}