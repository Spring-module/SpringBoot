package com.wisely.highlight_spring4.ch1.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ��д���ع����ע�⣺
 * @Target : ָ�������ε�Annotaion������������Щ����Ԫ
 * @Retention : ָ�������ε�Annotation���Ա����೤ʱ��
 * @Documented : ָ������ԪAnnotation���ε�Annotaion�ཫ��Javadoc������ȡ���ĵ�
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String name();
}
