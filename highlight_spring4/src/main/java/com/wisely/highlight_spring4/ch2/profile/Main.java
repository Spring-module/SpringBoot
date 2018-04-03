package com.wisely.highlight_spring4.ch2.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		  AnnotationConfigApplicationContext context =  
				  new AnnotationConfigApplicationContext();
		  
		  context.getEnvironment().setActiveProfiles("dev"); //1 �Ƚ���� Profile ����Ϊ prod
		  context.register(ProfileConfig.class);//2 ����ע�� Bean �����࣬��Ȼ�ᱨ Bean δ����Ĵ���
		  context.refresh(); //3 ˢ������
		  
	      DemoBean demoBean = context.getBean(DemoBean.class);
	      
	      System.out.println(demoBean.getContent());
	      
	      context.close();
	}
}
