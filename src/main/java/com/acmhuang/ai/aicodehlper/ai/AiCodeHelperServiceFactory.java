package com.acmhuang.ai.aicodehlper.ai;

import com.acmhuang.ai.aicodehlper.ai.tools.InterviewQuestionTool;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.spring.AiService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Acmhuang
 * @date 2025/09/14 15:07
 **/
@Configuration//更推荐这种使用工厂方式创建AiCodeHelperService
public class AiCodeHelperServiceFactory {

    @Resource
    private ChatModel myQwenChatModel;

    @Resource
    private ContentRetriever contentRetriever;

    @Resource
    private McpToolProvider mcpToolProvider;

    @Bean
    public AiCodeHelperService createAiCodeHelperService() {
        //会话记忆
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        //构造Ai Service
        AiCodeHelperService aiCodeHelperService = AiServices.builder(AiCodeHelperService.class)
                .chatMemory(chatMemory)
                //会话记忆
                .chatModel(myQwenChatModel)
                //使用memoryId创建会话记忆，对不同的对话记忆隔离
                //.chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
                //RAG检索增强生成
                .contentRetriever(contentRetriever)
                .tools(new InterviewQuestionTool())
                .toolProvider(mcpToolProvider)
                .build();
        return aiCodeHelperService;
    }
}