package com.acmhuang.ai.aicodehlper.ai;

import dev.langchain4j.service.Result;
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

    @Test
    void chatForReport() {
        String chat = "你好我是程序员ming，学习编程两年半，请帮我制定学习报告";
        AiCodeHelperService.Report report = aiCodeHelperService.chatForReport(chat);
        System.out.println(report);
    }

    @Test
    void chatWithRag(){
        Result<String> result = aiCodeHelperService.chatWithRag("怎么学习java？有哪些常见的面试题？");
        System.out.println(result.sources());
        System.out.println(result.content());
    }

    @Test
    void chatWithTools(){
        String chat = aiCodeHelperService.chat("有哪些常见的计算机网络面试题？");
        System.out.println(chat);
    }
}