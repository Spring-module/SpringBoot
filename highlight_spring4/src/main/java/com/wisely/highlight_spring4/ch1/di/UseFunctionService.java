package com.wisely.highlight_spring4.ch1.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 使用功能类的Bean：
 * 1 使用@Service 注解声明当前 UseFunctionService 类是 Spring 管理的一个Bean。
 * 2 使用@Autowired 将 FunctionService 的实体 Bean 注入到 UseFunctionService 中，让UseFucntionService 具备 FunctionService 的功能，此处使用 JSR-330 的@Inject 注解或者 JSR-250
 */
@Service //1
public class UseFunctionService {
	@Autowired //2
	FunctionService functionService;
	
	public String SayHello(String word){
		return functionService.sayHello(word);
	}

}
