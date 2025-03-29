# System Architecture

## High-Level Design

The Show Matching System follows a service-oriented architecture with clear separation of concerns:

```mermaid
graph TD
    A[CSV Data] --> B[CSVRow Model]
    B --> C[ShowMatcher]
    D[Show Database] --> C
    E[ShowTitle Database] --> C
    C --> F[MatchResult]
    G[ProducerParser] --> C
```

## Core Components

### ShowMatcher

The central component responsible for matching CSV rows against the show database:

- Indexes shows by ID and normalized title for efficient lookup
- Groups show titles by show ID for quick access
- Implements the matching algorithm
- Processes data in parallel for performance

### Data Models

- **Show**: Represents a show in the database with its metadata
- **ShowTitle**: Represents a title for a show in any language
- **CSVRow**: Represents a row from a CSV file to be matched
- **MatchResult**: Represents the result of a matching operation

### Utilities

- **ProducerParser**: Parses producer strings with different separators and normalizes producer names

## Design Patterns

The system implements several design patterns:

1. **Builder Pattern**: Used for creating complex objects like Show, ShowTitle, and CSVRow
2. **Immutable Objects**: All model classes use final fields and immutable collections
3. **Factory Methods**: Static factory methods for creating MatchResult objects
4. **Parallel Processing**: Parallel streams for efficient data processing

## Component Relationships

```mermaid
classDiagram
    class ShowMatcher {
        -List~Show~ shows
        -List~ShowTitle~ showTitles
        -Map~UUID, Show~ showsById
        -Map~UUID, List~ShowTitle~~ titlesByShowId
        -Map~String, List~Show~~ showsByNormalizedTitle
        +findMatches(List~CSVRow~) List~MatchResult~
        +findMatchesForRow(CSVRow) List~MatchResult~
    }
    
    class Show {
        -UUID id
        -List~String~ producers
        -Integer productionYear
        -String type
        -String originalTitle
    }
    
    class ShowTitle {
        -UUID showId
        -String language
        -String title
        -Integer episodeNumber
        -String episodeName
        -Integer seasonNumber
    }
    
    class CSVRow {
        -String originalTitle
        -String translatedTitle
        -String producers
        -Integer productionYear
        -String type
    }
    
    class MatchResult {
        -CSVRow csvRow
        -ShowTitle showTitle
        -boolean isMatch
        -String reason
        +static match(CSVRow, ShowTitle) MatchResult
        +static noMatch(CSVRow, ShowTitle, String) MatchResult
    }
    
    class ProducerParser {
        +static parse(String) List~String~
        +static normalizeProducerName(String) String
        +static hasCommonProducer(List~String~, List~String~) boolean
    }
    
    ShowMatcher --> Show : uses
    ShowMatcher --> ShowTitle : uses
    ShowMatcher --> CSVRow : processes
    ShowMatcher --> MatchResult : creates
    ShowMatcher --> ProducerParser : uses
    MatchResult --> CSVRow : references
    MatchResult --> ShowTitle : references
```

## Critical Implementation Paths

1. **Indexing**: Shows are indexed by normalized title during initialization
2. **Matching**: CSV rows are matched against shows using title containment, year, type, and producer matching
3. **Result Generation**: Match results are created for each successful match
4. **Filtering**: Only successful matches are returned in the final result
