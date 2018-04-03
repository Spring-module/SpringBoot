package com.wisely.highlight_spring4.ch3.taskexecutor;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 配置类：
 * 1 利用 @EnableAsync 注解开启异步任务支持。
 * 2 配置类实现 AsyncConfigurer 接口并重写 getAsyncExecutor 方法，并返回一个 ThreadPoolTaskExecutor，这样我们就获得了一个基于线程池 TaskExecutor。
 */
@Configuration
@ComponentScan("com.wisely.highlight_spring4.ch3.taskexecutor")
@EnableAsync //1 
public class TaskExecutorConfig implements AsyncConfigurer{//2 

	@Override
	public Executor getAsyncExecutor() {//2
		 ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	        taskExecutor.setCorePoolSize(5);
	        taskExecutor.setMaxPoolSize(10);
	        taskExecutor.setQueueCapacity(25);
	        taskExecutor.initialize();
	        return taskExecutor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return null;
	}

}
