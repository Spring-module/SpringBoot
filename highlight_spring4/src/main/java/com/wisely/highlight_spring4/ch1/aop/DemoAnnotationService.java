package com.wisely.highlight_spring4.ch1.aop;

import org.springframework.stereotype.Service;

/**
 * 编写“使用注解”的被拦截类
 */
@Service
public class DemoAnnotationService {
	@Action(name="注解式拦截的add操作")
    public void add(){
		System.out.println("DemoAnnotationService -> add");
	} 
   
}
