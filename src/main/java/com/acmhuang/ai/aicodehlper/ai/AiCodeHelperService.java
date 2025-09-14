package com.acmhuang.ai.aicodehlper.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

//@AiService// AiService注解 创建一个代理类，就无需使用工厂创建但不推荐
public interface AiCodeHelperService {

    @SystemMessage(fromResource = "system-prompt.txt")
    String chat(String userMessage);
}
