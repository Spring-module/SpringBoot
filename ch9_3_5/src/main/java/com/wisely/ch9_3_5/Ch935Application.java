package com.wisely.ch9_3_5;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 发送消息及目的地定义：
 * 1 可注入 Spring Boot 为我们自动配置好的 RabbitTemplate。
 * 2 定义目的地即队列，队列名称为 my-queue。
 * 3 通过 RabbitTemlate 的 convertAndSend 方法向队列 my-queue 发送消息。
 */
@SpringBootApplication
public class Ch935Application implements CommandLineRunner{
	@Autowired
	RabbitTemplate rabbitTemplate; //1

    public static void main(String[] args) {
        SpringApplication.run(Ch935Application.class, args);
    }
    
    @Bean //2
    public Queue wiselyQueue(){
        return new Queue("my-queue");
    }
    

	@Override
	public void run(String... args) throws Exception {
		 rabbitTemplate.convertAndSend("my-queue", "来自RabbitMQ的问候"); //3
	}
}
