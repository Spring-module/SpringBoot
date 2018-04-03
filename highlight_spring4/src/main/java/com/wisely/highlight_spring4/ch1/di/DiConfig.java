package com.wisely.highlight_spring4.ch1.di;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类：
 * 1 @Configuration 声明当前类是一个配置类。
 * 2 使用@ComponentScan，自动扫描包下所有使用@Service、@Component、@Repository 和  @Controller 的类，并注册为 Bean。
 */
@Configuration //1
@ComponentScan("com.wisely.highlight_spring4.ch1.di") //2
public class DiConfig {

}
