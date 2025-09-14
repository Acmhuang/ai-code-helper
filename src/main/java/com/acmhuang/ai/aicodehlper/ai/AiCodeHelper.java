package com.acmhuang.ai.aicodehlper.ai;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Acmhuang
 * @date 2025/09/14 11:39
 **/
@Service
@Slf4j
public class AiCodeHelper {

    @Resource
    private ChatModel qwenChatModel;

    public String chat(String message){
        UserMessage userMessage = UserMessage.from(message);
        ChatResponse chatResponse = qwenChatModel.chat(userMessage);
        AiMessage aiMessage = chatResponse.aiMessage();
        log.info("aiMessage: {}", aiMessage);
        return aiMessage.text();
    }
}