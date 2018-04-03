package com.wisely.highlight_spring4.ch3.conditional;

/**
 * Linux下所要创建的Bean的类
 */
public class LinuxListService implements ListService{

	@Override
	public String showListCmd() {
		return "ls";
	}

}
