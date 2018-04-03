package com.wisely.highlight_spring4.ch1.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 编写拦截规则的注解：
 * @Target : 指定被修饰的Annotaion能用于修饰哪些程序单元
 * @Retention : 指定被修饰的Annotation可以保留多长时间
 * @Documented : 指定被该元Annotation修饰的Annotaion类将被Javadoc工具提取成文档
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String name();
}
