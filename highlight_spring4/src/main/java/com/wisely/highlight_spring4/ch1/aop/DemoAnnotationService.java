package com.wisely.highlight_spring4.ch1.aop;

import org.springframework.stereotype.Service;

/**
 * ��д��ʹ��ע�⡱�ı�������
 */
@Service
public class DemoAnnotationService {
	@Action(name="ע��ʽ���ص�add����")
    public void add(){
		System.out.println("DemoAnnotationService -> add");
	} 
   
}
