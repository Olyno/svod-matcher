# Technical Context

## Technologies Used
- Java 17+ (for modern language features)
- JUnit 5 (for testing)
- Apache Commons Text (for string similarity)
- Java Streams API (for data processing)
- Gradle (for build management)

## Development Setup
- Standard Java project structure
- Gradle for dependency management
- JUnit for unit testing

## Technical Constraints
- Must process 30k entries in under 1 minute
- All variables should be final by default
- Code must be easily readable and maintainable

## Dependencies
- Apache Commons Text (for string similarity algorithms)
- JUnit 5 (for testing)
- Mockito (for mocking in tests)

## Tool Usage Patterns
- Builder pattern for complex objects
- Factory pattern for creating matchers
- Strategy pattern for different matching algorithms
