class Actor(val id: Int, val name: String) {
  def movies: Set[Movie] = TMDBClient.findActorMovies(id).map { case (id, title) => new Movie(id, title) }
}

class Movie(val id: Int, val title: String) {
  def director: Option[Director] = TMDBClient.findMovieDirector(id).map { case (id, name) => new Director(id, name) }
}

class Director(val id: Int, val name: String)
