package com.wisely.ch9_3_4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

/**
 * 消息发送及目的地定义：
 * 1 Spring Boot 为我们提供了 CommandLineRunner 接口，用于程序启动后执行的代码，通过重写其中run方法执行。
 * 2 注入 Spring Boot 为我们配置好的 JmsTemplate 的 Bean。
 * 3 通过 JmsTemplate 的 send 方法向 my-destination 目的地发送 Msg 的消息，这里也等于在消息代理上定义了一个目的地叫 my-destination。
 */
@SpringBootApplication
public class Ch934Application implements CommandLineRunner{ //1
	
	@Autowired
	JmsTemplate jmsTemplate; //2

    public static void main(String[] args) {
        SpringApplication.run(Ch934Application.class, args);
        
    }

	@Override
	public void run(String... args) throws Exception {
		jmsTemplate.send("my-destination", new Msg()); //3
		
	}
}
