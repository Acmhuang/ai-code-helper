package com.acmhuang.ai.aicodehlper.controller;

import com.acmhuang.ai.aicodehlper.ai.AiCodeHelperService;
import jakarta.annotation.Resource;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * @author Acmhuang
 * @date 2025/09/16 19:13
 **/
@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = "http://localhost:3000")
public class AiController {

    @Resource
    private AiCodeHelperService aiCodeHelperService;

    @GetMapping("/chat")
    public Flux<ServerSentEvent<String>> chat(int memoryId, String message) {
        return aiCodeHelperService.chatStream(memoryId,message)
                .map(chunk -> ServerSentEvent.<String>builder()
                        .data(chunk)
                        .build());
    }
}