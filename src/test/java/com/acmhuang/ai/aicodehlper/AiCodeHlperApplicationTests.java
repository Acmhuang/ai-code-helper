package com.acmhuang.ai.aicodehlper;

import com.acmhuang.ai.aicodehlper.ai.AiCodeHelper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AiCodeHlperApplicationTests {

    @Resource
    private AiCodeHelper aiCodeHelper;

    @Test
    void chat() {
        aiCodeHelper.chat("你好我是一个正在学习langchain4j的Java程序员");
    }

}
