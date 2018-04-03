package com.wisely.ch9_3_4;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消息监听：
 * @JmsListener 是 Spring 4.1 为我们提供的一个特性，用来简化 JMS 开发。我们只需在这个注解的属性 destinatin
 * 指定要监听的目的地，即可接受该目的地发送的消息。此例监听 my-destination 目的地发送的消息。
 */
@Component
public class Receiver {

	@JmsListener(destination = "my-destination") //1
	public void receiveMessage(String message) {
		System.out.println("接受到: <" + message + ">");
	}

}
