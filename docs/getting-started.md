# Getting Started Guide

This guide will help you set up and start working with the Show Matching System.

## Prerequisites

- Java 17 or higher
- Gradle 8.5 or higher

## Project Setup

1. Clone the repository:
   ```bash
   git clone [repository-url]
   cd show-matching-system
   ```

2. Build the project:
   ```bash
   ./gradlew build
   ```

3. Run the tests:
   ```bash
   ./gradlew test
   ```

## Running the Application

The application can be run using Gradle:

```bash
./gradlew run
```

This will execute the `main` method in the `App` class, which demonstrates the matching system using mock data.

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   │       └── example/
│   │   │           ├── App.java                  # Main application class
│   │   │           ├── data/
│   │   │           │   └── MockData.java         # Mock data generation
│   │   │           ├── matcher/
│   │   │           │   ├── MatchResult.java      # Match result model
│   │   │           │   └── ShowMatcher.java      # Core matching logic
│   │   │           ├── model/
│   │   │           │   ├── CSVRow.java           # CSV row model
│   │   │           │   ├── Show.java             # Show model
│   │   │           │   └── ShowTitle.java        # Show title model
│   │   │           └── util/
│   │   │               └── ProducerParser.java   # Producer parsing utility
│   │   └── resources/
│   └── test/
│       ├── java/
│       │   └── org/
│       │       └── example/
│       │           └── AppTest.java              # Application tests
│       └── resources/
└── build.gradle                                  # Gradle build file
```

## Using the Matching System

### Creating a Matcher

```java
// Create a matcher with shows and show titles
ShowMatcher matcher = new ShowMatcher(shows, showTitles);
```

### Finding Matches

```java
// Find matches for a list of CSV rows
List<MatchResult> matches = matcher.findMatches(csvRows);

// Find matches for a single CSV row
List<MatchResult> rowMatches = matcher.findMatchesForRow(csvRow);
```

### Processing Match Results

```java
// Filter successful matches
List<MatchResult> successfulMatches = matches.stream()
        .filter(MatchResult::isMatch)
        .collect(Collectors.toList());

// Group matches by CSV row
Map<CSVRow, List<MatchResult>> matchesByRow = matches.stream()
        .collect(Collectors.groupingBy(MatchResult::getCsvRow));
```

## Working with Mock Data

The `MockData` class provides methods for creating mock data:

```java
// Create mock shows
List<Show> shows = MockData.createMockShows();

// Create mock show titles
List<ShowTitle> showTitles = MockData.createMockShowTitles(shows);

// Create mock CSV rows
List<CSVRow> csvRows = MockData.createMockCSVRows();
```

## Creating Custom Models

All models use the Builder pattern:

```java
// Create a show
Show show = Show.builder()
        .id(UUID.randomUUID())
        .originalTitle("The Matrix")
        .producers(List.of("Warner Bros.", "Village Roadshow Pictures"))
        .productionYear(1999)
        .type("movie")
        .build();

// Create a show title
ShowTitle title = ShowTitle.builder()
        .showId(show.getId())
        .language("en")
        .title("The Matrix")
        .build();

// Create a CSV row
CSVRow csvRow = CSVRow.builder()
        .originalTitle("The Matrix")
        .translatedTitle("Matrix")
        .producers("Warner Bros.; Village Roadshow Pictures")
        .productionYear(1999)
        .type("movie")
        .build();
```

## Parsing Producers

The `ProducerParser` utility can be used to parse producer strings:

```java
// Parse a producer string
List<String> producers = ProducerParser.parse("Warner Bros., Village Roadshow Pictures");

// Check if two producer lists have a common producer
boolean hasCommon = ProducerParser.hasCommonProducer(producers1, producers2);
```

## Next Steps

1. Integrate with actual CSV file parsing
2. Connect to a real database
3. Implement additional matching criteria if needed
4. Create a user interface for displaying results
