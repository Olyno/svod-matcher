# Utility Components

## ProducerParser

The `ProducerParser` is a utility class for parsing producer strings with different separators and normalizing producer names.

### Purpose

Producer information often comes in various formats with different separators. The `ProducerParser` handles this variability by:

1. Detecting the separator used in the string
2. Splitting the string into individual producer names
3. Normalizing producer names for consistent comparison

### Implementation

```java
public final class ProducerParser {
    
    private static final List<String> COMMON_SEPARATORS = List.of(",", ";", "|", "/");
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+");
    
    private ProducerParser() {
        // Utility class, no instances
    }
    
    public static List<String> parse(String producerString) {
        if (producerString == null || producerString.isBlank()) {
            return Collections.emptyList();
        }
        
        final String separator = detectSeparator(producerString);
        return Arrays.stream(producerString.split(Pattern.quote(separator)))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
    
    private static String detectSeparator(String producerString) {
        for (final String separator : COMMON_SEPARATORS) {
            if (producerString.contains(separator)) {
                return separator;
            }
        }
        
        // Fall back to whitespace if no common separator is found
        return "\\s+";
    }
    
    public static String normalizeProducerName(String producerName) {
        if (producerName == null) {
            return "";
        }
        
        return WHITESPACE_PATTERN.matcher(producerName.trim()).replaceAll(" ").toLowerCase();
    }
    
    public static boolean hasCommonProducer(List<String> producers1, List<String> producers2) {
        if (producers1 == null || producers2 == null || producers1.isEmpty() || producers2.isEmpty()) {
            return false;
        }
        
        final List<String> normalizedProducers1 = producers1.stream()
                .map(ProducerParser::normalizeProducerName)
                .collect(Collectors.toList());
        
        final List<String> normalizedProducers2 = producers2.stream()
                .map(ProducerParser::normalizeProducerName)
                .collect(Collectors.toList());
        
        return normalizedProducers1.stream()
                .anyMatch(normalizedProducers2::contains);
    }
}
```

### Key Features

#### Separator Detection

The parser automatically detects the separator used in a producer string:

```java
private static String detectSeparator(String producerString) {
    for (final String separator : COMMON_SEPARATORS) {
        if (producerString.contains(separator)) {
            return separator;
        }
    }
    
    // Fall back to whitespace if no common separator is found
    return "\\s+";
}
```

Common separators include:
- Comma (,)
- Semicolon (;)
- Pipe (|)
- Slash (/)

If none of these separators are found, the parser falls back to whitespace.

#### Producer Name Normalization

Producer names are normalized for consistent comparison:

```java
public static String normalizeProducerName(String producerName) {
    if (producerName == null) {
        return "";
    }
    
    return WHITESPACE_PATTERN.matcher(producerName.trim()).replaceAll(" ").toLowerCase();
}
```

Normalization includes:
1. Trimming whitespace
2. Replacing multiple whitespace characters with a single space
3. Converting to lowercase

#### Common Producer Detection

The parser can check if two lists of producers have at least one producer in common:

```java
public static boolean hasCommonProducer(List<String> producers1, List<String> producers2) {
    if (producers1 == null || producers2 == null || producers1.isEmpty() || producers2.isEmpty()) {
        return false;
    }
    
    final List<String> normalizedProducers1 = producers1.stream()
            .map(ProducerParser::normalizeProducerName)
            .collect(Collectors.toList());
    
    final List<String> normalizedProducers2 = producers2.stream()
            .map(ProducerParser::normalizeProducerName)
            .collect(Collectors.toList());
    
    return normalizedProducers1.stream()
            .anyMatch(normalizedProducers2::contains);
}
```

This is used in the matching algorithm to check if a CSV row and a show have at least one producer in common.

### Usage Examples

#### Parsing a Producer String

```java
// Input: "Warner Bros., Village Roadshow Pictures"
List<String> producers = ProducerParser.parse("Warner Bros., Village Roadshow Pictures");
// Output: ["Warner Bros.", "Village Roadshow Pictures"]

// Input: "Bright/Kauffman/Crane Productions | Warner Bros. Television"
List<String> producers = ProducerParser.parse("Bright/Kauffman/Crane Productions | Warner Bros. Television");
// Output: ["Bright/Kauffman/Crane Productions", "Warner Bros. Television"]
```

#### Checking for Common Producers

```java
List<String> producers1 = List.of("Warner Bros.", "Village Roadshow Pictures");
List<String> producers2 = List.of("Warner Bros.", "Legendary Pictures");

boolean hasCommon = ProducerParser.hasCommonProducer(producers1, producers2);
// Output: true (because "Warner Bros." is in both lists)
