import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.*;


@RunWith(JUnit4.class)
public class Demo {

    @Test
    public void test() {
        Jedis jedis = new Jedis("localhost");
        System.out.println("已连接");
        jedis.set("name", "sss");
        System.out.println(jedis.get("name"));
    }

    @Test
    public void date() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        System.out.println(simpleDateFormat.format(date));
    }

    @Test
    public void sort() {

        Set<String> set = new TreeSet<String>(new MyComparator());

        set.add("5:13:22");
        set.add("4:22:22");
        set.add("4:11:33");
        for(Iterator<String> iterator = set.iterator(); iterator.hasNext();){
            System.out.print(iterator.next()+" ");
        }
    }

    class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);//升序排列
        }
    }
}
