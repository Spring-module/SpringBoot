package com.wisely.highlight_springmvc4;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.wisely.highlight_springmvc4.interceptor.DemoInterceptor;
import com.wisely.highlight_springmvc4.messageconverter.MyMessageConverter;

/**
 * Spring MVC 配置：
 * 此处无任何特别，只是一个普通的 Spring 配置类。这里我们配置了一个 JSP 的 ViewResolver，用来映射路径和实际页面的位置，
 * 其中，@EnableWebMvc 注解开启一些默认配置，如一些 ViewResolver 或者 MessageConverter 等。
 * 
 * 【4.4.1】
 * 1 @EnableWebMvc 开启 Spring MVC 支持，若无此句，重写 WebMvcConfigurerAdapter 方法无效。
 * 2 继承 WebMvcConfigurerAdapter 类，重写其方法 SpringMVC进行配置。
 * 3 addResourceLocations 指的是文件放置的目录，addResourceHandler 指的是对外暴露的访问路径。
 * 
 * 【4.4.2】
 * 11 配置拦截器的 Bean。
 * 22 重写 addInterceptors 方法，注册拦截器。
 * 
 * 【4.4.4】
 * 111 快捷的 ViewController，通过重写 addViewControllers方法，简化配置页面转向
 * 222 路径匹配参数配置，通过重写 configurePathMatch方法可不忽略"."后面的参数
 * 
 * 【4.5.1】
 * 1111 文件上传配置。MultipartResolver 配置。
 * 2222 自定义HttpMessageConverter。extendMessageConverter：仅添加一个自定义的 HttpMessageConverter，不覆盖默认注册的 HttpMessageConverter。
 */
@Configuration
@EnableWebMvc// 1
@EnableScheduling
@ComponentScan("com.wisely.highlight_springmvc4")
public class MyMvcConfig extends WebMvcConfigurerAdapter {// 2

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/assets/**").addResourceLocations(
				"classpath:/assets/");// 3

	}

	@Bean
	public DemoInterceptor demoInterceptor() {// 11
		return new DemoInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {// 22
		registry.addInterceptor(demoInterceptor());
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {// 111
		registry.addViewController("/index").setViewName("/index");
		registry.addViewController("/toUpload").setViewName("/upload");
		registry.addViewController("/converter").setViewName("/converter");
		registry.addViewController("/sse").setViewName("/sse");
		registry.addViewController("/async").setViewName("/async");
	}

	 @Override
	 public void configurePathMatch(PathMatchConfigurer configurer) {// 222
	 configurer.setUseSuffixPatternMatch(false);
	 }

	@Bean
	public MultipartResolver multipartResolver() {// 1111
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1000000);
		return multipartResolver;
	}
	
	@Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {// 2222
        converters.add(converter());
    }
	
	@Bean 
	public MyMessageConverter converter(){
		return new MyMessageConverter();
	}

	

}
