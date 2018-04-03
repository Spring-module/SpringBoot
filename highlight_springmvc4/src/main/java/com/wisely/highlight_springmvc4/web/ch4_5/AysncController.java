package com.wisely.highlight_springmvc4.web.ch4_5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.wisely.highlight_springmvc4.service.PushService;

/**
 * 双向通信技术：
 * Servlet3.0+异步方法处理，演示控制器，跨浏览器：
 * 异步任务的实现是通过控制器从一个线程返回一个 DeferredResult，这里的 DeferredResult 是从 pushService 中获得的。
 * 1 定时任务，定时更新 DeferredResult。
 * 2 返回给客户端 DeferredResult。
 */
@Controller
public class AysncController {
    @Autowired
    PushService pushService; //1

    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> deferredCall() { //2
        return pushService.getAsyncUpdate();
    }

}

