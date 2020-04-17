package com.petclinic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.petclinic")
public class C2_WebConfig implements WebMvcConfigurer {
	
//	@Override
//	   public void addViewControllers(ViewControllerRegistry registry) {
//	      registry.addViewController("/").setViewName("index");
//	   }
	 
	   @Bean
	   public ViewResolver viewResolver() {
	      InternalResourceViewResolver bean = new InternalResourceViewResolver();
	 
//	      bean.setViewClass(JstlView.class);
	      bean.setPrefix("/WEB-INF/views/jsp/");
	      bean.setSuffix(".jsp");
	 
	      return bean;
	   }

}
