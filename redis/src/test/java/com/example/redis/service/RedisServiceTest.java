package com.example.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void lPush() {
        String type = "mitsubishi";
        redisService.rPush("ROUTING", "MachineType:i5");
        redisService.rPush("ROUTING", "MachineType:siemens");
        redisService.rPush("ROUTING", "MachineType:fanuc");
        redisService.rPush("ROUTING", type);
    }

    @Test
    public void lPop() {
        redisService.lPop("ROUTING");
    }

    @Test
    public void lRange() {
    }

    @Test
    public void PushWithFixLen() {
        int i = 0;
        try {
            do {
//            redisService.pushWithFixLen("last_10_Messages", "2222", 10);
                redisService.pushWithTrim("last_60_Messages", i, 60);
                i++;
                Thread.sleep(100);
            } while (true);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void popWithElements() {
        String results;
        try {
            do {
                results = redisService.popWithElementes("last_60_Messages", 10);
                //System.out.println(results);
                log.debug(results);
                Thread.sleep(1000);
            } while (true);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}