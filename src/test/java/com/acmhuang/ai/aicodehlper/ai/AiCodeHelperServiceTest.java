package com.acmhuang.ai.aicodehlper.ai;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiCodeHelperServiceTest {

    @Resource
    private AiCodeHelperService aiCodeHelperService;

    @Test
    void chat() {
        String chat = aiCodeHelperService.chat("你好我是程序员ming");
        System.out.println(chat);
    }
    @Test
    void chatWithMemory(){
        String chat = aiCodeHelperService.chat("请记住我叫Ming");
        System.out.println(chat);
        chat = aiCodeHelperService.chat("我叫什么名字");
        System.out.println(chat);
    }

}