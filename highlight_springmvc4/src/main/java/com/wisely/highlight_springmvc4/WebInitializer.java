package com.wisely.highlight_springmvc4;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Web 配置：
 * 1 WebApplicationInitializer 是 Spring 提供用来配置 Servlet3.0+配置的接口，从而实现了替代 web.xml 的位置。实现此接口将会自动被 SpringServletContainerInitializer（用来启动 Servlet 3.0 容器）获取到。
 * 2 新疆 WebApplicationContext，注册配置类，并将其和当前 servletContext 关联。
 * 3 注册 Spring MVC 的 DispatcherServlet。
 * 
 * 【4.5.3】
 * 11 此句开启异步方法支持
 */
public class WebInitializer implements WebApplicationInitializer {//1

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MyMvcConfig.class);
        ctx.setServletContext(servletContext); //2
        
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx)); //3
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        servlet.setAsyncSupported(true);//11

	}

}
