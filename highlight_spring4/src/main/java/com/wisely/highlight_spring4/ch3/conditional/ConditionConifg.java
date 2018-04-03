package com.wisely.highlight_spring4.ch3.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类：
 * 1 通过@Conditional注解，符合 Windows条件则实例化 windowsListService。
 * 2 通过@Conditional注解，符合Linux条件按则实例化linuxListService。
 */
@Configuration
public class ConditionConifg {
	@Bean
    @Conditional(WindowsCondition.class) //1
    public ListService windowsListService() {
        return new WindowsListService();
    }

    @Bean
    @Conditional(LinuxCondition.class) //2
    public ListService linuxListService() {
        return new LinuxListService();
    }

}
