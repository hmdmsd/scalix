# Scalix TP Project

## Overview
This project is a Scala application that interacts with the [TMDB API](https://www.themoviedb.org/) to answer questions about actor collaborations and movie details. It demonstrates JSON manipulation, functional programming, and caching techniques.

## Features
- Retrieve actor IDs based on their names.
- Fetch movies in which an actor has appeared.
- Identify directors of specific movies.
- Find collaborations between two actors (common movies and their directors).
- Efficient data caching to minimize API calls.

## Requirements
- Scala 3.3.4
- sbt (Scala Build Tool)
- TMDB API Key

## Setup
1. Clone this repository:
   ```bash
   git clone https://github.com/your-repo/scalix-tp.git
   cd scalix-tp

Add your TMDB API key to the TMDBClient file:

val apiKey: String = "YOUR_API_KEY"

Notes
Caching: All API responses are cached in the data/ directory to avoid unnecessary API calls.
Error Handling: Ensure proper error handling for network issues or invalid API responses.
API Key Security: Do not expose your API key in public repositories.


Authors: Messaoud Hamdi & Mohamed Amine Dahech
