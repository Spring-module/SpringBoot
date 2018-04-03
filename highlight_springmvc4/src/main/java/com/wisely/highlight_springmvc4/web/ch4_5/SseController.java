package com.wisely.highlight_springmvc4.web.ch4_5;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 服务器端推送技术：
 * SSE(Server Send Event 服务端发送事件)，需要新式浏览器的支持：
 * 1 注意，这里使用输出的媒体类型为 text/event-stream，这是服务器端SSE的支持，本例演示每5秒钟向浏览器推送随机消息。
 */
@Controller
public class SseController {
	
	@RequestMapping(value="/push",produces="text/event-stream") //1
	public @ResponseBody String push(){
		 Random r = new Random();
        try {
                Thread.sleep(5000);
        } catch (InterruptedException e) {
                e.printStackTrace();
        }   
        return "data:Testing 1,2,3" + r.nextInt() +"\n\n";
	}

}
