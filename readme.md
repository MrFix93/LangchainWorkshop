# LangChain Workshop

![Info Support Swagshop](is-swag.jpg)

# Info Support Swagshop

Stel je een webshop voor waar je de meest gave Info Support Swag kunt vinden: denk aan stijlvolle hoodies, unieke gadgets, en toffe accessoires die je met trots kunt dragen op conferenties, bij klanten of gewoon in je vrije tijd. Deze webshop biedt niet alleen de coolste producten, maar maakt het ook super eenvoudig om precies te vinden wat je zoekt.

In deze workshop gaan we aan de slag om een deel van deze webshop te realiseren. We richten ons op het bouwen van een AI-chatbot waarmee je als gebruiker kunt vragen welke producten er beschikbaar zijn en direct bestellingen kunt plaatsen. Daarnaast kun je via de chatbot ook vragen stellen over de producten zelf. Samen werken we toe naar een webshop waar de gaafste Info Support items te koop zijn, zodat jij ze kunt laten zien waar en wanneer je maar wilt. Met behulp van Langchain technologie zullen we deze innovatieve en interactieve winkelervaring creëren.

# Wat is LangChain4j?

LangChain4j is een library dat helpt met het integreren van Artificial Intelligence (AI) in je Java project. Het komt met twee grote voordelen:

1. Uniforme API's: je hoeft je niet langer druk te maken over particuliere APIs van verschillende Large Language Model (LLM) leveranciers of embedding/vector stores. Met LangChain4j kan je makkelijk wisselen tussen leveranciers zonder je code te hoeven herschrijven. Op hun eigen GitHub zeggen ze ook al: "Think of it as a Hibernate, but for LLMs and embedding stores".

1. Comprehensive Toolbox: LangChain4j komt met een lading verschillende tools en handigheidjes voor ons ontwikkelaars om te gebruiken naar het verlangen van ons hart. Dit gaat van simpele prompt templates tot het opzetten van volwaardig Retrieval-Augmented Generation (RAG).


# Basic integratie met Large Language Model (LLM)
LangChain4j maakt het beginnen met AI in Java zo simpel als de volgende paar stappen:

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

