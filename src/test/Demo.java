import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {

    @Test
    public void test(){
        Jedis jedis = new Jedis("localhost");
        System.out.println("已连接");
        jedis.set("name","sss");
        System.out.println(jedis.get("name"));
    }

    @Test
    public void date(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        System.out.println(simpleDateFormat.format(date));
    }

}
