package nl.infosupport.swagshop;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import dev.langchain4j.service.AiServices;

public class Configuration {

    static Assistant assistant() {
        return AiServices.builder(Assistant.class)
                .chatLanguageModel(OpenAiChatModel.builder()
                        // Tools only work with paid openai license, this is not documented anywhere...
                        .apiKey("")
                        .modelName(OpenAiChatModelName.GPT_4)
                        .temperature(0d)
                        .build())
                .tools(new KlantTools())
                .chatMemory(MessageWindowChatMemory.withMaxMessages(5))
                .build();
    }
}
