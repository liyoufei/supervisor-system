package com.sss.service;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;

import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

    //在建立连接前调用拦截器
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> attributes){
        if (request instanceof ServletServerHttpRequest) {
            // 获取请求参数，首先我们要获取HttpServletRequest对象才能获取请求参数；当ServerHttpRequset的层次结构打开后其子类可以获取到我们想要的http对象，那么就简单了。
            // 我这里是把获取的请求数据绑定到session的map对象中（attributes）
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            // 标记用户
            String uid = servletRequest.getParameter("uid");
            attributes.put("uid", uid);
            if(uid!=null){
                attributes.put("uid", uid);
            }else{
                return false;
            }
        }
        return true;

    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
    }
}
