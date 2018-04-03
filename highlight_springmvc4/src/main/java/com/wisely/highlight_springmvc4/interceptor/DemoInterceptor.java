package com.wisely.highlight_springmvc4.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 示例拦截器：
 * 1 继承 HandlerInterceptorAdapter 类来实现自定义拦截器。
 * 2 重写 preHandle 方法，在请求发生前执行。
 * 3 重写 postHandle 方法，在请求完成后执行。
 */
public class DemoInterceptor extends HandlerInterceptorAdapter {//1

	@Override
	public boolean preHandle(HttpServletRequest request, //2
			HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, //3
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		request.removeAttribute("startTime");
		long endTime = System.currentTimeMillis();
		System.out.println("本次请求处理时间为:" + new Long(endTime - startTime)+"ms");
		request.setAttribute("handlingTime", endTime - startTime);
	}

}
