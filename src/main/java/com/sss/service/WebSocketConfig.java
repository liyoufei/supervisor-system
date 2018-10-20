package com.sss.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.*;

//作为webSocket的配置文件作为注册客户端
@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //注册WebSocket
        String websocket_url = "/socketServer";
        //将控制器与url绑定，以及添加拦截器
        registry.addHandler(new WebSocketHandler(), websocket_url).
                addInterceptors(new WebSocketHandshakeInterceptor());

    }


}

