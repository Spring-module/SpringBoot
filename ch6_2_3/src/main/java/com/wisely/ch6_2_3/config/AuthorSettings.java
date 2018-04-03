package com.wisely.ch6_2_3.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 类型安全的 Bean:
 * 1 通过 @ConfigurationProperties 加载 properties 文件内的配置，通过 prefix 属性指定 properties 的配置的前缀，通过 locations 指定 properties 文件的位置。
 */
@Component
@ConfigurationProperties(prefix = "author") //1 
public class AuthorSettings {
    private String name;
    private Long age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}