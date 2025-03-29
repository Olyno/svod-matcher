package org.example.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Utility class for parsing producer strings with different separators.
 */
public final class ProducerParser {
    
    private static final List<String> COMMON_SEPARATORS = List.of(",", ";", "|", "/");
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+");
    
    private ProducerParser() {
        // Utility class, no instances
    }
    
    /**
     * Parses a producer string into a list of producer names.
     * Attempts to detect the separator used in the string.
     * 
     * @param producerString the producer string to parse
     * @return a list of producer names, or an empty list if the input is null or empty
     */
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
    
    /**
     * Detects the separator used in a producer string.
     * Checks for common separators like comma, semicolon, pipe, and slash.
     * Falls back to whitespace if no common separator is found.
     * 
     * @param producerString the producer string to analyze
     * @return the detected separator
     */
    private static String detectSeparator(String producerString) {
        for (final String separator : COMMON_SEPARATORS) {
            if (producerString.contains(separator)) {
                return separator;
            }
        }
        
        // Fall back to whitespace if no common separator is found
        return "\\s+";
    }
    
    /**
     * Normalizes a producer name by trimming whitespace and converting to lowercase.
     * 
     * @param producerName the producer name to normalize
     * @return the normalized producer name
     */
    public static String normalizeProducerName(String producerName) {
        if (producerName == null) {
            return "";
        }
        
        return WHITESPACE_PATTERN.matcher(producerName.trim()).replaceAll(" ").toLowerCase();
    }
    
    /**
     * Checks if there is at least one producer in common between two lists.
     * 
     * @param producers1 the first list of producers
     * @param producers2 the second list of producers
     * @return true if there is at least one producer in common, false otherwise
     */
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
