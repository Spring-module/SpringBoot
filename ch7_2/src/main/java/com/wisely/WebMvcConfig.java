package com.wisely;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * [7.3.2] 接管 Spring Boot 的 Web 配置：
 * when？既需要保留 Spring Boot 提供的便利，又需要增加自己的额外的配置时
 * how？定义一个配置类并继承 WebMvcConfigurerAdapter，无需使用 @EnableWebMvc 注解
 * 
 * 在这里重写的 addViewControllers 方法，并不会覆盖 WebMvcAutoConfiguration 中的 addViewControllers
 */
@Configuration
public class WebMvcConfig  extends WebMvcConfigurerAdapter{
	
	 @Override
	   public void addViewControllers(ViewControllerRegistry registry) {
	       registry.addViewController("/xx").setViewName("/xx");
	   }

}
