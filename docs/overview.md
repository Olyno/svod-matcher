# Project Overview

## Purpose
Media content matching system that correlates shows across different data sources using similarity algorithms.

## Key Components

### Core Modules
- **Matching Engine** (`matcher` package) - Jaro-Winkler based title matching
- **Data Models** (`model` package) - Show metadata structures
- **CSV Integration** (`CSVRow` model) - External data mapping
- **Mock Data** (`data` package) - Development/testing dataset

### Architectural Flow
```mermaid
graph TD
    A[External Data Sources] --> B[CSVRow Model]
    B --> C[ShowMatcher]
    D[MockData] --> C
    C --> E[MatchResult]
    E --> F[Output Systems]
```

## Main Dependencies
- Java 17
- Gradle build system
- JUnit (testing)
