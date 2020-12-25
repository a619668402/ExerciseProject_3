package com.test.redisspringboot;

import com.test.redisspringboot.model.User;
import com.test.redisspringboot.utils.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootTest
class RedisSpringBootApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

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
        User user1 = (User) redisTemplate.opsForValue().get("user");
        System.out.println(user1.getUserName());
        System.out.println(user1.getAge());
    }

    @Test
    public void test1() {
//        redisTemplate.opsForValue().set("user:1","测试");
//        redisTemplate.opsForValue().set("key5", "val5");
//        System.out.println(redisTemplate.opsForValue().get("key2"));
        System.out.println(redisTemplate.opsForValue().get("key5"));
    }

    @Test
    public void test2() {
        System.out.println(redisUtil.get("key2"));
    }

    @Test
    public void test3() {
        User user = new User();
        user.setAge(20);
        user.setUserName("张三");
        redisUtil.set("user:1", user);
        redisUtil.hset("user:2", "username", "zhangsan");
        redisUtil.hset("user:2", "password", "123456");
        System.out.println(redisUtil.get("user:1"));
    }

    @Test
    public void test5() {
        List list = new ArrayList();
        list.add("one");
        list.add("two");
        list.add("three");
//        redisUtil.lSet("users", list);
        System.out.println(redisUtil.lGet("users", 0, -1));
//        redisUtil.expire("users", 60);
    }
}
