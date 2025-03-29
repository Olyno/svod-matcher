package org.example.model;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a show in the database.
 */
public final class Show {
    private final UUID id;
    private final List<String> producers;
    private final Integer productionYear;
    private final String type;
    private final String originalTitle;

    private Show(UUID id, List<String> producers, Integer productionYear, String type, String originalTitle) {
        this.id = id;
        this.producers = producers;
        this.productionYear = productionYear;
        this.type = type;
        this.originalTitle = originalTitle;
    }

    public UUID getId() {
        return id;
    }

    public List<String> getProducers() {
        return producers;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public String getType() {
        return type;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Show show = (Show) o;
        return Objects.equals(id, show.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Show{" +
                "id=" + id +
                ", producers=" + producers +
                ", productionYear=" + productionYear +
                ", type='" + type + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID id;
        private List<String> producers;
        private Integer productionYear;
        private String type;
        private String originalTitle;

        private Builder() {
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder producers(List<String> producers) {
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

        public Builder originalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
            return this;
        }

        public Show build() {
            return new Show(id, producers, productionYear, type, originalTitle);
        }
    }
}
