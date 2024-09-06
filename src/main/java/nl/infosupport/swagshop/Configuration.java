package nl.infosupport.swagshop;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import java.util.List;

public class Configuration {

    static Assistant assistant() {

        return AiServices.builder(Assistant.class)
                // Het bouwen van de Large Language Model, in dit geval is er gekozen voor OpenAI's ChatGPT
                .chatLanguageModel(OpenAiChatModel.builder()
                        // Tools en RAG werken alleen met betaalde API keys
                        .apiKey("API_KEY")
                        .modelName(OpenAiChatModelName.GPT_4)
                        .temperature(0d)
                        .build())
                // Een system message, hiermee geven we de chatbot wat achtergrond informatie en hoe deze moet reageren
                .systemMessageProvider((var x) -> "Je bent de chatbot voor de Info Support swag shop, een digitale winkel! Houdt antwoorden vriendelijk maar kort")
                .tools(new KlantTools())
                // In-memory chat geheugen, zodat chatbot context heeft van afgelopen 10 berichten.
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .build();
    }
}
