package com.example.devplatform;

import com.devplatform.DevplatformApplication;
import com.devplatform.redis.RedisUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevplatformApplication.class)
class DevplatformApplicationTests {

    @Autowired
    RedisUtil redisUtil;

    @Test
    void contextLoads() {
        redisUtil.set("ff",123);
        redisUtil.expire("ff",30);
        System.out.println(redisUtil.get("ff"));
    }
}
