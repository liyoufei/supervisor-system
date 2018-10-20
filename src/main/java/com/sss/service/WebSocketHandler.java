package com.sss.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;

public class WebSocketHandler extends TextWebSocketHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(WebSocketHandler.class);

    //WebSocketSession与name之间的映射
    public static ConcurrentHashMap<String,WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    //建立连接后调用
    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        sessionMap.put(username,session);
        LOGGER.info("用户 " + username + " Connection Established");

    }

    //处理客户端发送的文本信息
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        // 获取提交过来的消息详情
        LOGGER.debug("收到用户 " + username + "的消息:" + message.toString());
        //回复一条信息，
        session.sendMessage(new TextMessage("from server:" + message.getPayload()));
    }

    //连接关闭时调用
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        //异常结束时返回状态码
        LOGGER.info("用户 " + username + " Connection closed. Status: " + status);
        sessionMap.remove(username);
    }

    //处理二进制信息
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        super.handleBinaryMessage(session, message);
    }

    //处理传输错误的信息
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

}
