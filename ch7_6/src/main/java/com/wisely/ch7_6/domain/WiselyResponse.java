package com.wisely.ch7_6.domain;

/**
 * 服务端向浏览器发送的此类的消息
 */
public class WiselyResponse {
    private String responseMessage;
    public WiselyResponse(String responseMessage){
        this.responseMessage = responseMessage;
    }
    public String getResponseMessage(){
        return responseMessage;
    }
}