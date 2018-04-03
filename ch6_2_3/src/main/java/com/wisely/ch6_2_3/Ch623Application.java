package com.wisely.ch6_2_3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisely.ch6_2_3.config.AuthorSettings;

/**
 * 入口类：基于类型安全的配置方式，通过 @ConfigurationProperties 将 properties 属性和一个 Bean 及其属性关联，从而实现类型安全的配置。
 * 1 @Autowired 直接注入配置
 */
@RestController
@SpringBootApplication
public class Ch623Application {
	
	@Autowired
	private AuthorSettings authorSettings; //1
	
	@RequestMapping("/")
	public String index(){
		return "author name is "+ authorSettings.getName()+" and author age is "+authorSettings.getAge();
	}

    public static void main(String[] args) {
        SpringApplication.run(Ch623Application.class, args);
    }
}
