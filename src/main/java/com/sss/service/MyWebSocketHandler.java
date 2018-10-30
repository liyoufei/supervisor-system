package com.sss.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MyWebSocketHandler implements WebSocketHandler {

    private final static Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);
    //WebSocketSession与name之间的映射
    public static ConcurrentHashMap<String,WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    // websocket连接建立
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String uid = (String) session.getAttributes().get("uid");
          logger.info(String.format("userId:%s", uid));
        if (sessionMap.get(uid) == null) {
            sessionMap.put(uid, session);
        }
    }

    // 消息处理
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage message) throws Exception {

        //接收数据并将其传送到管理员
        try{
            sendDataToAdmin(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 给管理员界面发送数据
     * @param message
     * @throws IOException
     */
    public void sendDataToAdmin(WebSocketMessage message) throws IOException{
        WebSocketSession socketSession = sessionMap.get("admin");
        if(socketSession != null && socketSession.isOpen()){
            //logger.info(message.getPayload().toString());
            socketSession.sendMessage(message);
        }
    }

    // 消息传输错误
    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        logger.error("Websocket Transport Error");
        if (session.isOpen()) {
            session.close();
        }
        removeSession(session);
    }

    // websocket 连接关闭
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        // TODO Auto-generated method stub
        logger.info(String.format("Websocket:[userId=%s]已经关闭",(String) session.getAttributes().get("uid")));
        removeSession(session);
    }

    // 移除Socket会话
    private void removeSession(WebSocketSession session) {
        Iterator<Map.Entry<String, WebSocketSession>> iter = sessionMap.entrySet().iterator();
        while (iter.hasNext()) {
            ConcurrentHashMap.Entry entry = iter.next();
            if (entry.getValue() == session) {
                sessionMap.remove(entry.getKey());
                logger.info(String.format("Socket会话已经移除:用户ID:%s", entry.getKey()));
            }
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给某个用户发送消息
     *
     * @param uid
     * @param message
     * @throws IOException
     */
    public void sendMessageToUser(String uid, TextMessage message)
            throws IOException {
        WebSocketSession session = sessionMap.get(uid);
        if (session != null && session.isOpen()) {
            session.sendMessage(message);
        }
    }


}
