package com.dyl.redisspringboot;

import com.dyl.redisspringboot.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

@SpringBootTest
class RedisSpringBootApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        // 操作字符串
        Set user = redisTemplate.opsForSet().members("user");

        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.flushAll();
//        connection.flushDb();
        redisTemplate.opsForValue().set("user1", "hehe");
        Object user1 = redisTemplate.opsForValue().get("user1");
        System.out.println(user1);
    }

    @Test
    public void test() throws JsonProcessingException {
        User user = new User("zhangsan", 30);
        String string = new ObjectMapper().writeValueAsString(user);
//        redisTemplate.opsForValue().set("user", string);
        redisTemplate.opsForValue().set("user", user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

}
