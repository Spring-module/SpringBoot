package com.wisely.ch5_2_2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 入口类：
 * @SpringBootApplication 是 Spring Boot 项目的核心注解，主要目的是开启自动配置。
 * @SpringBootApplication 注解主要组合了 @Configuration、 @EnableAutoConfiguration、 @ComponentScan；
 * 若不使用 @SpringBootApplication 注解，则可以在入口类上直接使用 @Configuration、 @EnableAutoConfiguration 、 @ComponentScan。
 */
@RestController
@SpringBootApplication
public class Ch522Application {
	
	 @Value("${book.author}")
	 private String bookAuthor;
	 @Value("${book.name}")
	 private String bookName;
	
	 @RequestMapping("/")
	    String index() {
		
	        return "book name is:"+bookName+" and book author is:" + bookAuthor;
	    }

    public static void main(String[] args) { 
    	
        SpringApplication.run(Ch522Application.class, args);
    	
//    	SpringApplication app = new SpringApplication(Ch522Application.class);
//    	app.setShowBanner(false);//关闭启动时的默认图案显示
//    	app.run(args);
    }
}
