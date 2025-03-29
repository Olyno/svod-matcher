package org.example.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a show title in any language in the database.
 */
public final class ShowTitle {
    private final UUID showId;
    private final String language;
    private final String title;
    private final Integer episodeNumber;
    private final String episodeName;
    private final Integer seasonNumber;

    private ShowTitle(UUID showId, String language, String title, Integer episodeNumber, String episodeName, Integer seasonNumber) {
        this.showId = showId;
        this.language = language;
        this.title = title;
        this.episodeNumber = episodeNumber;
        this.episodeName = episodeName;
        this.seasonNumber = seasonNumber;
    }

    public UUID getShowId() {
        return showId;
    }

    public String getLanguage() {
        return language;
    }

    public String getTitle() {
        return title;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ShowTitle showTitle = (ShowTitle) o;
        return Objects.equals(showId, showTitle.showId) &&
                Objects.equals(language, showTitle.language) &&
                Objects.equals(title, showTitle.title) &&
                Objects.equals(episodeNumber, showTitle.episodeNumber) &&
                Objects.equals(seasonNumber, showTitle.seasonNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(showId, language, title, episodeNumber, seasonNumber);
    }

    @Override
    public String toString() {
        return "ShowTitle{" +
                "showId=" + showId +
                ", language='" + language + '\'' +
                ", title='" + title + '\'' +
                ", episodeNumber=" + episodeNumber +
                ", episodeName='" + episodeName + '\'' +
                ", seasonNumber=" + seasonNumber +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID showId;
        private String language;
        private String title;
        private Integer episodeNumber;
        private String episodeName;
        private Integer seasonNumber;

        private Builder() {
        }

        public Builder showId(UUID showId) {
            this.showId = showId;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder episodeNumber(Integer episodeNumber) {
            this.episodeNumber = episodeNumber;
            return this;
        }

        public Builder episodeName(String episodeName) {
            this.episodeName = episodeName;
            return this;
        }

        public Builder seasonNumber(Integer seasonNumber) {
            this.seasonNumber = seasonNumber;
            return this;
        }

        public ShowTitle build() {
            return new ShowTitle(showId, language, title, episodeNumber, episodeName, seasonNumber);
        }
    }
}
