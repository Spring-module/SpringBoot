package com.wisely.highlight_spring4.ch3.taskscheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * �ƻ�����ִ���ࣺ
 * 1 ͨ�� @Scheduled �����÷����Ǽƻ�����ʹ�� fixedRate ����ÿ���̶�ʱ��ִ��
 * 2 ʹ�� cron ���Կɰ���ָ��ʱ��ִ�У�����ָ����ÿ��11��28��ִ�У�cron �� UNIX ���� UNIX��Linux��ϵͳ�µĶ�ʱ����
 */
@Service
public class ScheduledTaskService {
	
	  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	  @Scheduled(fixedRate = 5000) //1
	  public void reportCurrentTime() {
	       System.out.println("ÿ������ִ��һ�� " + dateFormat.format(new Date()));
	   }

	  @Scheduled(cron = "0 28 16 ? * *"  ) //2
	  public void fixTimeExecution(){
	      System.out.println("��ָ��ʱ�� " + dateFormat.format(new Date())+"ִ��");
	  }

}
