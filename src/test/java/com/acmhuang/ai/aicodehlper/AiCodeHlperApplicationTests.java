package com.acmhuang.ai.aicodehlper;

import com.acmhuang.ai.aicodehlper.ai.AiCodeHelper;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;

@SpringBootTest
class AiCodeHlperApplicationTests {

    @Resource
    private AiCodeHelper aiCodeHelper;

    @Test
    void chat() {
        aiCodeHelper.chat("你好我是一个正在学习langchain4j的Java程序员");
    }

    @Test
    void testChat(){
        UserMessage userMessage = UserMessage.from(
                TextContent.from("描述图片"),
                ImageContent.from("https://i0.hdslb.com/bfs/new_dyn/edc297f0e77c71348bf24cff4c2174fb253428607.gif@480w_480h.avif")
        );
        aiCodeHelper.chat(userMessage);
    }

}
