package com.wisely.highlight_spring4.ch2.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * �¼�������
 * 	ʵ�� ApplicationListener�ӿڣ���ָ���������¼����͡�
 * 	ʹ�� onApplicationEvent��������Ϣ���н��ܴ���
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {

	public void onApplicationEvent(DemoEvent event) {
		
		String msg = event.getMsg();
		
		System.out.println("��(bean-demoListener)���ܵ���bean-demoPublisher��������Ϣ:"
				+ msg);

	}

}
