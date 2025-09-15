package com.acmhuang.ai.aicodehlper.ai.model;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Acmhuang
 * @date 2025/09/15 20:47
 **/

@Configuration
@ConfigurationProperties(prefix = "langchain4j.community.dashscope.chat-model")
@Data
public class QwenChatModelConfig {

    private String modelName;

    private String apiKey;

    @Resource
    private ChatModelListener chatModelListener;


    @Bean
    public QwenChatModel myQwenChatModel(){
        return QwenChatModel.builder()
                .modelName(modelName)
                .apiKey(apiKey)
                .listeners(List.of(chatModelListener))
                .build();
    }
}