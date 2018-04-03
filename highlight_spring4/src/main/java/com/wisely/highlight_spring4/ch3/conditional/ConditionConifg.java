package com.wisely.highlight_spring4.ch3.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * �����ࣺ
 * 1 ͨ��@Conditionalע�⣬���� Windows������ʵ���� windowsListService��
 * 2 ͨ��@Conditionalע�⣬����Linux��������ʵ����linuxListService��
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
