package com.wisely.highlight_spring4.ch2.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 事件发布类
 * 	注入ApplicationContext用来发布事件。
 * 	使用 ApplicationContext的publishEvent方法来发布。
 */
@Component
public class DemoPublisher {
	@Autowired
	ApplicationContext applicationContext;
	
	public void publish(String msg){
		applicationContext.publishEvent(new DemoEvent(this, msg));
	}

}
