package demo.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import javax.annotation.PostConstruct;

/**
 * Created by sun on 18-6-28.
 */
@Component
public class Test {
    @Autowired
    private JedisCluster jedisCluster;

    @PostConstruct
    public void run() {
        for (int i = 0; i < 1; i++) {
            jedisCluster.set("redis"+i, "key"+i);
            String value= jedisCluster.get("redis"+i);
            System.err.println(value);
        }
    }
}
