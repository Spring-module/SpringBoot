package com.wisely.highlight_springmvc4.web.ch4_3;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisely.highlight_springmvc4.domain.DemoObj;

/**
 * @RestController 演示
 * 1 使用 @RestController，声明是控制器，并且返回数据时不需要@ResponseBody。
 * 2 返回数据的媒体类型为 json。
 * 3 直接返回对象，对象会自动转换成 json。
 * 4 返回数据的媒体类型为 xml。
 * 5 直接返回对象，对象会自动转换成 xml。
 * 
 */
@RestController //1
@RequestMapping("/rest")
public class DemoRestController {
	
	@RequestMapping(value = "/getjson",
			produces={"application/json;charset=UTF-8"}) //2
	public DemoObj getjson (DemoObj obj){
		return new DemoObj(obj.getId()+1, obj.getName()+"yy");//3
	}
	@RequestMapping(value = "/getxml",
			produces={"application/xml;charset=UTF-8"})//4
	public DemoObj getxml(DemoObj obj){
		return new DemoObj(obj.getId()+1, obj.getName()+"yy");
	}

}
