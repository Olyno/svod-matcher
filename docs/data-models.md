# Data Models

The Show Matching System uses several immutable data models to represent shows, titles, and CSV data.

## Core Entities

### Show

Represents a show in the database with its metadata.

```java
public final class Show {
    private final UUID id;
    private final List<String> producers;
    private final Integer productionYear;
    private final String type;
    private final String originalTitle;
    
    // Getters, equals, hashCode, toString, and Builder implementation
}
```

#### Fields

- **id**: Unique identifier for the show
- **producers**: List of production companies
- **productionYear**: Year the show was produced (nullable)
- **type**: Type of show (e.g., "movie", "series") (nullable)
- **originalTitle**: Original title of the show

### ShowTitle

Represents a title for a show in any language, including episode information for series.

```java
public final class ShowTitle {
    private final UUID showId;
    private final String language;
    private final String title;
    private final Integer episodeNumber;
    private final String episodeName;
    private final Integer seasonNumber;
    
    // Getters, equals, hashCode, toString, and Builder implementation
}
```

#### Fields

- **showId**: Reference to the show this title belongs to
- **language**: Language code (e.g., "en", "fr", "es")
- **title**: The title text
- **episodeNumber**: Episode number for series (nullable)
- **episodeName**: Episode name for series (nullable)
- **seasonNumber**: Season number for series (nullable)

### CSVRow

Represents a row from a CSV file to be matched against the show database.

```java
public final class CSVRow {
    private final String originalTitle;
    private final String translatedTitle;
    private final String producers;
    private final Integer productionYear;
    private final String type;
    
    // Getters, equals, hashCode, toString, and Builder implementation
}
```

#### Fields

- **originalTitle**: Original title from the CSV
- **translatedTitle**: Translated title from the CSV (nullable)
- **producers**: Raw producer string that needs parsing
- **productionYear**: Year the show was produced (nullable)
- **type**: Type of show (e.g., "movie", "series") (nullable)

### MatchResult

Represents the result of a matching operation between a CSVRow and a ShowTitle.

```java
public final class MatchResult {
    private final CSVRow csvRow;
    private final ShowTitle showTitle;
    private final boolean isMatch;
    private final String reason;
    
    // Getters, equals, hashCode, toString
    
    // Factory methods
    public static MatchResult match(CSVRow csvRow, ShowTitle showTitle) { /*...*/ }
    public static MatchResult noMatch(CSVRow csvRow, ShowTitle showTitle, String reason) { /*...*/ }
}
```

#### Fields

- **csvRow**: The CSV row being matched
- **showTitle**: The show title being matched against
- **isMatch**: Whether the match was successful
- **reason**: Reason for the match result (especially useful for failed matches)

## Builder Pattern

All model classes use the Builder pattern for creating instances:

```java
// Creating a Show
Show show = Show.builder()
    .id(UUID.randomUUID())
    .originalTitle("The Matrix")
    .producers(List.of("Warner Bros.", "Village Roadshow Pictures"))
    .productionYear(1999)
    .type("movie")
    .build();

// Creating a ShowTitle
ShowTitle title = ShowTitle.builder()
    .showId(show.getId())
    .language("en")
    .title("The Matrix")
    .build();

// Creating a CSVRow
CSVRow csvRow = CSVRow.builder()
    .originalTitle("The Matrix")
    .translatedTitle("Matrix")
    .producers("Warner Bros.; Village Roadshow Pictures")
    .productionYear(1999)
    .type("movie")
    .build();
```

## Entity Relationships

```mermaid
erDiagram
    Show ||--o{ ShowTitle : "has"
    Show {
        UUID id
        List producers
        Integer productionYear
        String type
        String originalTitle
    }
    ShowTitle {
        UUID showId
        String language
        String title
        Integer episodeNumber
        String episodeName
        Integer seasonNumber
    }
    CSVRow {
        String originalTitle
        String translatedTitle
        String producers
        Integer productionYear
        String type
    }
    MatchResult {
        CSVRow csvRow
        ShowTitle showTitle
        boolean isMatch
        String reason
    }
    CSVRow ||--o{ MatchResult : "matched to"
    ShowTitle ||--o{ MatchResult : "matched with"
