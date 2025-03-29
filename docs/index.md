# Show Matching System Documentation

## Overview

The Show Matching System is a Java application designed to match shows from CSV data against a database of shows and their titles. It provides a robust matching algorithm that considers title similarity, producer overlap, and metadata comparison to identify matches with high confidence.

## Key Features

- **Title Matching**: Identifies matches based on title containment (case-insensitive)
- **Producer Parsing**: Handles various separator formats in producer strings
- **Metadata Comparison**: Compares production year and show type when available
- **Null-Aware Matching**: Gracefully handles missing data fields
- **Performance Optimization**: Uses indexing and parallel processing for large datasets

## Documentation Sections

- [Architecture](architecture.md) - System design and component relationships
- [Data Models](data-models.md) - Core entity structures and relationships
- [Matching Algorithm](matching-algorithm.md) - How the matching system works
- [Utilities](utilities.md) - Helper components and parsing tools
- [Mock Data](mock-data.md) - Test data generation and structure
- [Getting Started](getting-started.md) - Developer setup guide
- [Performance](performance.md) - Optimization techniques

## Success Criteria

- 100% confidence in matches
- Process 30k entries in under 1 minute
- Clean, maintainable code with immutable objects
- Comprehensive test coverage
