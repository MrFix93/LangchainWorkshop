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

        var embeddingStore = loadEmbeddingStore("src/main/resources/voorwerpen");

        return AiServices.builder(Assistant.class)
                .chatLanguageModel(OpenAiChatModel.builder()
                        // Tools only work with paid openai license, this is not documented anywhere...
                        .apiKey("")
                        .modelName(OpenAiChatModelName.GPT_4)
                        .temperature(0d)
                        .build())
                .systemMessageProvider((var x) -> "Je bent de chatbot voor de Info Support swag shop, een digitale winkel! Houdt antwoorden vriendelijk maar kort")
                .tools(new KlantTools())
                .contentRetriever(EmbeddingStoreContentRetriever.from(embeddingStore))
                .chatMemory(MessageWindowChatMemory.withMaxMessages(5))
                .build();
    }

    private static InMemoryEmbeddingStore<TextSegment> loadEmbeddingStore(String path) {
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        List<Document> documents = FileSystemDocumentLoader.loadDocuments(path);
        EmbeddingStoreIngestor.ingest(documents, embeddingStore);
        return embeddingStore;
    }
}
