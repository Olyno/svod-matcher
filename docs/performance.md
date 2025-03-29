# Performance Optimization

The Show Matching System is designed to process large datasets efficiently. This document outlines the performance optimization techniques used in the system.

## Performance Requirements

- Process 30,000 entries in under 1 minute
- Maintain high matching accuracy
- Scale with increasing dataset size

## Optimization Techniques

### 1. Indexing

Shows are indexed by normalized title during initialization for efficient lookup:

```java
// Index shows by ID for quick lookup
this.showsById = shows.stream()
        .collect(Collectors.toMap(Show::getId, Function.identity()));

// Group titles by show ID
this.titlesByShowId = showTitles.stream()
        .collect(Collectors.groupingBy(ShowTitle::getShowId));

// Index shows by normalized title for quick lookup
this.showsByNormalizedTitle = new ConcurrentHashMap<>();
for (final Show show : shows) {
    final String normalizedTitle = normalizeTitle(show.getOriginalTitle());
    showsByNormalizedTitle.computeIfAbsent(normalizedTitle, k -> new ArrayList<>()).add(show);
}
```

This indexing strategy provides:
- O(1) lookup for shows by ID
- O(1) lookup for titles by show ID
- O(1) lookup for shows by normalized title

### 2. Parallel Processing

The matching process uses parallel streams for efficient processing of large datasets:

```java
public List<MatchResult> findMatches(List<CSVRow> csvRows) {
    return csvRows.parallelStream()
            .flatMap(csvRow -> findMatchesForRow(csvRow).stream())
            .filter(MatchResult::isMatch)
            .collect(Collectors.toList());
}
```

Benefits of parallel processing:
- Utilizes multiple CPU cores
- Automatically distributes work across threads
- Scales with available hardware

### 3. Early Filtering

The matching algorithm uses early filtering to avoid unnecessary comparisons:

```java
// Check year match if both are non-null
if (!isYearMatch(csvRow.getProductionYear(), show.getProductionYear())) {
    continue;
}

// Check type match if both are non-null
if (!isTypeMatch(csvRow.getType(), show.getType())) {
    continue;
}

// Check producer match
final List<String> csvProducers = ProducerParser.parse(csvRow.getProducers());
if (!csvProducers.isEmpty() && !show.getProducers().isEmpty() && 
        !ProducerParser.hasCommonProducer(csvProducers, show.getProducers())) {
    continue;
}
```

This approach:
- Fails fast on non-matching criteria
- Avoids unnecessary producer parsing and comparison
- Reduces the number of match results created

### 4. Efficient String Operations

Title normalization is optimized for performance:

```java
private String normalizeTitle(String title) {
    if (title == null) {
        return "";
    }
    
    return title.trim()
            .toLowerCase()
            .replaceAll("[^a-z0-9]", ""); // Remove all non-alphanumeric characters
}
```

This approach:
- Handles null values efficiently
- Uses built-in string methods for performance
- Applies a single regex replacement instead of multiple operations

### 5. Immutable Objects

All model classes use immutable objects with final fields:

```java
public final class Show {
    private final UUID id;
    private final List<String> producers;
    private final Integer productionYear;
    private final String type;
    private final String originalTitle;
    
    // Constructor, getters, etc.
}
```

Benefits of immutability:
- Thread safety for parallel processing
- No defensive copying needed
- Predictable behavior

### 6. Concurrent Collections

The system uses concurrent collections for thread safety:

```java
// ConcurrentHashMap for thread-safe access
this.showsByNormalizedTitle = new ConcurrentHashMap<>();
```

Benefits of concurrent collections:
- Thread-safe without explicit synchronization
- High concurrency for read operations
- Efficient for parallel processing

### 7. Lazy Evaluation

The system uses lazy evaluation with streams:

```java
return csvRows.parallelStream()
        .flatMap(csvRow -> findMatchesForRow(csvRow).stream())
        .filter(MatchResult::isMatch)
        .collect(Collectors.toList());
```

Benefits of lazy evaluation:
- Operations are only performed when needed
- Intermediate results are not materialized
- Memory-efficient processing of large datasets

### 8. Null-Aware Comparisons

The system uses null-aware comparisons to avoid NullPointerExceptions:

```java
private boolean isYearMatch(Integer csvYear, Integer showYear) {
    // If either year is null, consider it a match
    if (csvYear == null || showYear == null) {
        return true;
    }
    
    // Otherwise, years must be equal
    return csvYear.equals(showYear);
}
```

This approach:
- Handles null values gracefully
- Avoids unnecessary null checks
- Improves code readability

## Performance Metrics

The system is designed to meet the performance requirement of processing 30,000 entries in under 1 minute. In the `App` class, performance metrics are collected:

```java
final long startTime = System.currentTimeMillis();
final List<MatchResult> matches = matcher.findMatches(csvRows);
final long endTime = System.currentTimeMillis();

System.out.println("Matching completed in " + (endTime - startTime) + "ms");
```

This allows for monitoring and tuning of performance as needed.

## Future Optimizations

Potential future optimizations include:

1. **Caching**: Implement a cache for frequently accessed data
2. **Batch Processing**: Process data in batches for better memory management
3. **Custom Thread Pool**: Use a custom thread pool for more control over parallelism
4. **Database Indexing**: If integrated with a database, use appropriate indexes
5. **Profiling and Tuning**: Use profiling tools to identify and address bottlenecks
