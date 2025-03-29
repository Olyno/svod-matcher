package org.example.matcher;

import org.example.model.CSVRow;
import org.example.model.ShowTitle;

import java.util.Objects;

/**
 * Represents the result of a match between a CSVRow and a ShowTitle.
 */
public final class MatchResult {
    private final CSVRow csvRow;
    private final ShowTitle showTitle;
    private final boolean isMatch;
    private final String reason;

    private MatchResult(CSVRow csvRow, ShowTitle showTitle, boolean isMatch, String reason) {
        this.csvRow = csvRow;
        this.showTitle = showTitle;
        this.isMatch = isMatch;
        this.reason = reason;
    }

    public CSVRow getCsvRow() {
        return csvRow;
    }

    public ShowTitle getShowTitle() {
        return showTitle;
    }

    public boolean isMatch() {
        return isMatch;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final MatchResult that = (MatchResult) o;
        return isMatch == that.isMatch &&
                Objects.equals(csvRow, that.csvRow) &&
                Objects.equals(showTitle, that.showTitle) &&
                Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(csvRow, showTitle, isMatch, reason);
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "csvRow=" + csvRow +
                ", showTitle=" + showTitle +
                ", isMatch=" + isMatch +
                ", reason='" + reason + '\'' +
                '}';
    }

    /**
     * Creates a successful match result.
     *
     * @param csvRow    the CSV row
     * @param showTitle the show title
     * @return a successful match result
     */
    public static MatchResult match(CSVRow csvRow, ShowTitle showTitle) {
        return new MatchResult(csvRow, showTitle, true, "Match found");
    }

    /**
     * Creates a failed match result.
     *
     * @param csvRow    the CSV row
     * @param showTitle the show title
     * @param reason    the reason for the failed match
     * @return a failed match result
     */
    public static MatchResult noMatch(CSVRow csvRow, ShowTitle showTitle, String reason) {
        return new MatchResult(csvRow, showTitle, false, reason);
    }
}
