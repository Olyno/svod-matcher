# Project Brief: Show Matching System

## Core Requirements
- Match shows from CSV data against a database of shows and their titles
- Extract all shows from CSV that have confident matches in the database
- Process large datasets efficiently (30k entries in under 1 minute)
- Implement flexible matching algorithm with high confidence

## Entities
1. **Show**: Represents a show in the database
   - UUID id
   - List<String> producers
   - Integer productionYear (nullable)
   - String type (nullable)
   - String originalTitle

2. **ShowTitle**: Represents a show title in any language
   - UUID showId
   - String language
   - String title
   - Integer episodeNumber (nullable)
   - String episodeName (nullable)
   - Integer seasonNumber (nullable)

3. **CSVRow**: Represents a row in a CSV file
   - String originalTitle
   - String frenchTitle (or other language)
   - String producers (needs parsing)
   - Integer productionYear (nullable)
   - String type (nullable)

## Success Criteria
- 100% confidence in matches
- Flexible algorithm that can be extended
- Comprehensive unit tests
- Clean, readable code with final variables by default
- Performance optimization for large datasets
