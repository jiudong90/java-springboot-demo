package com.example.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class ScheduledTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private Integer count0 = 1;
    private Integer count1 = 1;
    private Integer count2 = 1;

    @Autowired
    private RedisService redisService;


//    @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime() throws InterruptedException {
//        System.out.println(String.format("---第%s次执行，当前时间为：%s", count0++, dateFormat.format(new Date())));
//    }
//
//    @Scheduled(fixedDelay = 5000)
//    public void reportCurrentTimeAfterSleep() throws InterruptedException {
//        System.out.println(String.format("===第%s次执行，当前时间为：%s", count1++, dateFormat.format(new Date())));
//    }
//
//    @Scheduled(cron = "0 0 1 * * *")
//    public void reportCurrentTimeCron() throws InterruptedException {
//        System.out.println(String.format("+++第%s次执行，当前时间为：%s", count2++, dateFormat.format(new Date())));
//    }

    @Scheduled(fixedRate = 300)
    public void aProducer() throws InterruptedException {
        Thread current = Thread.currentThread();
        log.debug("A {}", current.getName());
        //redisService.pushWithTrim("last_60_Messages", "A-"+dateFormat.format(new Date()), 60);
        redisService.pushWithTrim("last_60_Messages", "A-"+dateFormat.format(new Date()), 600);
        //Thread.sleep(20);
    }

    @Scheduled(fixedRate = 300)
    public void bProducer() throws InterruptedException {
        Thread current = Thread.currentThread();
        log.debug("B {}", current.getName());
        redisService.pushWithTrim("last_60_Messages", "B-"+dateFormat.format(new Date()), 600);
        //Thread.sleep(20);
    }

    @Scheduled(fixedRate = 300)
    public void cProducer() throws InterruptedException {
        Thread current = Thread.currentThread();
        log.debug("C {}", current.getName());
        redisService.pushWithTrim("last_60_Messages", "C-"+dateFormat.format(new Date()), 600);
        //Thread.sleep(20);
    }


}
