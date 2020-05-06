//package com.petclinic.config;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
//import org.h2.server.web.WebServlet;
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
//import com.petclinic.sample.SampleInit;
//
//public class C1_Init implements WebApplicationInitializer {
//
//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//
//		//set active profiles
//        servletContext.setInitParameter("spring.profiles.active", "map");
//		
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(C2_WebConfig.class);
//        
//      //Spring Dispatcher Servlet
//        servletContext.addListener(new ContextLoaderListener(context));
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//        
//      //H2 console
//        String path = "/h2-console";
//        String urlMapping = (path.endsWith("/") ? path + "*" : path + "/*");
//        ServletRegistration.Dynamic h2Console = servletContext.addServlet("h2", new WebServlet());
//        h2Console.setLoadOnStartup(1);
//        h2Console.addMapping(urlMapping);
//        
//        init();
//            
//	}
//	
//	private void init(){
//		
//		System.out.println("Init");
//		SampleInit.init();
//	}
//
//}
