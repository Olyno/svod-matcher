package org.example.model;

import java.util.Objects;

/**
 * Represents a row in a CSV file.
 */
public final class CSVRow {
    private final String originalTitle;
    private final String translatedTitle;
    private final String producers;
    private final Integer productionYear;
    private final String type;

    private CSVRow(String originalTitle, String translatedTitle, String producers, Integer productionYear, String type) {
        this.originalTitle = originalTitle;
        this.translatedTitle = translatedTitle;
        this.producers = producers;
        this.productionYear = productionYear;
        this.type = type;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getTranslatedTitle() {
        return translatedTitle;
    }

    public String getProducers() {
        return producers;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CSVRow csvRow = (CSVRow) o;
        return Objects.equals(originalTitle, csvRow.originalTitle) &&
                Objects.equals(translatedTitle, csvRow.translatedTitle) &&
                Objects.equals(producers, csvRow.producers) &&
                Objects.equals(productionYear, csvRow.productionYear) &&
                Objects.equals(type, csvRow.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalTitle, translatedTitle, producers, productionYear, type);
    }

    @Override
    public String toString() {
        return "CSVRow{" +
                "originalTitle='" + originalTitle + '\'' +
                ", translatedTitle='" + translatedTitle + '\'' +
                ", producers='" + producers + '\'' +
                ", productionYear=" + productionYear +
                ", type='" + type + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String originalTitle;
        private String translatedTitle;
        private String producers;
        private Integer productionYear;
        private String type;

        private Builder() {
        }

        public Builder originalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
            return this;
        }

        public Builder translatedTitle(String translatedTitle) {
            this.translatedTitle = translatedTitle;
            return this;
        }

        public Builder producers(String producers) {
            this.producers = producers;
            return this;
        }

        public Builder productionYear(Integer productionYear) {
            this.productionYear = productionYear;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public CSVRow build() {
            return new CSVRow(originalTitle, translatedTitle, producers, productionYear, type);
        }
    }
}
