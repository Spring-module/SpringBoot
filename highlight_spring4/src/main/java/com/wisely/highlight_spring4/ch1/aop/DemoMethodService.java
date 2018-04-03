package com.wisely.highlight_spring4.ch1.aop;

import org.springframework.stereotype.Service;

/**
 * 编写使用方法规则被拦截类
 */
@Service
public class DemoMethodService {
	public void add(){
		System.out.println("DemoMethodService -> add");
	}
}
