package com.petclinic.sample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
	
	@RequestMapping("/")
	public String display() {
		return "index";
	}
	
	 @RequestMapping(value = "/hello", method = RequestMethod.GET)
	   public String printHello(ModelMap model) {
		 
		 HelloService helloService = new HelloService();
		 
	      model.addAttribute("message", helloService.hello());
	      
	      System.out.println(helloService.hello());
	      
	      return "index";
	   }
}