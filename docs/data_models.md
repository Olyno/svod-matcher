# Data Models Documentation

## Core Entities

### ShowTitle
```java
public class ShowTitle {
    private final String rawTitle;
    private final String normalized;
    private final int releaseYear;
    
    // Builder pattern implementation
    public static class Builder { /*...*/ }
}
```
- **rawTitle**: Original title from source
- **normalized**: Lowercase, punctuation removed
- **releaseYear**: Year of first release

### Show
```java
public class Show {
    private final ShowTitle primaryTitle;
    private final List<ShowTitle> alternativeTitles;
    private final List<String> producers;
    
    // Builder pattern implementation
    public static class Builder { /*...*/ }
}
```

### CSVRow
```java
public class CSVRow {
    private final String externalId;
    private final String rawTitle;
    private final String producerString;
    
    // Builder pattern implementation
    public static class Builder { /*...*/ }
}
```

## Relationships
```mermaid
classDiagram
    CSVRow --> Show : "Transformed via"
    ShowTitle --> Show : "Primary title"
    ShowTitle --> Show : "Alternative titles"
    CSVRow --> ProducerParser : "Parsing"
    ShowMatcher --> MatchResult : "Generates"
