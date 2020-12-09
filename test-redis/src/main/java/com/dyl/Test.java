package com.dyl;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

public class Test {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", "zhangsan");
        jsonObject.put("password", "123456");
        String str = jsonObject.toString();
        Transaction transaction = jedis.multi();
        try {
            transaction.set("user1", str);
            transaction.set("user2", str);
            transaction.exec();
        } catch (Exception e) {
            transaction.discard();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();
        }
        jedis.close();
    }
}