1. Indien van toepassing: verkrijg een API key voor de LLM provider. LangChain4j biedt op hun Github pagina een gratis demo key om met, onder andere, ChatGPT te kunnen testen (https://github.com/langchain4j/langchain4j).
1. Creëer een instantie van het gekozen model in je code, en je kunt al beginnen met chatten! Onderstaande code snippet toont hoe je met twee regels code al met ChatGPT kunt chatten in je app!

   ```java
   ChatLanguageModel model = OpenAiChatModel.withApiKey(yourApiKey);

   String answer = model.generate("Hello World! Greetings from Info Support!");

   System.out.println(answer); // Hello Info Support! It's great to connect with you. How are you doing today?
   ```


# Tools (Function Calling)

Tools (of Function Calling) geeft je LLM de mogelijkheid om een of meer Tools aan te roepen om zo het gewenste resultaat te krijgen. Een Tool is wat je er zelf van maakt: een API aanroep, een database zoekopdracht, een stukje code uitvoeren, etc. De LLM roept de code niet direct aan, het model geeft de intentie aan om een Tool aan te roepen en LangChain4j zorgt ervoor dat de methode uitgevoerd wordt en het resultaat teruggegeven wordt aan de LLM. 

Een klein voorbeeld: stel je wilt een chatbot de belasting kunnen uitrekenen over een bepaalde prijs, maar de chatbot is slecht in rekenen. Je kunt dan een Tool definieren om het voor de chatbot uit te rekenen, zodat de chatbot de tool kan gebruiken om het juiste antwoordt te krijgen. In code ziet dat er ongeveer zo uit:

```java
static double BTW = 0.21d;

@Tool("Berekend de BTW op een gegeven prijs")
double berekenBTW(double prijs){
    return prijs * BTW;
}
```

Als je nu aan de chatbot vraagt om de BTW te berekenen over een bepaalde prijs, kan de chatbot de `berekenBTW(..)` methode aanroepen en het resultaat gebruiken om de gebruiker het juiste antwoord te geven.
Het is mogelijk extra context aan het model te geven via de ```@P(..)``` annotatie op de Tool parameter(s).

Voor meer informatie over Tools, zie de LangChain4j documentatie: https://docs.langchain4j.dev/tutorials/tools/.

# Retrieval Augmented Generation (RAG)

RAG is een combinatie van verschillende technieken om LLM's te voorzien van domein specifieke kennis of particuliere data. Door relevante informatie uit lokale bronnen mee te sturen in de prompt, kan de LLM deze extra informatie gebruiken om een antwoord te formuleren.

Simpel gesproken bestaat RAG uit twee onderdelen/processen: de inname van data, en het ophalen van data. LangChain4j biedt de nodige tools om beide data pipelines op te zetten.

![Data inname pipeline van RAG](rag-ingestion.png)
De "data ingestion" pipeline.

![Data ophalen pipeline van RAG](rag-retrieval.png)
De "data retrieval" pipeline.

Zoals je ziet komen er veel onderdelen kijken bij het opzetten van RAG. Gelukkig maakt LangChain4j het sinds kort makkelijk door middel van "EasyRAG".

EasyRAG komt als een aparte dependency en is bedoeld om je snel met RAG te laten spelen, zonder al te veel te hoeven nadenken over de ingestion en retrieval pipelines, vector stores of embedding models. Verwijs simpelweg naar de documenten en LangChain4j verzorgt de rest.

Om aan de slag te gaan met EasyRAG moet de Assistant met de volgende onderdelen uitgebreid worden:
1. Het laden van je documenten:
```java
List<Document> documents = FileSystemDocumentLoader.loadDocuments("/path/to/files");
```
2. Het aanmaken van een embedding store en het vullen ervan:
```java
InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
EmbeddingStoreIngestor.ingest(documents, embeddingStore);
```
3. Je AIService uitbreiden met de embedding store:
```java
.contentRetriever(EmbeddingStoreContentRetriever.from(embeddingStore))
```


Je kunt de nodige stappen voor EasyRAG hier teruglezen: https://docs.langchain4j.dev/tutorials/rag#easy-rag



# De opdracht

Nu is het tijd om zelf aan de slag te gaan met Tools en RAG!

## Tools

Breidt ```KlantTools.java``` uit zodat je de volgende handelingen, met behulp van de chatbot, kunt uitvoeren via de chat:
1. Aanmaken van een nieuwe klant en het ophalen ervan via naam of id.
2. Haal een product via naam of id op. 
3. Maak een nieuwe order aan.
4. Update een bestaande order met een extra orderline.
5. (Extra): Zorg dat de chatbot de totaalprijs van een bestelling kan teruggeven (incl. 21% btw!).

Alle nodige methodes zijn aanwezig, je moet enkel de Tools schrijven.

Tip voor het gebruiken van productnamen: gebruik de namen zoals te vinden in ```Database.init()```

### Benieuwd wat LangChain4j onderwater aan het doen is?
Je kunt de berichten die LangChain4j lokaal heen en weer stuurt met het model van keuze inzichtelijk krijgen door:
- In ```simplelogger.properties``` bestand loglevel op ```debug``` te zetten.
- In ```Configuration.java``` bij het aanmaken van het model, ```logRequests(..)``` en/of ```logResponses(..)``` op true te zetten.

## RAG

In de resources directory hebben we tekst bestanden met beschrijvingen en informatie over de voorwerpen.

Zorg ervoor dat je de chatbot vragen kunt stellen over de Info Support swag shop voorwerpen. Gebruik hiervoor LangChain4j's EasyRAG oplossing.

Je kunt het aanmaken en ingesten van de embedding store simpel in één methode plaatsen:
```java
    private static InMemoryEmbeddingStore<TextSegment> loadEmbeddingStore(String path) {
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        List<Document> documents = FileSystemDocumentLoader.loadDocuments(path);
        EmbeddingStoreIngestor.ingest(documents, embeddingStore);
        return embeddingStore;
    }
```

Deze kun je vervolgens zo aanmaken en gebruiken:
```java
  var embeddingStore = loadEmbeddingStore("path/to/files");

  return AiServices.builder(Assistant.class)
     ...
     .tools(new KlantTools())
     .contentRetriever(EmbeddingStoreContentRetriever.from(embeddingStore))
     ...
     .build();
```


Voorbeelden van vragen die de chatbot moet kunnen beantwoorden:
- **Vraag**: Wat is de prijs van een hoodie?
- **Antwoord**: 45 euro.


- **Vraag**: Waar kan ik de gratis poster ophalen?
- **Antwoord**: bij binnenkomst, bij de balie.

 
- **Vraag**: Hoe kom ik aan een gouden sleutelhanger? 
- **Antwoord**: alleen op verzoek

 
- **Vraag**: Welke maten t-shirts zijn nog beschikbaar? 
- **Antwoord**: Alles van XS t/m XXL, met uitzondering van L.


- **Vraag**: Hoeveel kost de 16 TB USB-stick? 
- **Antwoord**: 160 euro