import TMDBClient._ // Importing TMDBClient for API access

class Movie(val id: Int, val title: String) {
  /**
   * Fetches the director of the movie.
   * @return An Option containing the Director object.
   */
  def director: Option[Director] = {
    TMDBClient.findMovieDirector(id).map { case (directorId, name) => new Director(directorId, name) }
  }
}
