package com.wisely.highlight_spring4.ch2.prepost;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.wisely.highlight_spring4.ch2.prepost")
public class PrePostConfig {
	
	@Bean(initMethod="init",destroyMethod="destroy") //1 initMethod 和 destroyMethod指定 BeanWayService类的 init 和 destory 方法在构造之后、Bean 销毁之前执行
	BeanWayService beanWayService(){
		return new BeanWayService();
	}
	
	@Bean
	JSR250WayService jsr250WayService(){
		return new JSR250WayService();
	}

}
