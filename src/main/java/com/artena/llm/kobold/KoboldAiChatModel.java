package com.artena.llm.kobold;

import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.output.Response;
import org.apache.commons.compress.utils.Lists;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;


public class KoboldAiChatModel implements ChatLanguageModel {

    public static final String EXTRA_API_KEY = "extra";

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl;
    private final String promptDescription;
    private final List<String> stopSequence;
    private final String apiType;
    private final int maxContextLength;
    private String promptMemory;
    private int maxLength;

    public KoboldAiChatModel(String baseUrl, String apiType, String promptMemory, String promptDescription,
                             List<String> stopSequence, int maxContextLength, int maxLength) {
        this.baseUrl = baseUrl;
        this.apiType = apiType;
        this.promptMemory = promptMemory;
        this.promptDescription = promptDescription;
        this.stopSequence = stopSequence;
        this.maxContextLength = maxContextLength;
        this.maxLength = maxLength;
        //
        HttpMessageConverter<String> stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        List<HttpMessageConverter<?>> httpMessageConverter = Lists.newArrayList();
        httpMessageConverter.add(stringHttpMessageConverter);
        restTemplate.setMessageConverters(httpMessageConverter);
    }

    public static KoboldAiChatModel.KoboldAiChatModelBuilder builder() {
        return new KoboldAiChatModelBuilder();
    }

    @Override
    public Response<AiMessage> generate(List<ChatMessage> list) {
        return generate(list.toArray(new ChatMessage[0]));
    }

    @Override
    public String generate(String prompt) {
        return "not implemented";
    }

    @Override
    public Response<AiMessage> generate(ChatMessage... messages) {
        return new Response<>(new AiMessage("not implemented"));
    }

    @Override
    public Response<AiMessage> generate(List<ChatMessage> messages, List<ToolSpecification> toolSpecifications) {
        return ChatLanguageModel.super.generate(messages, toolSpecifications);
    }

    @Override
    public Response<AiMessage> generate(List<ChatMessage> messages, ToolSpecification toolSpecification) {
        return ChatLanguageModel.super.generate(messages, toolSpecification);
    }

    public void setPromptMemory(String promptMemory) {
        this.promptMemory = promptMemory;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public static class KoboldAiChatModelBuilder {
        private String baseUrl;

        private String apiType;

        private List<String> stopSequence;

        private String promptDescription;

        private String promptMemory;

        private int maxContextLength;

        private int maxLength;

        public KoboldAiChatModel.KoboldAiChatModelBuilder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public KoboldAiChatModel.KoboldAiChatModelBuilder apiType(String apiType) {
            this.apiType = apiType;
            return this;
        }

        public KoboldAiChatModel.KoboldAiChatModelBuilder promptDescription(String promptDescription) {
            this.promptDescription = promptDescription;
            return this;
        }

        public KoboldAiChatModel.KoboldAiChatModelBuilder promptMemory(String promptMemory) {
            this.promptMemory = promptMemory;
            return this;
        }

        public KoboldAiChatModelBuilder stopSequence(List<String> stopSequence) {
            this.stopSequence = stopSequence;
            return this;
        }

        public KoboldAiChatModel build() {
            return new KoboldAiChatModel(this.baseUrl, this.apiType, this.promptMemory,
                    this.promptDescription, this.stopSequence, this.maxContextLength, this.maxLength);
        }

        public KoboldAiChatModelBuilder setMaxContextLength(int maxContextLength) {
            this.maxContextLength = maxContextLength;
            return this;
        }

        public KoboldAiChatModelBuilder setMaxLength(int maxLength) {
            this.maxLength = maxLength;
            return this;
        }

        public String toString() {
            return "KoboldAiChatModel.KoboldAiChatModelBuilder(baseUrl=" + this.baseUrl +
                    ", apiType=" + this.apiType + ", promptMemory=" + promptMemory +
                    ", promptDescription=" + this.promptDescription + ", stopSequence=" +
                    this.stopSequence + ", maxContextLength=" + maxContextLength +
                    ", maxLength=" + maxLength + ")";
        }
    }
}
