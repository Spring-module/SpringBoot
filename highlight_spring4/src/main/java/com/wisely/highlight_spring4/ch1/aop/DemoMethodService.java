package com.wisely.highlight_spring4.ch1.aop;

import org.springframework.stereotype.Service;

/**
 * ��дʹ�÷�������������
 */
@Service
public class DemoMethodService {
	public void add(){
		System.out.println("DemoMethodService -> add");
	}
}
