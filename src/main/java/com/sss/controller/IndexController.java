package com.sss.controller;


import com.sss.service.MyWebSocketHandler;
import com.sss.util.DataUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Map;


@Controller
public class IndexController {
    //spring依赖注入
    @Bean
    public MyWebSocketHandler infoHandler() {
        return new MyWebSocketHandler();
    }
    //获取数据存取工具类
    private static DataUtil dataUtil = new DataUtil();

    //访问index管理页面
    @RequestMapping("/admin")
    public ModelAndView handleRequest() {
        //模型视图定位器
        ModelAndView mav = new ModelAndView("admin");
        ArrayList<String> userName = new ArrayList<>();
        int count = MyWebSocketHandler.sessionMap.size();
        userName.addAll(MyWebSocketHandler.sessionMap.keySet());

        mav.addObject("name",userName.toString());
        mav.addObject("count",count);
        return mav;

    }

    /**
     * 将搜索的数据返回给页面
     * @param id 设备id
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> search(@RequestParam (value = "id") String id) {
        System.out.println(id);
        return dataUtil.getData(id);
    }

    //访问客户端页面
    @RequestMapping("/client")
    public ModelAndView client() {
        return new ModelAndView("client");
    }

    //访问图表界面
    @RequestMapping("/chart")
    public ModelAndView chart(){
        return new ModelAndView("chart");
    }


    @RequestMapping("/test")
    public ModelAndView test(){
        return new ModelAndView("client");
    }



}
