# Scalix Movie Application

## Requirements
- Scala version: 3.3.4
- sbt version: 1.8.2

## Dependencies
- json4s for JSON parsing.

## Instructions
1. Clone the repository and navigate to the project root.
2. Ensure your API key is set in `TMDBClient.scala`.
3. Run the project using `sbt run`.

## Design Choices
- **Two-Level Cache**:
   - Primary cache (in-memory) for quick lookup.
   - Secondary cache (file system) for persistence.

- **Object-Oriented Design**:
   - `Actor`, `Movie`, and `Director` classes encapsulate behaviors.
