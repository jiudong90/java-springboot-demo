package com.example.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerTask {

    @Autowired
    private RedisService redisService;


    @Scheduled(fixedRate = 1000)
    public void consumer() {
        Thread current = Thread.currentThread();
        log.debug("consumer {}", current.getId());
        String results;
        results = redisService.popWithElementes("last_60_Messages", 10);
        log.debug(results);
    }
}
