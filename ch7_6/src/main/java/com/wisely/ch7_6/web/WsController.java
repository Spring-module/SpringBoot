package com.wisely.ch7_6.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.wisely.ch7_6.domain.WiselyMessage;
import com.wisely.ch7_6.domain.WiselyResponse;

/**
 * 演示控制器：
 * 1 当浏览器向服务器发送请求时，通过@MessageMapping 映射 /welcome 这个地址类似于 @RequestMapping。
 * 2 当服务端有消息时，会对订阅了 @SendTo 中的路径的浏览器发送消息。
 * 
 * 11 通过 SimpMessagingTemplate 向浏览器发送消息。
 * 22 在 SpringMVC中，可以直接在参数中获得 principal，pinciple 中包含当前用户的信息。
 * 33 这里是一段硬编码，如果发送人是wyf，则发送给 wisly；如果发送人是 wisely，则发送给 wyf，读者可以根据项目实际需要改写此处代码。
 * 44 通过 messagingTemplate.convertAndSendToUser 向用户发送消息，第一个参数是接收消息的用户，第二个是浏览器订阅的地址，第三个是消息本身。
 */
@Controller
public class WsController {

	@MessageMapping("/welcome")//1
	@SendTo("/topic/getResponse")//2
	public WiselyResponse say(WiselyMessage message) throws Exception {
		Thread.sleep(3000);
		return new WiselyResponse("Welcome, " + message.getName() + "!");
	}

	@Autowired
	private SimpMessagingTemplate messagingTemplate;//11

	@MessageMapping("/chat")
	public void handleChat(Principal principal, String msg) { //22
		if (principal.getName().equals("wyf")) {//33
			messagingTemplate.convertAndSendToUser("wisely",
					"/queue/notifications", principal.getName() + "-send:"
							+ msg);//44
		} else {
			messagingTemplate.convertAndSendToUser("wyf",
					"/queue/notifications", principal.getName() + "-send:"
							+ msg);
		}
	}
}