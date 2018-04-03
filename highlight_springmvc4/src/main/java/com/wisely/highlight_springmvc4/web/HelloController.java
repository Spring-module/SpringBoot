package com.wisely.highlight_springmvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 简单控制器：
 * 1 利用 @Controller 注解声明是一个控制器
 * 2 利用 @RequestMapping 配置 URL 和方法之间的映射。
 * 3 通过上面 ViewResolver 的 Bean 配置，返回值为 index，说明我们的页面放置的路径为 /WEB-INF/classes/views/index.jsp。
 */
@Controller//1
public class HelloController {
	
	@RequestMapping("/index")//2
	public  String hello(){
		
		return "index";
	}

}
