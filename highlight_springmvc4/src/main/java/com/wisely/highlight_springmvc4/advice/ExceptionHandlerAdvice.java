package com.wisely.highlight_springmvc4.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 定制 ControllerAdcice：
 * 1 @ControllerAdvice 声明一个控制器建言，@ControllerAdvice 组合了 @Component 注解，所以自动注册为Spring的Bean。
 * 2 @ExceptionHandler 在此处定义全局处理控制器里的异常，通过 @ExceptionHandler 的 value 属性可过滤拦截的条件，在此处我们可以看出我们拦截所有的Exception。
 * 3 此处使用 @ModelAttribute 注解将键值对添加到全局（Model里），所有注解的 @RequestMapping 的方法可获得此处设置的键值对。
 * 4 通过 @InitBinder 注解用来设置 WebDataBinder。WebDataBinder 用来自动绑定前后台请求参数到 Model 中。
 * 5 此处演示忽略 request 参数的 id，更过关于 WebDataBinder 的配置，请参考 WebDataBinder的API文档。
 */
@ControllerAdvice //1
public class ExceptionHandlerAdvice { 

	@ExceptionHandler(value = Exception.class)//2
	public ModelAndView exception(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("error");// error页面
		modelAndView.addObject("errorMessage", exception.getMessage());
		return modelAndView;
	}

	@ModelAttribute //3
	public void addAttributes(Model model) {
		model.addAttribute("msg", "额外信息"); //3
	}

	@InitBinder //4
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("id"); //5
	}
}
