package com.wisely.highlight_spring4.ch3.conditional;

/**
 * Windows����Ҫ������ Bean����
 */
public class WindowsListService implements ListService {

	@Override
	public String showListCmd() {
		return "dir";
	}

}