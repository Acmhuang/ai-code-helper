package com.acmhuang.ai.aicodehlper.ai.guardrail;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.guardrail.InputGuardrail;
import dev.langchain4j.guardrail.InputGuardrailResult;

import java.util.Set;

/**
 * @author Acmhuang
 * @date 2025/09/15 20:26
 **/
public class SafeInputGuardrail implements InputGuardrail {

    //敏感词集合
    private static final Set<String> SENSITIVE_WORDS = Set.of("kill", "evil");

    @Override
    public InputGuardrailResult validate(UserMessage userMessage) {
        String input = userMessage.singleText().toLowerCase();
        String[] words = input.split("\\W+");
        for (String word : words) {
            if (SENSITIVE_WORDS.contains(word)) {
                return fatal("Input contains sensitive words" + word);
            }
        }
        return success();
    }
}