package com.sss.controller;


import com.sss.service.MyWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;



@Controller
public class IndexController {
    //spring依赖注入
    @Bean
    public MyWebSocketHandler infoHandler() {
        return new MyWebSocketHandler();
    }

    //访问index管理页面
    @RequestMapping("/admin")
    public ModelAndView handleRequest() {
        //模型视图定位器
        ModelAndView mav = new ModelAndView("admin");
        ArrayList<String> userName = new ArrayList<>();
        int count = MyWebSocketHandler.sessionMap.size();
        userName.addAll(MyWebSocketHandler.sessionMap.keySet());

        mav.addObject("name",userName.toString());
        mav.addObject("count",count-1);
        return mav;

    }

    //访问客户端页面
    @RequestMapping("/client")
    public ModelAndView client() {
        return new ModelAndView("client");
    }




}
