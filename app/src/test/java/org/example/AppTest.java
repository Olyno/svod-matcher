package org.example;

import org.example.data.MockData;
import org.example.matcher.MatchResult;
import org.example.matcher.ShowMatcher;
import org.example.model.CSVRow;
import org.example.model.Show;
import org.example.model.ShowTitle;
import org.example.util.ProducerParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the show matching system.
 */
class AppTest {
    
    private List<Show> shows;
    private List<ShowTitle> showTitles;
    private List<CSVRow> csvRows;
    private ShowMatcher matcher;
    
    @BeforeEach
    void setUp() {
        // Create mock data for each test
        shows = MockData.createMockShows();
        showTitles = MockData.createMockShowTitles(shows);
        csvRows = MockData.createMockCSVRows();
        matcher = new ShowMatcher(shows, showTitles);
    }
    
    @Test
    void testExactTitleMatch() {
        // Find the Garfield exact match CSV row
        final CSVRow garfieldExact = csvRows.stream()
                .filter(row -> "Garfield: The Movie".equals(row.getOriginalTitle()))
                .findFirst()
                .orElseThrow();
        
        // Find matches for this row
        final List<MatchResult> matches = matcher.findMatchesForRow(garfieldExact);
        
        // Should have matches (3 languages)
        assertFalse(matches.isEmpty(), "Should find matches for exact title");
        
        // All matches should be successful
        assertTrue(matches.stream().allMatch(MatchResult::isMatch), "All matches should be successful");
        
        // Verify the matched show is Garfield
        final ShowTitle firstMatch = matches.get(0).getShowTitle();
        final Show matchedShow = shows.stream()
                .filter(show -> show.getId().equals(firstMatch.getShowId()))
                .findFirst()
                .orElseThrow();
        
        assertTrue(matchedShow.getOriginalTitle().contains("Garfield"), 
                "Matched show should be Garfield");
    }
    
    @Test
    void testPartialTitleMatch() {
        // Find the Garfield partial match CSV row
        final CSVRow garfieldPartial = csvRows.stream()
                .filter(row -> "Garfield".equals(row.getOriginalTitle()) && 
                        row.getProductionYear() != null && row.getProductionYear() == 2004 &&
                        "movie".equals(row.getType()))
                .findFirst()
                .orElseThrow();
        
        // Find matches for this row
        final List<MatchResult> matches = matcher.findMatchesForRow(garfieldPartial);
        
        // Should have matches
        assertFalse(matches.isEmpty(), "Should find matches for partial title");
        
        // All matches should be successful
        assertTrue(matches.stream().allMatch(MatchResult::isMatch), "All matches should be successful");
        
        // Verify the matched show is Garfield
        final ShowTitle firstMatch = matches.get(0).getShowTitle();
        final Show matchedShow = shows.stream()
                .filter(show -> show.getId().equals(firstMatch.getShowId()))
                .findFirst()
                .orElseThrow();
        
        assertTrue(matchedShow.getOriginalTitle().contains("Garfield"), 
                "Matched show should be Garfield");
    }
    
    @Test
    void testYearMismatch() {
        // Find the Garfield with wrong year CSV row
        final CSVRow garfieldWrongYear = csvRows.stream()
                .filter(row -> "Garfield".equals(row.getOriginalTitle()) && 
                        row.getProductionYear() != null && row.getProductionYear() == 2005)
                .findFirst()
                .orElseThrow();
        
        // Find matches for this row
        final List<MatchResult> matches = matcher.findMatchesForRow(garfieldWrongYear);
        
        // Should not have matches due to year mismatch
        assertTrue(matches.isEmpty(), "Should not find matches for year mismatch");
    }
    
    @Test
    void testTypeMismatch() {
        // Find the Garfield with wrong type CSV row
        final CSVRow garfieldWrongType = csvRows.stream()
                .filter(row -> "Garfield".equals(row.getOriginalTitle()) && 
                        "series".equals(row.getType()))
                .findFirst()
                .orElseThrow();
        
        // Find matches for this row
        final List<MatchResult> matches = matcher.findMatchesForRow(garfieldWrongType);
        
        // Should not have matches due to type mismatch
        assertTrue(matches.isEmpty(), "Should not find matches for type mismatch");
    }
    
    @Test
    void testProducerMismatch() {
        // Find the Garfield with wrong producers CSV row
        final CSVRow garfieldWrongProducers = csvRows.stream()
                .filter(row -> "Garfield".equals(row.getOriginalTitle()) && 
                        row.getProducers() != null && row.getProducers().contains("Wrong Productions"))
                .findFirst()
                .orElseThrow();
        
        // Find matches for this row
        final List<MatchResult> matches = matcher.findMatchesForRow(garfieldWrongProducers);
        
        // Should not have matches due to producer mismatch
        assertTrue(matches.isEmpty(), "Should not find matches for producer mismatch");
    }
    
