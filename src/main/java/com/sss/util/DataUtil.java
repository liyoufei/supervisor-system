package com.sss.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketHandler;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataUtil {

    private final static Logger logger = LoggerFactory.getLogger(DataUtil.class);
    private static Jedis jedis = new Jedis("localhost");
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");


    /**
     * 将获取到的消息存入redis中
     * @param id 设备id
     * @param data 设备数据
     * @param date 时间
     */
    public  void saveData(String id,String data, Date date){
        Map<String,String> concurrentHashMap = new HashMap<>();
        //将时间作为键值，显示不同时间的数据
        concurrentHashMap.put(simpleDateFormat.format(date),data);
        jedis.hmset(id,concurrentHashMap);
    }

    /**
     * 获取数据
     * @param id 键值，设备ID
     * @return 目前先返回所有的数据
     */
    public  Map<String, String> getData(String id){
        return jedis.hgetAll(id);
    }


}
