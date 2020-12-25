package com.test.testspringboot.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduldService {

    // cron 表达式
    // 秒 分 时 日 月 周几
    @Scheduled(cron = "30 * * * * *")
    public void testSchedule() {
        System.out.println("--------testSchedule");
    }
}
