package org.example;

import com.alibaba.fastjson.JSONArray;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DynamicLoggerLevelTest {

    @Autowired
    private LoggerLevelSettingService loggerLevelSettingService;

    @Test
    public void test() {

        String json = "[{'loggerName':'org.example.DynamicLoggerLevelTest','level':'ERROR'}]";
        log.info("测试1");
        loggerLevelSettingService.setLoggerLevel(JSONArray.parseArray(json, LoggerConfig.class));
        log.error("测试2");
    }
}
