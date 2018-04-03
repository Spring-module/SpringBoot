package com.wisely.highlight_spring4.ch1.javaconfig;

import com.wisely.highlight_spring4.ch1.javaconfig.FunctionService;

/**
 * 使用功能类的 Bean：
 * 1 此处没有使用 @Service 声明 Bean。
 * 2 此处没有使用 @Autowired 注解注入的 Bean。
 */
//1
public class UseFunctionService {
	//2
	FunctionService functionService;
	
	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}
	
	public String SayHello(String word){
		return functionService.sayHello(word);
	}

}
