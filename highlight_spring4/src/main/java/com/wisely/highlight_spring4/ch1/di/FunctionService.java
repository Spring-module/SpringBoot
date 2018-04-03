package com.wisely.highlight_spring4.ch1.di;

import org.springframework.stereotype.Service;

/**
 * 编写功能类的Bean：
 * 1 使用@Service注解声明当前 FunctionService类是 Spring 管理的一个 Bean。其中，使用 @Component、@Service、@Repository 和 @Controller 是等效的，可根据需要使用。
 */
@Service //1
public class FunctionService {
	public String sayHello(String word){
		return "Hello " + word +" !"; 
	}

}
