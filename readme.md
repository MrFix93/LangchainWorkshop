# LangChain Workshop

![Info Support Swagshop](is-swag.jpg)

## Info Support Swagshop

Stel je een webshop voor waar je de meest gave Info Support Swag kunt vinden: denk aan stijlvolle hoodies, unieke gadgets, en toffe accessoires die je met trots kunt dragen op conferenties, bij klanten of gewoon in je vrije tijd. Deze webshop biedt niet alleen de coolste producten, maar maakt het ook super eenvoudig om precies te vinden wat je zoekt.

In deze workshop gaan we aan de slag om een deel van deze webshop te realiseren. We richten ons op het bouwen van een AI-chatbot waarmee je als gebruiker kunt vragen welke producten er beschikbaar zijn en direct bestellingen kunt plaatsen. Daarnaast kun je via de chatbot ook vragen stellen over de producten zelf. Samen werken we toe naar een webshop waar de gaafste Info Support items te koop zijn, zodat jij ze kunt laten zien waar en wanneer je maar wilt. Met behulp van Langchain technologie zullen we deze innovatieve en interactieve winkelervaring creëren.

## Wat is LangChain4j?

LangChain4j is een library dat helpt met het integreren van Artificial Intelligence (AI) in je Java project. Het komt met twee grote voordelen:

1. Uniforme API's: je hoeft je niet langer druk te maken over particuliere APIs van verschillende Large Language Model (LLM) leveranciers of embedding/vector stores. Met LangChain4j kan je makkelijk wisselen tussen leveranciers zonder je code te hoeven herschrijven. Op hun eigen GitHub zeggen ze ook al: "Think of it as a Hibernate, but for LLMs and embedding stores".

1. Comprehensive Toolbox: LangChain4j komt met een lading verschillende tools en handigheidjes voor ons ontwikkelaars om te gebruiken naar het verlangen van ons hart. Dit gaat van simpele prompt templates tot het opzetten van volwaardig Retrieval-Augmented Generation (RAG).

LangChain4j maakt met het beginnen van AI in Java zo simpel als de volgende paar stappen:

1. Voeg de LangChain4j dependency, en een LangChain4j model dependeny (zoals OpenAI) toe aan je project. Voorbeeld:

   ```xml
   <dependency>
   <groupId>dev.langchain4j</groupId>
   <artifactId>langchain4j</artifactId>
   <version>0.33.0</version>
   </dependency>

   <dependency>
   <groupId>dev.langchain4j</groupId>
   <artifactId>langchain4j-open-ai</artifactId>
   <version>0.33.0</version>
   </dependency>
   ```

1. Indien van toepassing: verkrijg een API key voor de LLM provider. LangChain4j biedt op hun Github pagina een gratis demo key om met, onder andere, ChatGPT te kunnen testen.
1. Creëer een instantie van het gekozen model in je code, en je kunt al beginnen met chatten! Onderstaande code snippet toont hoe je met twee regels code al met ChatGPT kunt chatten in je app!

   ```java
   ChatLanguageModel model = OpenAiChatModel.withApiKey(yourApiKey);

   String answer = model.generate("Hello World! Greetings from Info Support!");

   System.out.println(answer); // Hello Info Support! It's great to connect with you. How are you doing today?
   ```

LangChain4j Github: https://github.com/langchain4j/langchain4j

## Retrieval Augmented Generation (RAG)

RAG is een combinatie van verschillende technieken om LLM's te voorzien van domein specifieke kennis of particuliere data. Door relevante informatie uit lokale bronnen mee te sturen in de prompt, kan de LLM deze extra informatie gebruiken om een antwoord te formuleren.

Simpel gesproken bestaat RAG uit twee onderdelen/processen: de inname van data, en het ophalen van data. LangChain4j biedt de nodige tools om beide data pipelines op te zetten.

![Data inname pipeline van RAG](rag-ingestion.png)
De "data ingestion" pipeline.

![Data ophalen pipeline van RAG](rag-retrieval.png)
De "data retrieval" pipeline.

Zoals je ziet komen er veel onderdelen kijken bij het opzetten van RAG. Gelukkig maakt LangChain4j het sinds kort makkelijk door middel van "EasyRAG".

.......?

Langchain4j RAG docs: https://docs.langchain4j.dev/tutorials/rag/
