package org.example.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.example.model.CSVRow;
import org.example.model.Show;
import org.example.model.ShowTitle;

/**
 * Provides mock data for testing the show matching algorithm.
 */
public final class MockData {

        private MockData() {
                // Utility class, no instances
        }

        /**
         * Creates a list of mock shows.
         *
         * @return a list of mock shows
         */
        public static List<Show> createMockShows() {
                final List<Show> shows = new ArrayList<>();

                // Show 1: Garfield
                final UUID garfieldId = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(garfieldId)
                                .originalTitle("Garfield: The Movie")
                                .producers(List.of("20th Century Fox", "Davis Entertainment"))
                                .productionYear(2004)
                                .type("movie")
                                .build());

                // Show 2: The Matrix
                final UUID matrixId = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(matrixId)
                                .originalTitle("The Matrix")
                                .producers(List.of("Warner Bros.", "Village Roadshow Pictures"))
                                .productionYear(1999)
                                .type("movie")
                                .build());

                // Show 3: Friends
                final UUID friendsId = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(friendsId)
                                .originalTitle("Friends")
                                .producers(List.of("Bright/Kauffman/Crane Productions", "Warner Bros. Television"))
                                .productionYear(1994)
                                .type("series")
                                .build());

                // Show 4: Inception
                final UUID inceptionId = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(inceptionId)
                                .originalTitle("Inception")
                                .producers(List.of("Warner Bros.", "Legendary Pictures", "Syncopy"))
                                .productionYear(2010)
                                .type("movie")
                                .build());

                // Show 5: Breaking Bad
                final UUID breakingBadId = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(breakingBadId)
                                .originalTitle("Breaking Bad")
                                .producers(List.of("High Bridge Productions", "Gran Via Productions",
                                                "Sony Pictures Television"))
                                .productionYear(2008)
                                .type("series")
                                .build());

                // Show 6: Star Wars: Episode IV - A New Hope
                final UUID starWarsId = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(starWarsId)
                                .originalTitle("Star Wars: Episode IV - A New Hope")
                                .producers(List.of("Lucasfilm Ltd."))
                                .productionYear(1977)
                                .type("movie")
                                .build());

                // Show 7: The Lord of the Rings: The Fellowship of the Ring
                final UUID lotrId = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(lotrId)
                                .originalTitle("The Lord of the Rings: The Fellowship of the Ring")
                                .producers(List.of("New Line Cinema", "WingNut Films"))
                                .productionYear(2001)
                                .type("movie")
                                .build());

                // Show 8: Game of Thrones
                final UUID gotId = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(gotId)
                                .originalTitle("Game of Thrones")
                                .producers(List.of("HBO", "Television 360", "Grok! Television"))
                                .productionYear(2011)
                                .type("series")
                                .build());

                // Show 9: The Shawshank Redemption
                final UUID shawshankId = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(shawshankId)
                                .originalTitle("The Shawshank Redemption")
                                .producers(List.of("Castle Rock Entertainment"))
                                .productionYear(1994)
                                .type("movie")
                                .build());

                // Show 10: The Godfather
                final UUID godfatherId = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(godfatherId)
                                .originalTitle("The Godfather")
                                .producers(List.of("Paramount Pictures", "Alfran Productions"))
                                .productionYear(1972)
                                .type("movie")
                                .build());

                // Show 11: Amélie (with accented characters)
                final UUID amelieId = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(amelieId)
                                .originalTitle("Le Fabuleux Destin d'Amélie Poulain")
                                .producers(List.of("Claudie Ossard Productions", "UGC"))
                                .productionYear(2001)
                                .type("movie")
                                .build());

                // Show 12: Spirited Away (with special characters and parentheses)
                final UUID spiritedAwayId = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(spiritedAwayId)
                                .originalTitle("千と千尋の神隠し (Spirited Away)")
                                .producers(List.of("Studio Ghibli", "Tokuma Shoten"))
                                .productionYear(2001)
                                .type("movie")
                                .build());

                // Show 13: The 100 (with numbers)
                final UUID the100Id = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(the100Id)
                                .originalTitle("The 100")
                                .producers(List.of("Warner Bros. Television", "CBS Television Studios"))
                                .productionYear(2014)
                                .type("series")
                                .build());

                // Show 14: Stranger Things (with special formatting)
                final UUID strangerThingsId = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(strangerThingsId)
                                .originalTitle("STRANGER THINGS")
                                .producers(List.of("21 Laps Entertainment", "Monkey Massacre"))
                                .productionYear(2016)
                                .type("series")
                                .build());

                // Show 15: Monty Python's Flying Circus (with apostrophe)
                final UUID montyPythonId = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(montyPythonId)
                                .originalTitle("Monty Python's Flying Circus")
                                .producers(List.of("BBC"))
                                .productionYear(1969)
                                .type("series")
                                .build());

                // Show 16: 2001: A Space Odyssey (with year in title)
                final UUID spaceOdysseyId = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(spaceOdysseyId)
                                .originalTitle("2001: A Space Odyssey")
                                .producers(List.of("Metro-Goldwyn-Mayer", "Stanley Kubrick Productions"))
                                .productionYear(1968)
                                .type("movie")
                                .build());

                // Show 17: M*A*S*H (with asterisks)
                final UUID mashId = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(mashId)
                                .originalTitle("M*A*S*H")
                                .producers(List.of("20th Century Fox Television"))
                                .productionYear(1972)
                                .type("series")
                                .build());

                // Show 18: Se7en (with number substitution)
                final UUID se7enId = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(se7enId)
                                .originalTitle("Se7en")
                                .producers(List.of("New Line Cinema", "Cecchi Gori Pictures"))
                                .productionYear(1995)
                                .type("movie")
                                .build());

                // Show 19: "The Office" (with quotes)
                final UUID officeId = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(officeId)
                                .originalTitle("\"The Office\"")
                                .producers(List.of("Deedle-Dee Productions", "Reveille Productions",
                                                "NBC Universal Television"))
                                .productionYear(2005)
                                .type("series")
                                .build());

                // Show 20: [REC] (with brackets)
                final UUID recId = UUID.randomUUID();
                shows.add(Show.builder()
                                .id(recId)
                                .originalTitle("[REC]")
                                .producers(List.of("Filmax"))
                                .productionYear(2007)
                                .type("movie")
                                .build());

                return shows;
        }

        /**
         * Creates a list of mock show titles.
         *
         * @param shows the list of shows to create titles for
         * @return a list of mock show titles
         */
        public static List<ShowTitle> createMockShowTitles(List<Show> shows) {
                final List<ShowTitle> showTitles = new ArrayList<>();

                for (final Show show : shows) {
                        // Add original title
                        showTitles.add(ShowTitle.builder()
                                        .showId(show.getId())
                                        .language("en")
                                        .title(show.getOriginalTitle())
                                        .build());

                        // Add French title based on original title
                        final String frenchTitle = translateToFrench(show.getOriginalTitle());
                        showTitles.add(ShowTitle.builder()
                                        .showId(show.getId())
                                        .language("fr")
                                        .title(frenchTitle)
                                        .build());

                        // Add Spanish title based on original title
                        final String spanishTitle = translateToSpanish(show.getOriginalTitle());
                        showTitles.add(ShowTitle.builder()
                                        .showId(show.getId())
                                        .language("es")
                                        .title(spanishTitle)
                                        .build());
                }

                // Add some episode titles for series
                for (final Show show : shows) {
                        if ("series".equalsIgnoreCase(show.getType())) {
                                // Add episode titles for the first season
                                for (int i = 1; i <= 5; i++) {
                                        showTitles.add(ShowTitle.builder()
                                                        .showId(show.getId())
                                                        .language("en")
                                                        .title(show.getOriginalTitle() + " - S01E0" + i)
                                                        .seasonNumber(1)
                                                        .episodeNumber(i)
                                                        .episodeName("Episode " + i)
                                                        .build());

                                        showTitles.add(ShowTitle.builder()
                                                        .showId(show.getId())
                                                        .language("fr")
                                                        .title(translateToFrench(show.getOriginalTitle()) + " - S01E0"
                                                                        + i)
                                                        .seasonNumber(1)
                                                        .episodeNumber(i)
                                                        .episodeName("Épisode " + i)
                                                        .build());
                                }
                        }
                }

                return showTitles;
        }

        /**
         * Creates a list of mock CSV rows.
         *
         * @return a list of mock CSV rows
         */
        public static List<CSVRow> createMockCSVRows() {
                final List<CSVRow> csvRows = new ArrayList<>();

                // CSV Row 1: Garfield (exact match)
                csvRows.add(CSVRow.builder()
                                .originalTitle("Garfield: The Movie")
                                .translatedTitle("Garfield, le film")
                                .producers("20th Century Fox, Davis Entertainment")
                                .productionYear(2004)
                                .type("movie")
                                .build());

                // CSV Row 2: Garfield (partial title match)
                csvRows.add(CSVRow.builder()
                                .originalTitle("Garfield")
                                .translatedTitle("Garfield")
                                .producers("20th Century Fox, Davis Entertainment")
                                .productionYear(2004)
                                .type("movie")
                                .build());

                // CSV Row 3: The Matrix (exact match)
                csvRows.add(CSVRow.builder()
                                .originalTitle("The Matrix")
                                .translatedTitle("Matrix")
                                .producers("Warner Bros.; Village Roadshow Pictures")
                                .productionYear(1999)
                                .type("movie")
                                .build());

                // CSV Row 4: Friends (exact match)
                csvRows.add(CSVRow.builder()
                                .originalTitle("Friends")
                                .translatedTitle("Friends")
                                .producers("Bright/Kauffman/Crane Productions | Warner Bros. Television")
                                .productionYear(1994)
                                .type("series")
                                .build());

                // CSV Row 5: Inception (exact match)
                csvRows.add(CSVRow.builder()
                                .originalTitle("Inception")
                                .translatedTitle("Inception")
                                .producers("Warner Bros., Legendary Pictures, Syncopy")
                                .productionYear(2010)
                                .type("movie")
                                .build());

                // CSV Row 6: Breaking Bad (exact match)
                csvRows.add(CSVRow.builder()
                                .originalTitle("Breaking Bad")
                                .translatedTitle("Breaking Bad")
                                .producers("High Bridge Productions, Gran Via Productions, Sony Pictures Television")
                                .productionYear(2008)
                                .type("series")
                                .build());

                // CSV Row 7: Star Wars (partial title match)
                csvRows.add(CSVRow.builder()
                                .originalTitle("Star Wars")
                                .translatedTitle("La Guerre des Étoiles")
                                .producers("Lucasfilm Ltd.")
                                .productionYear(1977)
                                .type("movie")
                                .build());

                // CSV Row 8: The Lord of the Rings (partial title match)
                csvRows.add(CSVRow.builder()
                                .originalTitle("The Lord of the Rings")
                                .translatedTitle("Le Seigneur des Anneaux")
                                .producers("New Line Cinema, WingNut Films")
                                .productionYear(2001)
                                .type("movie")
                                .build());

                // CSV Row 9: Game of Thrones (exact match)
                csvRows.add(CSVRow.builder()
                                .originalTitle("Game of Thrones")
                                .translatedTitle("Le Trône de Fer")
                                .producers("HBO, Television 360, Grok! Television")
                                .productionYear(2011)
                                .type("series")
                                .build());

                // CSV Row 10: The Shawshank Redemption (exact match)
                csvRows.add(CSVRow.builder()
                                .originalTitle("The Shawshank Redemption")
                                .translatedTitle("Les Évadés")
                                .producers("Castle Rock Entertainment")
                                .productionYear(1994)
                                .type("movie")
                                .build());

                // CSV Row 11: The Godfather (exact match)
                csvRows.add(CSVRow.builder()
                                .originalTitle("The Godfather")
                                .translatedTitle("Le Parrain")
                                .producers("Paramount Pictures, Alfran Productions")
                                .productionYear(1972)
                                .type("movie")
                                .build());

                // CSV Row 12: Amélie (with accented characters)
                csvRows.add(CSVRow.builder()
                                .originalTitle("Le Fabuleux Destin d'Amélie Poulain")
                                .translatedTitle("Amélie")
                                .producers("Claudie Ossard Productions, UGC")
                                .productionYear(2001)
                                .type("movie")
                                .build());

                // CSV Row 13: Spirited Away (with special characters and parentheses)
                csvRows.add(CSVRow.builder()
                                .originalTitle("千と千尋の神隠し (Spirited Away)")
                                .translatedTitle("Le Voyage de Chihiro")
                                .producers("Studio Ghibli, Tokuma Shoten")
                                .productionYear(2001)
                                .type("movie")
                                .build());

                // CSV Row 14: The 100 (with numbers)
                csvRows.add(CSVRow.builder()
                                .originalTitle("The 100")
                                .translatedTitle("Les 100")
                                .producers("Warner Bros. Television, CBS Television Studios")
                                .productionYear(2014)
                                .type("series")
                                .build());

                // CSV Row 15: Stranger Things (with special formatting)
                csvRows.add(CSVRow.builder()
                                .originalTitle("STRANGER THINGS")
                                .translatedTitle("Stranger Things")
                                .producers("21 Laps Entertainment, Monkey Massacre")
                                .productionYear(2016)
                                .type("series")
                                .build());

                // CSV Row 16: Monty Python's Flying Circus (with apostrophe)
                csvRows.add(CSVRow.builder()
                                .originalTitle("Monty Python's Flying Circus")
                                .translatedTitle("Monty Python's Flying Circus")
                                .producers("BBC")
                                .productionYear(1969)
                                .type("series")
                                .build());

                // CSV Row 17: 2001: A Space Odyssey (with year in title)
                csvRows.add(CSVRow.builder()
                                .originalTitle("2001: A Space Odyssey")
                                .translatedTitle("2001 : L'Odyssée de l'espace")
                                .producers("Metro-Goldwyn-Mayer, Stanley Kubrick Productions")
                                .productionYear(1968)
                                .type("movie")
                                .build());

                // CSV Row 18: M*A*S*H (with asterisks)
                csvRows.add(CSVRow.builder()
                                .originalTitle("M*A*S*H")
                                .translatedTitle("M*A*S*H")
                                .producers("20th Century Fox Television")
                                .productionYear(1972)
                                .type("series")
                                .build());

                // CSV Row 19: Se7en (with number substitution)
                csvRows.add(CSVRow.builder()
                                .originalTitle("Se7en")
                                .translatedTitle("Seven")
                                .producers("New Line Cinema, Cecchi Gori Pictures")
                                .productionYear(1995)
                                .type("movie")
                                .build());

                // CSV Row 20: "The Office" (with quotes)
                csvRows.add(CSVRow.builder()
                                .originalTitle("\"The Office\"")
                                .translatedTitle("The Office")
                                .producers("Deedle-Dee Productions, Reveille Productions, NBC Universal Television")
                                .productionYear(2005)
                                .type("series")
                                .build());

                // CSV Row 21: [REC] (with brackets)
                csvRows.add(CSVRow.builder()
                                .originalTitle("[REC]")
                                .translatedTitle("[REC]")
                                .producers("Filmax")
                                .productionYear(2007)
                                .type("movie")
                                .build());

                // CSV Row 22: Non-existent show
                csvRows.add(CSVRow.builder()
                                .originalTitle("Non-existent Show")
                                .translatedTitle("Émission inexistante")
                                .producers("Fake Productions")
                                .productionYear(2022)
                                .type("movie")
                                .build());

                // CSV Row 23: Partial match with wrong year
                csvRows.add(CSVRow.builder()
                                .originalTitle("Garfield")
                                .translatedTitle("Garfield")
                                .producers("20th Century Fox, Davis Entertainment")
                                .productionYear(2005) // Wrong year
                                .type("movie")
                                .build());

                // CSV Row 24: Partial match with wrong type
                csvRows.add(CSVRow.builder()
                                .originalTitle("Garfield")
                                .translatedTitle("Garfield")
                                .producers("20th Century Fox, Davis Entertainment")
                                .productionYear(2004)
                                .type("series") // Wrong type
                                .build());

                // CSV Row 25: Partial match with wrong producers
                csvRows.add(CSVRow.builder()
                                .originalTitle("Garfield")
                                .translatedTitle("Garfield")
                                .producers("Wrong Productions") // Wrong producers
                                .productionYear(2004)
                                .type("movie")
                                .build());

                return csvRows;
        }

        /**
         * Simulates translating a title to French.
         *
         * @param title the title to translate
         * @return the translated title
         */
        private static String translateToFrench(String title) {
                if (title == null) {
                        return null;
                }

                return switch (title) {
                        case "Garfield: The Movie" -> "Garfield, le film";
                        case "The Matrix" -> "Matrix";
                        case "Friends" -> "Friends";
                        case "Inception" -> "Inception";
                        case "Breaking Bad" -> "Breaking Bad";
                        case "Star Wars: Episode IV - A New Hope" -> "La Guerre des Étoiles: Un Nouvel Espoir";
                        case "The Lord of the Rings: The Fellowship of the Ring" ->
                                "Le Seigneur des Anneaux: La Communauté de l'Anneau";
                        case "Game of Thrones" -> "Le Trône de Fer";
                        case "The Shawshank Redemption" -> "Les Évadés";
                        case "The Godfather" -> "Le Parrain";
                        case "Le Fabuleux Destin d'Amélie Poulain" -> "Le Fabuleux Destin d'Amélie Poulain";
                        case "千と千尋の神隠し (Spirited Away)" -> "Le Voyage de Chihiro";
                        case "The 100" -> "Les 100";
                        case "STRANGER THINGS" -> "Stranger Things";
                        case "Monty Python's Flying Circus" -> "Monty Python's Flying Circus";
                        case "2001: A Space Odyssey" -> "2001 : L'Odyssée de l'espace";
                        case "M*A*S*H" -> "M*A*S*H";
                        case "Se7en" -> "Seven";
                        case "\"The Office\"" -> "The Office";
                        case "[REC]" -> "[REC]";
                        default -> title + " (en français)";
                };
        }

        /**
         * Simulates translating a title to Spanish.
         *
         * @param title the title to translate
         * @return the translated title
         */
        private static String translateToSpanish(String title) {
                if (title == null) {
                        return null;
                }

                return switch (title) {
                        case "Garfield: The Movie" -> "Garfield: La Película";
                        case "The Matrix" -> "Matrix";
                        case "Friends" -> "Friends";
                        case "Inception" -> "Origen";
                        case "Breaking Bad" -> "Breaking Bad";
                        case "Star Wars: Episode IV - A New Hope" -> "La Guerra de las Galaxias: Una Nueva Esperanza";
                        case "The Lord of the Rings: The Fellowship of the Ring" ->
                                "El Señor de los Anillos: La Comunidad del Anillo";
                        case "Game of Thrones" -> "Juego de Tronos";
                        case "The Shawshank Redemption" -> "Cadena Perpetua";
                        case "The Godfather" -> "El Padrino";
                        case "Le Fabuleux Destin d'Amélie Poulain" -> "Amélie";
                        case "千と千尋の神隠し (Spirited Away)" -> "El Viaje de Chihiro";
                        case "The 100" -> "Los 100";
                        case "STRANGER THINGS" -> "Stranger Things";
                        case "Monty Python's Flying Circus" -> "El Circo Volador de Monty Python";
                        case "2001: A Space Odyssey" -> "2001: Una Odisea del Espacio";
                        case "M*A*S*H" -> "M*A*S*H";
                        case "Se7en" -> "Se7en";
                        case "\"The Office\"" -> "La Oficina";
                        case "[REC]" -> "[REC]";
                        default -> title + " (en español)";
                };
        }
}
