# Mock Data System

The Show Matching System includes a comprehensive mock data system for testing and demonstration purposes.

## MockData Class

The `MockData` class provides methods for creating mock shows, show titles, and CSV rows:

```java
public final class MockData {
    private MockData() {
        // Utility class, no instances
    }

    public static List<Show> createMockShows() { /*...*/ }
    public static List<ShowTitle> createMockShowTitles(List<Show> shows) { /*...*/ }
    public static List<CSVRow> createMockCSVRows() { /*...*/ }
}
```

## Mock Shows

The `createMockShows()` method creates a list of 20 mock shows with various titles, producers, years, and types:

```java
public static List<Show> createMockShows() {
    final List<Show> shows = new ArrayList<>();

    // Show 1: Garfield
    final UUID garfieldId = UUID.randomUUID();
    shows.add(Show.builder()
            .id(garfieldId)
            .originalTitle("Garfield: The Movie")
            .producers(List.of("20th Century Fox", "Davis Entertainment"))
            .productionYear(2004)
            .type("movie")
            .build());

    // Additional shows...

    return shows;
}
```

The mock shows include:
- Movies and series
- Various production years
- Different producers
- Titles with special characters, accents, numbers, etc.

## Mock Show Titles

The `createMockShowTitles(List<Show> shows)` method creates show titles for each show in multiple languages:

```java
public static List<ShowTitle> createMockShowTitles(List<Show> shows) {
    final List<ShowTitle> showTitles = new ArrayList<>();

    for (final Show show : shows) {
        // Add original title
        showTitles.add(ShowTitle.builder()
                .showId(show.getId())
                .language("en")
                .title(show.getOriginalTitle())
                .build());

        // Add French title
        final String frenchTitle = translateToFrench(show.getOriginalTitle());
        showTitles.add(ShowTitle.builder()
                .showId(show.getId())
                .language("fr")
                .title(frenchTitle)
                .build());

        // Add Spanish title
        final String spanishTitle = translateToSpanish(show.getOriginalTitle());
        showTitles.add(ShowTitle.builder()
                .showId(show.getId())
                .language("es")
                .title(spanishTitle)
                .build());
    }

    // Add episode titles for series
    for (final Show show : shows) {
        if ("series".equalsIgnoreCase(show.getType())) {
            // Add episode titles for the first season
            for (int i = 1; i <= 5; i++) {
                // Add English and French episode titles...
            }
        }
    }

    return showTitles;
}
```

For each show, the method creates:
- An English title (same as the original title)
- A French title
- A Spanish title

For series, it also creates episode titles for the first season in English and French.

## Mock CSV Rows

The `createMockCSVRows()` method creates a list of 25 mock CSV rows:

```java
public static List<CSVRow> createMockCSVRows() {
    final List<CSVRow> csvRows = new ArrayList<>();

    // CSV Row 1: Garfield (exact match)
    csvRows.add(CSVRow.builder()
            .originalTitle("Garfield: The Movie")
            .translatedTitle("Garfield, le film")
            .producers("20th Century Fox, Davis Entertainment")
            .productionYear(2004)
            .type("movie")
            .build());

    // Additional CSV rows...

    return csvRows;
}
```

The mock CSV rows include:
- Exact matches with shows in the database
- Partial title matches
- Matches with different separators in producer strings
- Non-existent shows
- Partial matches with wrong year, type, or producers

## Title Translation

The mock data system includes methods for simulating title translation:

```java
private static String translateToFrench(String title) {
    if (title == null) {
        return null;
    }

    return switch (title) {
        case "Garfield: The Movie" -> "Garfield, le film";
        case "The Matrix" -> "Matrix";
        // Additional translations...
        default -> title + " (en français)";
    };
}

private static String translateToSpanish(String title) {
    if (title == null) {
        return null;
    }

    return switch (title) {
        case "Garfield: The Movie" -> "Garfield: La Película";
        case "The Matrix" -> "Matrix";
        // Additional translations...
        default -> title + " (en español)";
    };
}
```

These methods provide realistic translations for the mock show titles.

## Usage in the Application

The mock data is used in the `App` class to demonstrate the matching system:

```java
public static void main(String[] args) {
    // Create mock data
    final List<Show> shows = MockData.createMockShows();
    final List<ShowTitle> showTitles = MockData.createMockShowTitles(shows);
    final List<CSVRow> csvRows = MockData.createMockCSVRows();
    
    // Create matcher
    final ShowMatcher matcher = new ShowMatcher(shows, showTitles);
    
    // Find matches
    final List<MatchResult> matches = matcher.findMatches(csvRows);
    
    // Process and display results...
}
```

## Test Cases

The mock data is designed to cover various test cases:

1. **Exact Matches**: CSV rows that exactly match shows in the database
2. **Partial Title Matches**: CSV rows with titles that partially match shows
3. **Different Separators**: CSV rows with different separators in producer strings
4. **Special Characters**: Titles with accents, numbers, punctuation, etc.
5. **Missing Data**: CSV rows with null or empty fields
6. **Non-Matches**: CSV rows that should not match any show
7. **Edge Cases**: CSV rows with wrong year, type, or producers

This comprehensive set of test cases helps ensure the matching algorithm works correctly in various scenarios.
