# Matching System Architecture

## ShowMatcher Class
```java
public class ShowMatcher {
    public MatchResult matchTitles(CSVRow csvRow, Show existingShow) {
        // Jaro-Winkler similarity implementation
    }
}
```

## Matching Process
1. Input normalization
2. Tokenization of titles/producers
3. Similarity scoring:
   - Title: 70% weight (Jaro-Winkler)
   - Producers: 30% weight (Exact match)
4. Threshold-based matching (0.85 minimum)

```mermaid
sequenceDiagram
    participant CSV as CSV Row
    participant Matcher as ShowMatcher
    participant Mock as MockData
    participant Result as MatchResult
    
    CSV->>Matcher: Raw title data
    Mock->>Matcher: Existing shows
    Matcher->>Result: Similarity scores
    Result-->>Matcher: Confidence rating
