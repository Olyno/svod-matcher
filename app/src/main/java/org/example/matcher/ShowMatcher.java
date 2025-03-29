package org.example.matcher;

import org.example.model.CSVRow;
import org.example.model.Show;
import org.example.model.ShowTitle;
import org.example.util.ProducerParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Core class for matching shows from CSV rows against a database of shows and their titles.
 */
public final class ShowMatcher {
    private final List<Show> shows;
    private final List<ShowTitle> showTitles;
    private final Map<UUID, Show> showsById;
    private final Map<UUID, List<ShowTitle>> titlesByShowId;
    private final Map<String, List<Show>> showsByNormalizedTitle;
    
    /**
     * Creates a new ShowMatcher with the given shows and show titles.
     *
     * @param shows      the list of shows
     * @param showTitles the list of show titles
     */
    public ShowMatcher(List<Show> shows, List<ShowTitle> showTitles) {
        this.shows = new ArrayList<>(shows);
        this.showTitles = new ArrayList<>(showTitles);
        
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
    }
    
    /**
     * Finds all show titles that match the given CSV rows.
     *
     * @param csvRows the list of CSV rows to match
     * @return a list of match results
     */
    public List<MatchResult> findMatches(List<CSVRow> csvRows) {
        return csvRows.parallelStream()
                .flatMap(csvRow -> findMatchesForRow(csvRow).stream())
                .filter(MatchResult::isMatch)
                .collect(Collectors.toList());
    }
    
    /**
     * Finds all show titles that match the given CSV row.
     *
     * @param csvRow the CSV row to match
     * @return a list of match results
     */
    public List<MatchResult> findMatchesForRow(CSVRow csvRow) {
        final List<MatchResult> results = new ArrayList<>();
        
        // Get potential show matches based on title
        final List<Show> potentialShows = findPotentialShowsByTitle(csvRow);
        
        for (final Show show : potentialShows) {
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
            
            // If we get here, we have a match
            // Find all titles for this show
            final List<ShowTitle> matchingTitles = titlesByShowId.getOrDefault(show.getId(), List.of());
            
            for (final ShowTitle title : matchingTitles) {
                results.add(MatchResult.match(csvRow, title));
            }
        }
        
        return results;
    }
    
    /**
     * Finds potential shows that match the given CSV row based on title.
     *
     * @param csvRow the CSV row to match
     * @return a list of potential show matches
     */
    private List<Show> findPotentialShowsByTitle(CSVRow csvRow) {
        final List<Show> potentialShows = new ArrayList<>();
        
        // Check original title
        if (csvRow.getOriginalTitle() != null && !csvRow.getOriginalTitle().isBlank()) {
            potentialShows.addAll(findShowsByTitle(csvRow.getOriginalTitle()));
        }
        
        // Check translated title
        if (csvRow.getTranslatedTitle() != null && !csvRow.getTranslatedTitle().isBlank()) {
            potentialShows.addAll(findShowsByTitle(csvRow.getTranslatedTitle()));
        }
        
        return potentialShows.stream()
                .distinct()
                .collect(Collectors.toList());
    }
    
    /**
     * Finds shows that match the given title.
     *
     * @param title the title to match
     * @return a list of show matches
     */
    private List<Show> findShowsByTitle(String title) {
        final List<Show> result = new ArrayList<>();
        final String normalizedTitle = normalizeTitle(title);
        
        // Direct lookup by normalized title
        final List<Show> directMatches = showsByNormalizedTitle.getOrDefault(normalizedTitle, List.of());
        result.addAll(directMatches);
        
        // Check if any show title contains this title
        for (final ShowTitle showTitle : showTitles) {
            final String normalizedShowTitle = normalizeTitle(showTitle.getTitle());
            
            // Check if either title contains the other
            if (normalizedShowTitle.contains(normalizedTitle) || normalizedTitle.contains(normalizedShowTitle)) {
                final Show show = showsById.get(showTitle.getShowId());
                if (show != null) {
                    result.add(show);
                }
            }
        }
        
        return result.stream()
                .distinct()
                .collect(Collectors.toList());
    }
    
    /**
     * Normalizes a title for comparison.
     *
     * @param title the title to normalize
     * @return the normalized title
     */
    private String normalizeTitle(String title) {
        if (title == null) {
            return "";
        }
        
        return title.trim()
                .toLowerCase()
                .replaceAll("[^a-z0-9]", ""); // Remove all non-alphanumeric characters
    }
    
    /**
     * Checks if two years match.
     *
     * @param csvYear  the year from the CSV row
     * @param showYear the year from the show
     * @return true if the years match, false otherwise
     */
    private boolean isYearMatch(Integer csvYear, Integer showYear) {
        // If either year is null, consider it a match
        if (csvYear == null || showYear == null) {
            return true;
        }
        
        // Otherwise, years must be equal
        return csvYear.equals(showYear);
    }
    
    /**
     * Checks if two types match.
     *
     * @param csvType  the type from the CSV row
     * @param showType the type from the show
     * @return true if the types match, false otherwise
     */
    private boolean isTypeMatch(String csvType, String showType) {
        // If either type is null, consider it a match
        if (csvType == null || showType == null) {
            return true;
        }
        
        // Otherwise, types must be equal (case-insensitive)
        return csvType.equalsIgnoreCase(showType);
    }
}
