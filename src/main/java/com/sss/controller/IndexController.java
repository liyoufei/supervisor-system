package com.sss.controller;


import com.sss.service.WebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;



@Controller
public class IndexController {
    //spring依赖注入
    @Bean
    public WebSocketHandler infoHandler() {
        return new WebSocketHandler();
    }

    //访问index管理页面
    @RequestMapping("/index")
    public ModelAndView handleRequest() {
        //模型视图定位器
        ModelAndView mav = new ModelAndView("index");
        ArrayList<String> userName = new ArrayList<>();
        int count = WebSocketHandler.sessionMap.size();
        userName.addAll(WebSocketHandler.sessionMap.keySet());
        mav.addObject("name",userName.toString());
        mav.addObject("count",count);
        return mav;

    }

    //访问客户端页面
    @RequestMapping("/websocket")
    public ModelAndView client() {
        return new ModelAndView("websocket");
    }

    //客户端连接
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request) {
        String username = request.getParameter("username");
        //通过httpsession间接传递username(因为这是页面跳转的控制器不应该直接设定websocketsession)
        HttpSession session = request.getSession();
        session.setAttribute("SESSION_USERNAME", username);
        return new ModelAndView("client");

    }


}
