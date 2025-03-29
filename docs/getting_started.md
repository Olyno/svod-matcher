# Getting Started Guide

## Prerequisites
- Java 17 JDK
- Gradle 8.5+

## Build & Test
```bash
./gradlew build  # Build project
./gradlew test   # Run unit tests
```

## Working with Mock Data
```java
// Access sample dataset
List<Show> shows = MockData.getSampleShows(); 
List<CSVRow> csvData = MockData.getSampleCSVRows();
```

## Running Matcher
```java
ShowMatcher matcher = new ShowMatcher();
MatchResult result = matcher.matchTitles(csvRow, existingShow);
System.out.println(result.getConfidenceScore());
