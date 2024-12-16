# ScalixTP: TMDB Movie Collaboration Finder

## **Project Overview**
ScalixTP is a Scala-based application designed to interact with the TMDB API. It enables users to:
- Find an actor's ID using their first and last names.
- Retrieve movies associated with an actor.
- Find the director of a given movie.
- Identify common movies between two actors and their respective directors.

The application implements efficient caching mechanisms (primary in-memory cache and secondary file-based cache) to minimize redundant API calls and optimize performance.

---

## **Technologies and Tools**

- **Scala**: Version 3.3.4
- **Java**: Version 11+ (required for Scala 3 compatibility)
- **SBT**: Scala Build Tool for dependency management
- **json4s**: Library for JSON parsing
- **ScalaTest**: Library for unit testing

### **Dependencies**
The dependencies are managed in `build.sbt`:
```scala
libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-native" % "4.1.0-M8",
  "org.scalatest" %% "scalatest" % "3.2.17" % Test
)
```

---

## **Project Structure**

```
project/
├── build.sbt
├── src/
│   ├── main/
│   │   ├── scala/
│   │   │   ├── Actor.scala           // Actor class
│   │   │   ├── CacheManager.scala    // Two-level caching mechanism
│   │   │   ├── Director.scala        // Director class
│   │   │   ├── MainApp.scala         // Entry point of the application
│   │   │   ├── Movie.scala           // Movie class
│   │   │   └── TMDBClient.scala      // API client for TMDB
│   ├── test/
│   │   ├── scala/
│   │   │   └── TMDBClientTest.scala  // Unit tests for TMDBClient
```

---

## **How to Run the Application**

### **Prerequisites**
1. Install **Java 11+** (ensure `JAVA_HOME` is correctly set).
2. Install **SBT** (version 1.8+ recommended).

### **Steps to Run**
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd ScalixTP
   ```

2. Update the **API Key** in `TMDBClient.scala`:
   Replace the placeholder with your TMDB API key:
   ```scala
   val apiKey: String = "YOUR_TMDB_API_KEY"
   ```

3. Run the application:
   ```bash
   sbt run
   ```

4. Run the tests:
   ```bash
   sbt test
   ```

5. Package the project (to generate a `.jar` file):
   ```bash
   sbt package
   ```

---

## **Key Design Choices**

### **1. Two-Level Caching**
- **Primary Cache**: In-memory cache using `mutable.Map` for quick lookups.
- **Secondary Cache**: File-based cache stored in the `data/` directory.
- Justification: Reduces redundant API calls to TMDB, optimizing both time and energy usage.

### **2. Object-Oriented Design**
- Encapsulated behavior using `Actor`, `Movie`, and `Director` classes.
- **TMDBClient** acts as the API handler, abstracting API interactions.
- **CacheManager** handles caching to ensure reusability and separation of concerns.

### **3. Error Handling**
- Try-Catch blocks ensure API errors do not crash the application.
- Graceful fallbacks return safe defaults (e.g., `None` or empty sets).

### **4. Testing**
- The `TMDBClientTest.scala` uses ScalaTest to validate:
  - Actor ID retrieval
  - Movie fetching for actors
  - Director fetching for movies
  - Collaboration detection between two actors

---

## **Tools and AI Assistance**
- **IA Generative Tool**: ChatGPT (OpenAI) was used to provide code suggestions, optimization strategies, and testing support.
- **Manual Adjustments**: All generated code was carefully reviewed, modified, and tested to align with project requirements.

---

## **Comments on Section 5 (Architecture Exercise)**

### **Advantages of Object-Oriented Design**
1. **Encapsulation**:
  - The `Actor`, `Movie`, and `Director` classes encapsulate data and associated behavior, enhancing modularity and readability.
2. **Reusability**:
  - Each class and method (e.g., `findActorMovies`, `findMovieDirector`) is reusable in different parts of the application.
3. **Scalability**:
  - New features (e.g., fetching producer details or genres) can be added without modifying existing logic.

### **Potential Drawbacks**
1. **API Coupling**:
  - The models depend directly on `TMDBClient`, making testing slightly harder without stubbing API responses.
  - This could be improved using dependency injection for better testability.

---

## **Potential Improvements**
1. **Dependency Injection**:
  - Use a dependency injection framework to decouple `TMDBClient` from the models.
2. **Parallel Requests**:
  - Use Scala's Parallel Collections or Futures to fetch data concurrently for actors or movies.
3. **JSON Error Handling**:
  - Add robust error handling for malformed JSON responses.

---

## **Conclusion**
This project demonstrates:
- Effective JSON parsing and API interaction using `json4s`.
- Efficient caching mechanisms to minimize API calls.
- A clean, modular design for better scalability and maintainability.

---