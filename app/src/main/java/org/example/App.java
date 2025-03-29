package org.example;

import org.example.data.MockData;
import org.example.matcher.MatchResult;
import org.example.matcher.ShowMatcher;
import org.example.model.CSVRow;
import org.example.model.Show;
import org.example.model.ShowTitle;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Main application class for the show matching system.
 */
public class App {
    
    /**
     * Main method to run the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Show Matching System");
        System.out.println("====================");
        
        // Create mock data
        final List<Show> shows = MockData.createMockShows();
        final List<ShowTitle> showTitles = MockData.createMockShowTitles(shows);
        final List<CSVRow> csvRows = MockData.createMockCSVRows();
        
        System.out.println("Mock data created:");
        System.out.println("- Shows: " + shows.size());
        System.out.println("- Show Titles: " + showTitles.size());
        System.out.println("- CSV Rows: " + csvRows.size());
        System.out.println();
        
        // Create matcher
        final ShowMatcher matcher = new ShowMatcher(shows, showTitles);
        
        // Find matches
        final long startTime = System.currentTimeMillis();
        final List<MatchResult> matches = matcher.findMatches(csvRows);
        final long endTime = System.currentTimeMillis();
        
        System.out.println("Matching completed in " + (endTime - startTime) + "ms");
        System.out.println("Found " + matches.size() + " matches");
        System.out.println();
        
        // Group matches by CSV row
        final Map<CSVRow, List<MatchResult>> matchesByRow = matches.stream()
                .collect(Collectors.groupingBy(MatchResult::getCsvRow));
        
        // Print matches
        System.out.println("Matches by CSV row:");
        matchesByRow.forEach((csvRow, rowMatches) -> {
            System.out.println("CSV Row: " + csvRow.getOriginalTitle() + " / " + csvRow.getTranslatedTitle());
            System.out.println("Matched with " + rowMatches.size() + " show titles:");
            
            for (final MatchResult match : rowMatches) {
                final ShowTitle showTitle = match.getShowTitle();
                final Show show = shows.stream()
                        .filter(s -> s.getId().equals(showTitle.getShowId()))
                        .findFirst()
                        .orElse(null);
                
                if (show != null) {
                    System.out.println("  - " + showTitle.getTitle() + " (" + showTitle.getLanguage() + ")");
                    System.out.println("    Show: " + show.getOriginalTitle() + " (" + show.getProductionYear() + ")");
                    System.out.println("    Producers: " + String.join(", ", show.getProducers()));
                    System.out.println();
                }
            }
        });
    }
}
