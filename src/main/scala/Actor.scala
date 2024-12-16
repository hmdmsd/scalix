import TMDBClient._ // Importing TMDBClient for API access

class Actor(val id: Int, val name: String) {
  /**
   * Fetches all movies the actor has been part of.
   * @return A set of Movie objects (id and title).
   */
  def movies: Set[Movie] = {
    TMDBClient.findActorMovies(id).map { case (movieId, title) => new Movie(movieId, title) }
  }
}