    @Test
    void testNonExistentShow() {
        // Find the non-existent show CSV row
        final CSVRow nonExistentShow = csvRows.stream()
                .filter(row -> "Non-existent Show".equals(row.getOriginalTitle()))
                .findFirst()
                .orElseThrow();
        
        // Find matches for this row
        final List<MatchResult> matches = matcher.findMatchesForRow(nonExistentShow);
        
        // Should not have matches
        assertTrue(matches.isEmpty(), "Should not find matches for non-existent show");
    }
    
    @Test
    void testAllMatchesAreValid() {
        // Find all matches
        final List<MatchResult> allMatches = matcher.findMatches(csvRows);
        
        // All matches should be successful
        assertTrue(allMatches.stream().allMatch(MatchResult::isMatch), 
                "All matches should be successful");
        
        // Group matches by CSV row
        final Map<CSVRow, List<MatchResult>> matchesByRow = allMatches.stream()
                .collect(Collectors.groupingBy(MatchResult::getCsvRow));
        
        // Verify each matched row has the correct show
        for (final Map.Entry<CSVRow, List<MatchResult>> entry : matchesByRow.entrySet()) {
            final CSVRow csvRow = entry.getKey();
            final List<MatchResult> matches = entry.getValue();
            
            // Get the first match
            final MatchResult firstMatch = matches.get(0);
            final ShowTitle matchedTitle = firstMatch.getShowTitle();
            final Show matchedShow = shows.stream()
                    .filter(show -> show.getId().equals(matchedTitle.getShowId()))
                    .findFirst()
                    .orElseThrow();
            
            // Verify year match if both are non-null
            if (csvRow.getProductionYear() != null && matchedShow.getProductionYear() != null) {
                assertEquals(csvRow.getProductionYear(), matchedShow.getProductionYear(),
                        "Production year should match");
            }
            
            // Verify type match if both are non-null
            if (csvRow.getType() != null && matchedShow.getType() != null) {
                assertTrue(csvRow.getType().equalsIgnoreCase(matchedShow.getType()),
                        "Type should match");
            }
            
            // Verify producer match if both are non-null and non-empty
            final List<String> csvProducers = ProducerParser.parse(csvRow.getProducers());
            if (!csvProducers.isEmpty() && !matchedShow.getProducers().isEmpty()) {
                assertTrue(ProducerParser.hasCommonProducer(csvProducers, matchedShow.getProducers()),
                        "Should have at least one common producer");
            }
            
            // Verify title match
            final String normalizedCsvTitle = csvRow.getOriginalTitle().toLowerCase().replaceAll("[^a-z0-9]", "");
            final String normalizedShowTitle = matchedShow.getOriginalTitle().toLowerCase().replaceAll("[^a-z0-9]", "");
            
            assertTrue(normalizedCsvTitle.contains(normalizedShowTitle) || normalizedShowTitle.contains(normalizedCsvTitle),
                    "Titles should match");
        }
    }
    
    @Test
    void testProducerParser() {
        // Test comma separator
        final List<String> commaSeparated = ProducerParser.parse("Producer1, Producer2, Producer3");
        assertEquals(3, commaSeparated.size(), "Should parse 3 producers with comma separator");
        
        // Test semicolon separator
        final List<String> semicolonSeparated = ProducerParser.parse("Producer1; Producer2; Producer3");
        assertEquals(3, semicolonSeparated.size(), "Should parse 3 producers with semicolon separator");
        
        // Test pipe separator
        final List<String> pipeSeparated = ProducerParser.parse("Producer1 | Producer2 | Producer3");
        assertEquals(3, pipeSeparated.size(), "Should parse 3 producers with pipe separator");
        
        // Test mixed separators
        final List<String> mixedSeparators = ProducerParser.parse("Producer1, Producer2; Producer3 | Producer4");
        assertTrue(mixedSeparators.size() >= 2, "Should parse at least 2 producers with mixed separators");
        
        // Test empty string
        final List<String> empty = ProducerParser.parse("");
        assertTrue(empty.isEmpty(), "Should return empty list for empty string");
        
        // Test null
        final List<String> nullString = ProducerParser.parse(null);
        assertTrue(nullString.isEmpty(), "Should return empty list for null");
    }
    
    @Test
    void testPerformance() {
        // Create a larger dataset for performance testing
        final List<CSVRow> largeCSVRows = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            largeCSVRows.addAll(csvRows);
        }
        
        // Measure performance
        final long startTime = System.currentTimeMillis();
        matcher.findMatches(largeCSVRows);
        final long endTime = System.currentTimeMillis();
        
        final long duration = endTime - startTime;
        System.out.println("Performance test with " + largeCSVRows.size() + " rows: " + duration + "ms");
        
        // Should be fast enough (extrapolate to 30k entries)
        final long estimatedTimeFor30k = duration * 30000 / largeCSVRows.size();
        assertTrue(estimatedTimeFor30k < 60000, 
                "Estimated time for 30k entries should be less than 1 minute (actual: " + estimatedTimeFor30k + "ms)");
    }
}
